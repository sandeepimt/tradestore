package com.example.tradestore.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleCustomException(BusinessException be) {
        return new ResponseEntity<>(be.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleCustomException(Exception be) {
        return new ResponseEntity<>("Internal Error Occurred:: Please refer message for more details" + be.getMessage(), HttpStatus.BAD_REQUEST);
    }


}







