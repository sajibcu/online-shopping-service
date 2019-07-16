package com.red.code.onlineshopping.common.exceptions;


public class ActionNotPermittedException extends RuntimeException {

    public ActionNotPermittedException(String message) {
        super(message);
    }

    public ActionNotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotPermittedException(Throwable cause) {
        super(cause);
    }

    public ActionNotPermittedException() {
        super("This action is not permitted!");
    }
}