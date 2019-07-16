package com.red.code.onlineshopping.common.exceptions;


public class UniqueIdTakenException extends RuntimeException {

    public UniqueIdTakenException(String message) {
        super(message);
    }

    public UniqueIdTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueIdTakenException(Throwable cause) {
        super(cause);
    }

    public UniqueIdTakenException() {
        super("Unique id already taken!");
    }
}
