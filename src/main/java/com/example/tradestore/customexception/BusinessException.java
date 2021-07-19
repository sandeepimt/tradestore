package com.example.tradestore.customexception;

import org.springframework.stereotype.Component;

@Component
public class BusinessException extends RuntimeException {

    private String errorMessage;

    public BusinessException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public BusinessException() {

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
