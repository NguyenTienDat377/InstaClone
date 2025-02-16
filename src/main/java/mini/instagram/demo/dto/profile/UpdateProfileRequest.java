package mini.instagram.demo.dto.profile;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    @Length(min = 1, max = 100)
    private String displayName;

    @Length(min = 1, max = 100)
    private String username;

    @Length(min = 1, max = 100)
    private String bio;
}
