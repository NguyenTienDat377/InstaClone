package mini.instagram.demo.service.feed;

import java.util.List;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.CreatePostRequest;
import mini.instagram.demo.model.Post;

public interface PostService {
    Post createPost(UserPrincipal userPrincipal, CreatePostRequest request);

    Post getPost(int postId);

    void deletePost(UserPrincipal userPrincipal, int postId);

    List<Post> getUserPosts(int profileId);
}
