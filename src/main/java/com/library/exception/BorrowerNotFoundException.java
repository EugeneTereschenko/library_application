// src/main/java/com/library/exception/BorrowerNotFoundException.java
package com.library.exception;

public class BorrowerNotFoundException extends RuntimeException {
    public BorrowerNotFoundException(String message) {
        super(message);
    }
}