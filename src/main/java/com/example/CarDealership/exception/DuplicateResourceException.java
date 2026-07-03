package com.example.CarDealership.exception;

/**
 * Thrown when creating an entity that would violate a uniqueness rule. Mapped to
 * HTTP 409 by {@link GlobalExceptionHandler}.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
