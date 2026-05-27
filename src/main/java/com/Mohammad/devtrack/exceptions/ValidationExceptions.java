package com.Mohammad.devtrack.exceptions;

public class ValidationExceptions {

    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
