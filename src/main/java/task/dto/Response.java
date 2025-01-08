package task.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    String message;
    HttpStatus status;
}
