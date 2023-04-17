package com.idfinance.cryptocurrencywatcher.service;

import com.idfinance.cryptocurrencywatcher.config.PredefinedCurrencies;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.exceptions.CurrencyNotFoundException;
import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import com.idfinance.cryptocurrencywatcher.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<CryptoCurrency> getAllAvailable() {
        return cryptoCurrencyRepository.findAll();
    }

    public CryptoCurrency save(CryptoCurrency cryptoCurrency) {
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }

    public CryptoCurrency getById(Long id) {
        return cryptoCurrencyRepository.findById(id)
                .orElseThrow(() -> new CurrencyNotFoundException(id));
    }

    public CryptoCurrency getBySymbol(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol)
                .orElseThrow(() -> new CurrencyNotFoundException(symbol));
    }

    public boolean existsByExternalId(Long externalId) {
        return cryptoCurrencyRepository.existsByExternalId(externalId);
    }
}
