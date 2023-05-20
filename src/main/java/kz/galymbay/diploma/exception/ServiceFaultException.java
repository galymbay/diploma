package kz.galymbay.diploma.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceFaultException extends RuntimeException {
    private String message;
    private HttpStatus status;
}
