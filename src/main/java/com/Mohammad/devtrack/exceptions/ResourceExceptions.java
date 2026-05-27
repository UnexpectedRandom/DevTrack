package com.Mohammad.devtrack.exceptions;

public class ResourceExceptions {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
