package mini.instagram.demo.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.FollowUserResponse;
import mini.instagram.demo.dto.profile.GetFollowerResponse;
import mini.instagram.demo.dto.profile.GetFollowingResponse;
import mini.instagram.demo.service.profile.FollowerService;

@RestController
@RequestMapping("/api/profiles")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/{profileId}/follow")
    public ResponseEntity<FollowUserResponse> followUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable int profileId) {
        followerService.followUser(userPrincipal, profileId);
        return ResponseEntity.ok(FollowUserResponse.builder().success(true).build());
    }

    @DeleteMapping("/{profileId}/follow")
    public ResponseEntity<Void> unfollowUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable int profileId) {
        followerService.unfollowUser(userPrincipal, profileId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{profileId}/followers")
    public ResponseEntity<GetFollowerResponse> getFollowers(
            @PathVariable int profileId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(followerService.getFollowers(profileId, page, limit));
    }

    @GetMapping("/{profileId}/following")
    public ResponseEntity<GetFollowingResponse> getFollowing(
            @PathVariable int profileId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(followerService.getFollowing(profileId, page, limit));
    }
}
