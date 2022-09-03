package com.example.presidents.exception;

import com.example.presidents.exception.exceptions.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
    return new ResponseEntity<>(getBody(HttpStatus.NOT_FOUND, errors.toString()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex) {
      return new ResponseEntity<>(getBody(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }



  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    String message = nonNull(ex.getMessage()) ? ex.getMessage().split(":")[0] : ex.getMessage();
    return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, message),
            HttpStatus.BAD_REQUEST);
  }


  private Map<Object, Object> getBody(HttpStatus status, String message) {
    Map<Object, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now());
    body.put("status", status.value());
    body.put("error", status);
    body.put("message", message);
    return body;
  }
}
