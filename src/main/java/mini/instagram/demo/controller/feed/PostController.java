package mini.instagram.demo.controller.feed;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.CreatePostRequest;
import mini.instagram.demo.dto.feed.GetPostResponse;
import mini.instagram.demo.dto.feed.GetUserPostResponse;
import mini.instagram.demo.model.Post;
import mini.instagram.demo.service.feed.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(userPrincipal, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPostResponse> getPost(@PathVariable int id) {
        return ResponseEntity.ok(GetPostResponse.builder().post(postService.getPost(id)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable int id) {
        postService.deletePost(userPrincipal, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{profileId}")
    public ResponseEntity<GetUserPostResponse> getUserPosts(@PathVariable int profileId) {
        return ResponseEntity.ok(GetUserPostResponse.builder().posts(postService.getUserPosts(profileId)).build());
    }
}
