package com.idfinance.cryptocurrencywatcher.service;

import com.idfinance.cryptocurrencywatcher.config.PredefinedCurrencies;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyResponse;
import com.idfinance.cryptocurrencywatcher.mapper.CryptoCurrencyMapper;
import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CryptoServiceScheduler {

    private static final String BASE_URL = "https://api.coinlore.net";
    public static final String API_URI_TEMPLATE = "/api/ticker/?id=%d";
    private static final String UPDATE_LOG_MESSAGE = "%s price updated";

    private final PredefinedCurrencies predefinedCurrencies;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 60000)
    public void updatePrices() {
        List<CryptoCurrencyInfoDto> currencies = predefinedCurrencies.getCurrencyInfoDtos();
        currencies.forEach(currency -> updatePrice(currency.getExternalId()));
    }

    private void updatePrice(Long externalId) {
        WebClient client = WebClient.create(BASE_URL);
        var typeReference = new ParameterizedTypeReference<List<CryptoCurrencyResponse>>() {
        };

        var result = client.get()
                .uri(String.format(API_URI_TEMPLATE, externalId))
                .retrieve()
                .bodyToMono(typeReference);

        result.subscribe(data -> saveCurrency(data.get(0)));
    }

    private void saveCurrency(CryptoCurrencyResponse response) {
        CryptoCurrency cryptoCurrency;

        if (cryptoCurrencyService.existsByExternalId(response.getExternalId())) {
            cryptoCurrency = cryptoCurrencyService.getBySymbol(response.getSymbol());
            cryptoCurrency.setPriceUsd(response.getPriceUsd());
            notificationService.notify(cryptoCurrency);
        } else {
            cryptoCurrency = cryptoCurrencyMapper.mapToEntity(response);
        }

        cryptoCurrencyService.save(cryptoCurrency);
        log.info(String.format(UPDATE_LOG_MESSAGE, cryptoCurrency.getSymbol()));
    }
}
