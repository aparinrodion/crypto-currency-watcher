package com.idfinance.cryptocurrencywatcher.exceptions;

public class CurrencyNotFoundException extends RuntimeException {

    private static final String ID_MESSAGE_TEMPLATE = "Currency with id=%d not found";
    private static final String SYMBOL_MESSAGE_TEMPLATE = "Currency %s not found";

    public CurrencyNotFoundException(Long id) {
        super(String.format(ID_MESSAGE_TEMPLATE, id));
    }

    public CurrencyNotFoundException(String symbol) {
        super(String.format(SYMBOL_MESSAGE_TEMPLATE, symbol));
    }
}
