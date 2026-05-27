package mini.instagram.demo.service.profile;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.profile.GetFollowerResponse;
import mini.instagram.demo.dto.profile.GetFollowingResponse;
import mini.instagram.demo.exception.InvalidInputException;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.model.UserFollowing;
import mini.instagram.demo.repository.FollowerRepository;
import mini.instagram.demo.repository.ProfileRepository;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Override
    public void followUser(UserPrincipal userPrincipal, int targetProfileId) {
        Profile profile = profileService.getUserProfile(userPrincipal);
        if (profile.getId() == targetProfileId) {
            throw new InvalidInputException();
        }
        UserFollowing existing = followerRepository.findByFollowersUserIdAndFollowingUserId(profile.getId(), targetProfileId);
        if (existing != null) {
            return;
        }
        UserFollowing userFollowing = UserFollowing.builder()
            .followersUserId(profile.getId())
            .followingUserId(targetProfileId)
            .createdAt(new Date())
            .build();
        followerRepository.save(userFollowing);
    }

    @Override
    public void unfollowUser(UserPrincipal userPrincipal, int targetProfileId) {
        Profile profile = profileService.getUserProfile(userPrincipal);
        followerRepository.deleteByFollowersUserIdAndFollowingUserId(profile.getId(), targetProfileId);
    }

    @Override
    public GetFollowerResponse getFollowers(int profileId, int page, int limit) {
        List<UserFollowing> followings = followerRepository.findByFollowingUserId(profileId);
        int total = followings.size();
        int totalPages = (int) Math.ceil((double) total / limit);
        int offset = (page - 1) * limit;
        List<Integer> profileIds = followings.stream()
            .map(UserFollowing::getFollowersUserId)
            .skip(offset)
            .limit(limit)
            .toList();
        List<Profile> profiles = profileRepository.findAllById(profileIds);
        return GetFollowerResponse.builder()
            .profiles(profiles)
            .totalPages(totalPages)
            .build();
    }

    @Override
    public GetFollowingResponse getFollowing(int profileId, int page, int limit) {
        List<UserFollowing> followings = followerRepository.findByFollowersUserId(profileId);
        int total = followings.size();
        int totalPages = (int) Math.ceil((double) total / limit);
        int offset = (page - 1) * limit;
        List<Integer> profileIds = followings.stream()
            .map(UserFollowing::getFollowingUserId)
            .skip(offset)
            .limit(limit)
            .toList();
        List<Profile> profiles = profileRepository.findAllById(profileIds);
        return GetFollowingResponse.builder()
            .profiles(profiles)
            .totalPages(totalPages)
            .build();
    }
}
