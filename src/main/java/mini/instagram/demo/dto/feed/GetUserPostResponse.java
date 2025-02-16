package mini.instagram.demo.dto.feed;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import mini.instagram.demo.model.Post;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPostResponse {
    private List<Post> posts;
}
