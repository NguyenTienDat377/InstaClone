package mini.instagram.demo.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Autowired
    MinioClient minioClient;

    private String getFileExtension(String base64String) {
        String[] strings = base64String.split(",");
        String extensions;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extensions = "jpeg";
                break;
            case "data:image/png;base64":
                extensions = "png";
                break;
            default:
                extensions = "jpg";
                break;
        }
        return extensions;
    }

    private InputStream getImageFromBase64(String base64String) {
        String[] strings = base64String.split(",");

        byte[] data = Base64.getDecoder().decode(strings[1]);
        return new ByteArrayInputStream(data);
    }

    @Override
    public String uploadImage(String base64) {
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), this.getFileExtension(base64));
        try {
            minioClient.putObject(PutObjectArgs.builder().bucket("string-boot").object(fileName).stream(this.getImageFromBase64(base64), -1, 5242880).build());
        } catch (Exception e) {
            log.error("Error uploading image to minio", e);
            return null;
        }
        return fileName;
    }

}
