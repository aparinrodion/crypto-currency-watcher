package com.idfinance.cryptocurrencywatcher.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CryptoCurrencyDto {

    private Long id;
    private Long externalId;
    private String name;
    private String symbol;
    private BigDecimal priceUsd;
    private LocalDateTime updatedAt;
}
