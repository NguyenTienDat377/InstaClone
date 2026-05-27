package mini.instagram.demo.service.feed;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mini.instagram.demo.model.Post;
import mini.instagram.demo.repository.FollowerRepository;
import mini.instagram.demo.repository.PostRepository;
import mini.instagram.demo.service.profile.ProfileService;
import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.GetFeedResponse;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.model.UserFollowing;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public GetFeedResponse getFeed(UserPrincipal userPrincipal, int limit, int page) {
        Profile profile = profileService.getUserProfile(userPrincipal);

        List<UserFollowing> followings = followerRepository.findByFollowersUserId(profile.getId());
        List<Integer> followingIds = followings.stream()
            .map(UserFollowing::getFollowingUserId)
            .toList();

        log.info("followingIds={}", followingIds);

        if (followingIds.isEmpty()) {
            return GetFeedResponse.builder().posts(Collections.emptyList()).totalPages(0).build();
        }

        int totalPost = postRepository.countByCreatedByIn(followingIds);
        int totalPages = (int) Math.ceil((double) totalPost / limit);
        int offset = (page - 1) * limit;

        log.info("totalPost={}, totalPages={}, offset={}", totalPost, totalPages, offset);

        List<Post> posts = postRepository.findByCreatedByIdsWithPagination(followingIds, limit, offset);

        return GetFeedResponse.builder()
            .posts(posts)
            .totalPages(totalPages)
            .build();
    }
}
