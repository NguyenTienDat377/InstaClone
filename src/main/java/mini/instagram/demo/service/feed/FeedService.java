package mini.instagram.demo.service.feed;

import mini.instagram.demo.dto.UserPrincipal;

import mini.instagram.demo.dto.feed.GetFeedResponse;

public interface FeedService {
    GetFeedResponse getFeed(UserPrincipal userPrincipal, int limit, int page);
}
