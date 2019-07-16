package com.red.code.onlineshopping.common.exceptions;


public class EntityNotEnabledException extends RuntimeException {

    public EntityNotEnabledException(String message) {
        super(message);
    }

    public EntityNotEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotEnabledException(Throwable cause) {
        super(cause);
    }

    public EntityNotEnabledException() {
        super("This entity is not enabled!");
    }
}