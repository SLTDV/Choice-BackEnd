package com.select.choice.global.error.handler;

import com.select.choice.global.error.exception.GlobalExceptions;
import com.select.choice.global.error.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(GlobalExceptions.class)
    public ResponseEntity<ErrorResponse> GlobalExceptions(GlobalExceptions e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

}
