package com.gsantander.tesla.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TslTokenExpiredException extends TslServiceException {

    public TslTokenExpiredException() {
        super("");
    }

}
