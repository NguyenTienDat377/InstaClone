package mini.instagram.demo.dto;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

@Data
public class UploadImageRequest {
    @Length(min = 1)
    private String base64ImagesString; 
}
