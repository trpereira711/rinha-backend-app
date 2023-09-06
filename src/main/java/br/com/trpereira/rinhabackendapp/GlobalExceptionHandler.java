package br.com.trpereira.rinhabackendapp;

import br.com.trpereira.rinhabackendapp.interfaces.validation.ContentException;
import br.com.trpereira.rinhabackendapp.interfaces.validation.SyntaxException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String INVALID_REQUEST = "invalid request";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(INVALID_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(INVALID_REQUEST, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ContentException.class)
    public ResponseEntity<String> handleException(ContentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SyntaxException.class)
    public ResponseEntity<String> handleException(SyntaxException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


