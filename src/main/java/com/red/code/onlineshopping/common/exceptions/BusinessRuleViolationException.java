package com.red.code.onlineshopping.common.exceptions;


public class BusinessRuleViolationException extends RuntimeException {

    public BusinessRuleViolationException(String message) {
        super(message);
    }

    public BusinessRuleViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuleViolationException(Throwable cause) {
        super(cause);
    }

    public BusinessRuleViolationException() {
        super("This action violates business rule!");
    }
}