package mini.instagram.demo.service.profile;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.GetProfileResponse;
import mini.instagram.demo.dto.profile.UpdateProfileImageRequest;
import mini.instagram.demo.dto.profile.UpdateProfileRequest;
import mini.instagram.demo.exception.UserNotFoundException;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.repository.FollowerRepository;
import mini.instagram.demo.repository.PostRepository;
import mini.instagram.demo.repository.ProfileRepository;
import mini.instagram.demo.service.UploadService;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public Profile updateProfile(UserPrincipal userPrincipal, UpdateProfileRequest updateProfileRequest) {
        Profile profile = profileRepository.findOneByUserId(userPrincipal.getId().toString());
        profile.setBio(updateProfileRequest.getBio());
        profile.setDisplayName(updateProfileRequest.getDisplayName());
        profile.setUsername(updateProfileRequest.getUsername());
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile getUserProfile(int userId) {
        return profileRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

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
    public Profile updateProfileImage(UserPrincipal userPrincipal, UpdateProfileImageRequest request) {
        String url = uploadService.uploadImage(request.getBase64ImageString());
        Profile profile = this.getUserProfile(userPrincipal);
        profile.setProfileUserImg(url);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public GetProfileResponse getProfileResponse(int profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(UserNotFoundException::new);
        int numberOfPosts = postRepository.countByCreatedBy(profile);
        int numberOfFollowers = followerRepository.countByFollowingUserId(profileId);
        int numberOfFollowing = followerRepository.countByFollowersUserId(profileId);
        return GetProfileResponse.builder()
            .profile(profile)
            .numberOfPosts(numberOfPosts)
            .numberOfFollowers(numberOfFollowers)
            .numberOfFollowing(numberOfFollowing)
            .build();
    }
}
