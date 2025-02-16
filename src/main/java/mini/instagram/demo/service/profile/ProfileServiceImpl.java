package mini.instagram.demo.service.profile;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.UpdateProfileRequest;
import mini.instagram.demo.exception.UserNotFoundException;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.repository.ProfileRepository;
import mini.instagram.demo.service.UploadService;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UploadService uploadService;

    @Override
    public Profile getUserProfile(UserPrincipal userPrincipal) {
        Profile profile = profileRepository.findOneByUserId(userPrincipal.getId().toString());
        if (Objects.isNull(profile)) {
            profile = new Profile();
            profile.setUserId(userPrincipal.getId().toString());
            profile.setDisplayName(userPrincipal.getName());
            profileRepository.save(profile);
        }
        return profile;
    }

    @Override
    public Profile getUserProfile(int id) {
        return profileRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Profile updateProfile(UserPrincipal userPrincipal, UpdateProfileRequest updateProfileRequest) {
        Profile profile = profileRepository.
    }

}
