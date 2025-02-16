package mini.instagram.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mini.instagram.demo.exception.PostNotFoundException;
import mini.instagram.demo.exception.UserNotFoundException;
import mini.instagram.demo.exception.CommentNotFoundException;
import mini.instagram.demo.exception.NoPermissionException;

import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String ERROR_CODE_INTERNAL = "INTERNAL_ERROR";
    private static final Map<Class<? extends RuntimeException>, HttpStatus> EXCEPTION_TO_HTTP_STATUS_CODE = Map.of(
        UserNotFoundException.class, HttpStatus.NOT_FOUND,
        PostNotFoundException.class, HttpStatus.NOT_FOUND,
        CommentNotFoundException.class, HttpStatus.NOT_FOUND,
        NoPermissionException.class, HttpStatus.FORBIDDEN
    );

    private static final Map<Class<? extends RuntimeException>, String> EXCEPTION_TO_ERROR_CODE = Map.of(
        UserNotFoundException.class, "USER_NOT_FOUND",
        PostNotFoundException.class, "POST_NOT_FOUND",
        CommentNotFoundException.class, "COMMENT_NOT_FOUND",
        NoPermissionException.class, "NO_PERMISSION"
    );

    @ExceptionHandler()
    ResponseEntity<ApiExceptionResponse> handleException(RuntimeException exception) {
        HttpStatus httpStatus = EXCEPTION_TO_HTTP_STATUS_CODE.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        String errorCode = EXCEPTION_TO_ERROR_CODE.getOrDefault(exception.getClass(), ERROR_CODE_INTERNAL);

        final ApiExceptionResponse response = ApiExceptionResponse.builder().status(httpStatus).errorCode(errorCode).build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}