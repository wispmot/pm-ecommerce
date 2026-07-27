package ru.sfera.pm.ecommerce.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, Long id) {
        super(message + " (id=" + id + ")");
    }

}
