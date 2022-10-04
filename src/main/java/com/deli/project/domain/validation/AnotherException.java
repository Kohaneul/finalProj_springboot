package com.deli.project.domain.validation;

public class AnotherException extends RuntimeException{
    public AnotherException() {
        super();
    }

    public AnotherException(String message) {
        super(message);
    }

    public AnotherException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnotherException(Throwable cause) {
        super(cause);
    }

    protected AnotherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
