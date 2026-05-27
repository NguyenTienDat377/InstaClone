package mini.instagram.demo.dto.profile;

import mini.instagram.demo.model.Profile;

import java.util.List;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFollowerResponse {
    private List<Profile> profiles;
    private int totalPages;
}
