package com.hclhackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerAlreadyExistsException() {
        super("Customer already exists");
    }

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    // Convenience factory helpers
    public static CustomerAlreadyExistsException forEmail(String email) {
        return new CustomerAlreadyExistsException("Customer already exists with email: " + email);
    }

    public static CustomerAlreadyExistsException forId(Long id) {
        return new CustomerAlreadyExistsException("Customer already exists with id: " + id);
    }
}

