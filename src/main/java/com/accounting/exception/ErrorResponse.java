package com.accounting.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String code;
    private ServiceException.ErrorType errorType;
    private String message;
    private int statuscode;
}

