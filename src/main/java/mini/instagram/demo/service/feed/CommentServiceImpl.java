package mini.instagram.demo.service.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.repository.CommentRepository;
import mini.instagram.demo.repository.PostRepository;
import mini.instagram.demo.repository.ProfileRepository;
import mini.instagram.demo.service.profile.ProfileService;
import mini.instagram.demo.dto.feed.CreateCommentRequest;
import mini.instagram.demo.model.Comment;
import mini.instagram.demo.model.Post;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.exception.CommentNotFoundException;
import mini.instagram.demo.exception.NoPermissionException;
import mini.instagram.demo.exception.PostNotFoundException;
import mini.instagram.demo.repository.CommentRepository;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileService profileService;
    
    //@Override
    //public Post createComment(UserPrincipal userPrincipal, CreateCommentRequest request) {
    //    Profile profile = profileRepository
    //}
}
