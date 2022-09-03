package com.example.presidents.exception;

import com.example.presidents.exception.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {


  @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex) {
      return new ResponseEntity<>(getBody(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public final ResponseEntity<Object> handleHttpMessageNotReadableException(Exception ex) {
    return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, ex.getMessage().split(":")[0]), HttpStatus.BAD_REQUEST);
  }

  private Map<Object, Object> getBody(HttpStatus status, String message) {
    Map<Object, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now());
    body.put("code", status.value());
    body.put("message", message);
    return body;
  }
}
