package com.library.exception;

public class BorrowerAlreadyExitException extends RuntimeException {
    public BorrowerAlreadyExitException(String message) {
        super(message);
    }
}
