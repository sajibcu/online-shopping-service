package com.red.code.onlineshopping.common.exceptions;

public class ActionNotImplementedException extends RuntimeException {

    public ActionNotImplementedException(String message) {
        super(message);
    }

    public ActionNotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotImplementedException(Throwable cause) {
        super(cause);
    }

    public ActionNotImplementedException() {
        super("This action is not implemented!");
    }
}