package com.Mohammad.devtrack.exceptions;

public class EmailExceptions {

    public static class EmailAlreadyTakenException extends RuntimeException {
        public EmailAlreadyTakenException(String message) {
            super(message);
        }
    }
}
