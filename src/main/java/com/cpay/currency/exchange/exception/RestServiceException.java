package com.cpay.currency.exchange.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
public class RestServiceException extends RuntimeException {
    private String serviceName;
    private HttpStatus statusCode;
    private String error;
}
