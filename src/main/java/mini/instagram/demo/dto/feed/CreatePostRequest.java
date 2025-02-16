package mini.instagram.demo.dto.feed;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    @Length(min = 1)
    private String image;

    @Length(min = 1, max = 2000)
    private String caption;
}
