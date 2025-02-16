package mini.instagram.demo.dto.profile;

import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserRequest {
    @Positive
    private int profileId;
}
