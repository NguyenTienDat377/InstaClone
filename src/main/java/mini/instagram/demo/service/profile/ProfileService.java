package mini.instagram.demo.service.profile;

import java.nio.file.attribute.UserPrincipal;

import mini.instagram.demo.model.Profile;
import mini.instagram.demo.dto.profile.UpdateProfileRequest;

public interface ProfileService {
    Profile getUserProfile(UserPrincipal userPrincipal);

    Profile getUserProfile(int id);

    Profile updateProfile(UserPrincipal userPrincipal, UpdateProfileRequest updateProfileRequest);
}
