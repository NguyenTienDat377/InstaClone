package mini.instagram.demo.dto.feed;

import lombok.Data;
import lombok.Builder;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    private int postId;
    @Length(min = 1, max = 200)
    private String content;
}
