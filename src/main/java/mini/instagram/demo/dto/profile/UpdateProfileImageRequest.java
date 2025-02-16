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
public class UpdateProfileImageRequest {
    @Length(min = 1)
    private String base64ImageString;
}
