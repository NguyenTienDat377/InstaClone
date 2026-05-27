package mini.instagram.demo.service.profile;

import mini.instagram.demo.dto.UserPrincipal;

import mini.instagram.demo.model.Profile;
import mini.instagram.demo.dto.profile.UpdateProfileImageRequest;
import mini.instagram.demo.dto.profile.UpdateProfileRequest;

public interface ProfileService {
    Profile getUserProfile(UserPrincipal userPrincipal);

    Profile getUserProfile(int id);

    Profile updateProfile(UserPrincipal userPrincipal, UpdateProfileRequest updateProfileRequest);

    Profile updateProfileImage(UserPrincipal userPrincipal, UpdateProfileImageRequest updateProfileImageRequest);

    mini.instagram.demo.dto.profile.GetProfileResponse getProfileResponse(int profileId);
}
