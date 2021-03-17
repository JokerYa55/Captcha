package app.controller;

import app.exception.DataNotFoundException;
import app.exception.ExceptionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author vasil
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({DataNotFoundException.class})
    public final ResponseEntity<ExceptionData> handleDataNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity(((DataNotFoundException) ex).getData(), HttpStatus.NOT_FOUND);
    }
}
