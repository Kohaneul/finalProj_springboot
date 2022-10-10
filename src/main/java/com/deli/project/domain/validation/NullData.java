package com.deli.project.domain.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xml.sax.ErrorHandler;

import javax.xml.validation.Validator;
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class NullData extends RuntimeException{

    public NullData() {
        super();
    }

    public NullData(String message) {
        super(message);
    }

    public NullData(String message, Throwable cause) {
        super(message, cause);
    }

    public NullData(Throwable cause) {
        super(cause);
    }

    protected NullData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
