package com.gsantander.tesla.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class TslFoundException extends TslServiceException {

    public TslFoundException() {
        super("");
    }

}
