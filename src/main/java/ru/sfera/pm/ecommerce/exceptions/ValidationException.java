package ru.sfera.pm.ecommerce.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super( message );
    }

}
