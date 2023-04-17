package com.idfinance.cryptocurrencywatcher.exceptions;

public class SubscriptionAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "The user %s has already subscribed" +
            " to %s price change notifications";

    public SubscriptionAlreadyExistsException(String username, String symbol) {
        super(String.format(MESSAGE_TEMPLATE, username, symbol));
    }
}
