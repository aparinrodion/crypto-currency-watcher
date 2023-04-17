package com.idfinance.cryptocurrencywatcher.exceptions;

public class ApiResponseException extends RuntimeException{
    public ApiResponseException(Throwable cause) {
        super(cause);
    }
}
