package com.idfinance.cryptocurrencywatcher.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserSubscriptionDto {

    private Long id;
    private String symbol;
    private String username;
    private BigDecimal subscriptionPriceUsd;
}
