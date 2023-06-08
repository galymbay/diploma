package kz.galymbay.diploma.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleServiceException(Exception ex, Model model) {
        return "error";
    }
}
