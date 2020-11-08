package com.accounting.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//RESTful风格控制器增强
public class GlobalExceptionHandler {

    //DRY: Don't repeat yourself
    @ExceptionHandler(ServiceException.class)
    //@ExceptionHandler可以抓住controller层抛出的异常
    ResponseEntity<?> handleServiceException(ServiceException e) {
        val errorResponse = ErrorResponse.builder()
                .code(e.getErrorCode())
                .message(e.getMessage())
                .statuscode(e.getStatusCode())
                .errorType(e.getErrorType())
                .build();
        return ResponseEntity
                .status(e.getStatusCode() != 0
                        ?
                        e.getStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(errorResponse);
    }
}


