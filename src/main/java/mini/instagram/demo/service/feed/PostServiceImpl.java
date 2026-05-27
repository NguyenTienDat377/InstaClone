package mini.instagram.demo.service.feed;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini.instagram.demo.dto.UserPrincipal;
import mini.instagram.demo.dto.feed.CreatePostRequest;
import mini.instagram.demo.exception.NoPermissionException;
import mini.instagram.demo.exception.PostNotFoundException;
import mini.instagram.demo.model.Post;
import mini.instagram.demo.model.Profile;
import mini.instagram.demo.repository.PostRepository;
import mini.instagram.demo.service.UploadService;
import mini.instagram.demo.service.profile.ProfileService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UploadService uploadService;

    @Override
    public Post createPost(UserPrincipal userPrincipal, CreatePostRequest request) {
        Profile profile = profileService.getUserProfile(userPrincipal);
        String imageUrl = uploadService.uploadImage(request.getImage());
        Post post = Post.builder()
            .createdBy(profile)
            .imageUrl(imageUrl)
            .caption(request.getCaption())
            .createdAt(new Date())
            .build();
        return postRepository.save(post);
    }

    @Override
    public Post getPost(int postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public void deletePost(UserPrincipal userPrincipal, int postId) {
        Profile profile = profileService.getUserProfile(userPrincipal);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        if (post.getCreatedBy().getId() != profile.getId()) {
            throw new NoPermissionException();
        }
        postRepository.delete(post);
    }

    @Override
    public List<Post> getUserPosts(int profileId) {
        Profile profile = profileService.getUserProfile(profileId);
        return postRepository.findByCreatedBy(profile);
    }
}
