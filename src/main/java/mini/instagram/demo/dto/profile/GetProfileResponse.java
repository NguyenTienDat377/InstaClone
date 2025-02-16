package mini.instagram.demo.dto.profile;

import mini.instagram.demo.model.Profile;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProfileResponse {
    private Profile profile;
    private int numberOfPosts;
    private int numberOfFollowers;
    private int numberOfFollowing;
}
