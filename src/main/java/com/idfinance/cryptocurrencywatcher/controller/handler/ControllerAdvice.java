package com.idfinance.cryptocurrencywatcher.controller.handler;

import com.idfinance.cryptocurrencywatcher.dto.ExceptionDto;
import com.idfinance.cryptocurrencywatcher.exceptions.CurrencyNotFoundException;
import com.idfinance.cryptocurrencywatcher.exceptions.SubscriptionAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleCurrencyNotFoundException(CurrencyNotFoundException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(SubscriptionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleSubscriptionAlreadyExistsException(SubscriptionAlreadyExistsException exception) {
        return new ExceptionDto(exception.getMessage());
    }
}
