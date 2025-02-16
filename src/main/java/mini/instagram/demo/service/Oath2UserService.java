package mini.instagram.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import mini.instagram.demo.dto.OAuth2UserInfoDto;
import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.model.User;
import mini.instagram.demo.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oath2UserService  extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        log.trace("Load user{}", oAuth2UserRequest);
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfoDto userInfoDto = OAuth2UserInfoDto.builder()
            .name(oAuth2User.getAttributes().get("name").toString())
            .id(oAuth2User.getAttributes().get("id").toString())
            .email(oAuth2User.getAttributes().get("email").toString())
            .picture(oAuth2User.getAttributes().get("picture").toString())
            .build();
        Optional<User> userOptional = userRepository.findByUserName(userInfoDto.getEmail());
        User user = userOptional.map(existingUser -> updateExistingUser(existingUser, userInfoDto)).orElseGet(() -> registerNewUser(oAuth2UserRequest, userInfoDto));
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfoDto userInfoDto) {
        User user = new User();
        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setProviderId(userInfoDto.getId());
        user.setName(userInfoDto.getName());
        user.setUsername(userInfoDto.getEmail());
        user.setPicture(userInfoDto.getPicture());
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfoDto userInfoDto) {
        existingUser.setName(userInfoDto.getEmail());
        existingUser.setPicture(userInfoDto.getPicture());
        return userRepository.save(existingUser);
    }
}
