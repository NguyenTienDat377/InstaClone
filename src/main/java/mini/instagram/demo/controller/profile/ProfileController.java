package mini.instagram.demo.controller.profile;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.GetProfileResponse;
import mini.instagram.demo.dto.profile.UpdateProfileImageRequest;
import mini.instagram.demo.dto.profile.UpdateProfileImageResponse;
import mini.instagram.demo.dto.profile.UpdateProfileRequest;
import mini.instagram.demo.dto.profile.UpdateProfileResponse;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.service.profile.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable int id) {
        return ResponseEntity.ok(profileService.getProfileResponse(id));
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateProfileResponse> updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UpdateProfileRequest request) {
        Profile profile = profileService.updateProfile(userPrincipal, request);
        return ResponseEntity.ok(UpdateProfileResponse.builder().profile(profile).build());
    }

    @PutMapping("/me/image")
    public ResponseEntity<UpdateProfileImageResponse> updateProfileImage(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody UpdateProfileImageRequest request) {
        Profile profile = profileService.updateProfileImage(userPrincipal, request);
        return ResponseEntity.ok(UpdateProfileImageResponse.builder().url(profile.getProfileUserImg()).build());
    }
}
