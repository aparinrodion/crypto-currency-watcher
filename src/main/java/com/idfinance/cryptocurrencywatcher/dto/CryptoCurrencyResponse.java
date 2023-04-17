package com.idfinance.cryptocurrencywatcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CryptoCurrencyResponse {
    @JsonProperty("id")
    private Long externalId;
    private String name;
    private String symbol;
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;
}
