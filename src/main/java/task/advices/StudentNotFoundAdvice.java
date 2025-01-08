package task.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.exceptions.StudentNotFoundException;

@RestControllerAdvice
public class StudentNotFoundAdvice {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> exceptionDuplicateKeyHandler(StudentNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
