package springrest.tennisplayerrest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class PlayerExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<PlayerErrorResponse> playerNotFoundHandler(PlayerNotFoundException exception, HttpServletRequest request) {
        PlayerErrorResponse error = new PlayerErrorResponse(ZonedDateTime.now(), HttpStatus.NOT_FOUND.value(), request.getRequestURI(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> genericHandler(Exception exception, HttpServletRequest request) {
        PlayerErrorResponse error = new PlayerErrorResponse(ZonedDateTime.now(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
