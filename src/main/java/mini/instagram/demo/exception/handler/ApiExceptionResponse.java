package mini.instagram.demo.exception.handler;

import lombok.Data;
import lombok.Builder;

import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiExceptionResponse {
    private HttpStatus status;
    private String errorCode;
}
