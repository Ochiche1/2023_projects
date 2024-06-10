package com.orbit.transaction.users.exceptions;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    private Integer httpCode;
    private String timestamp;

    public ServiceException(Integer httpCode, String message, String timestamp) {
        super(message);
        this.httpCode = httpCode;
        this.timestamp = timestamp;
    }
    public ServiceException(String message){
        super();
    }
}
