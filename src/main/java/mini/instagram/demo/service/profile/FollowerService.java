package mini.instagram.demo.service.profile;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.GetFollowerResponse;
import mini.instagram.demo.dto.profile.GetFollowingResponse;

public interface FollowerService {
    void followUser(UserPrincipal userPrincipal, int targetProfileId);

    void unfollowUser(UserPrincipal userPrincipal, int targetProfileId);

    GetFollowerResponse getFollowers(int profileId, int page, int limit);

    GetFollowingResponse getFollowing(int profileId, int page, int limit);
}
