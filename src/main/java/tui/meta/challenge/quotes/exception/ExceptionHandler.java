package tui.meta.challenge.quotes.exception;

import java.time.Instant;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(NotFoundException ex, WebRequest request) {
    Map<String, Object> body = Map.of(
        "timestamp", Instant.now().toString(),
        "status", HttpStatus.NOT_FOUND.value(),
        "error", "Not Found",
        "message", ex.getMessage(),
        "path", request.getDescription(false).replace("uri=", "")
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGeneric(Exception ex, WebRequest request) {
    Map<String, Object> body = Map.of(
        "timestamp", Instant.now().toString(),
        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "error", "Internal Server Error",
        "message", ex.getMessage(),
        "path", request.getDescription(false).replace("uri=", "")
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

}
