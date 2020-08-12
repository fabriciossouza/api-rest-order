package br.com.api.infrastructure.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
