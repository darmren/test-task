package task.advices;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DuplicateKeyExceptionAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> exceptionDuplicateKeyHandler(){
        return ResponseEntity.badRequest().body("This student has already been saved");
    }
}
