package mini.instagram.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2UserInfoDto {
    private String name;
    private String email;
    private String picture;
    private String id;
}
