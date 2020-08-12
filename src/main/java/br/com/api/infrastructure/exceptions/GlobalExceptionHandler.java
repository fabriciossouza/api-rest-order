package br.com.api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ResponseBody
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResource> handleNotFoundException(final EntityNotFoundException exception) {
        return this.buildErrorResource(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResource> handleBusinessException(final BusinessException exception) {
        return this.buildErrorResource(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResource> validationException(ValidationException exception){
        return this.buildErrorResource(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResource> buildErrorResource(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorResource(message), httpStatus);
    }

}
