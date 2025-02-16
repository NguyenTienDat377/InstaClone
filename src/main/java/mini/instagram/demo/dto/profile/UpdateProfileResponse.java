package mini.instagram.demo.dto.profile;

import mini.instagram.demo.model.Profile;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileResponse {
    private Profile profile;
}
