package learn.field_agent.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, this update cannot be made due to an integrity constraint."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
