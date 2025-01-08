package task.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import task.exceptions.GroupNotFoundException;

@RestControllerAdvice
public class GroupNotFoundAdvice {

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> exceptionGroupNotFoundHandler(GroupNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
