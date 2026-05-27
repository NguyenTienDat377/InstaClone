package mini.instagram.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini.instagram.demo.dto.UploadImageRequest;
import mini.instagram.demo.dto.UploadImageResponse;
import mini.instagram.demo.service.UploadService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping
    public ResponseEntity<UploadImageResponse> uploadImage(@Valid @RequestBody UploadImageRequest request) {
        String url = uploadService.uploadImage(request.getBase64ImagesString());
        return ResponseEntity.ok(UploadImageResponse.builder().url(url).build());
    }
}
