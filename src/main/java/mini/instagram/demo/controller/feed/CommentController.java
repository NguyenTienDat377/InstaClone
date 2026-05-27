package mini.instagram.demo.controller.feed;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.CreateCommentRequest;
import mini.instagram.demo.model.Post;
import mini.instagram.demo.service.feed.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Post> createComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(userPrincipal, request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Post> deleteComment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable int commentId) {
        return ResponseEntity.ok(commentService.deleteComment(userPrincipal, commentId));
    }
}
