package mini.instagram.demo.service.feed;

import mini.instagram.demo.model.Post;
import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.CreateCommentRequest;

public interface CommentService {
    Post createComment(UserPrincipal userPrincipal, CreateCommentRequest request);

    Post deleteComment(UserPrincipal userPrincipal, int commentId);
}
