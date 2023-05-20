package kz.galymbay.diploma.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceFaultException.class)
    public ResponseEntity<ExceptionResponse> handleServiceException(ServiceFaultException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(), ex.getStatus());
        return new ResponseEntity<>(response, ex.getStatus());
    }
}
