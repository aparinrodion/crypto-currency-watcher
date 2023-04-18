package com.idfinance.cryptocurrencywatcher.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.exceptions.CurrencySourceFileException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Data
public class PredefinedCurrencies {

    private List<CryptoCurrencyInfoDto> currencyInfoDtos;

    public PredefinedCurrencies() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            currencyInfoDtos = objectMapper.readValue(
                    getClass().getClassLoader().getResource("currencies.json"),
                    new TypeReference<>() {
                    });
        } catch (IOException | IllegalArgumentException e) {
            throw new CurrencySourceFileException(e);
        }

    }
}
