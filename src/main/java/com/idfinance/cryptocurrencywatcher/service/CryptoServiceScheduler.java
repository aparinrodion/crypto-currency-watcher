package com.idfinance.cryptocurrencywatcher.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfinance.cryptocurrencywatcher.config.PredefinedCurrencies;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyResponse;
import com.idfinance.cryptocurrencywatcher.exceptions.ApiResponseMappingException;
import com.idfinance.cryptocurrencywatcher.mapper.CryptoCurrencyMapper;
import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CryptoServiceScheduler {

    private static final String API_URL_TEMPLATE = "https://api.coinlore.net/api/ticker/?id=%d";
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
        CryptoCurrencyResponse cryptoCurrencyResponse = getCurrencyFromApi(externalId);

        CryptoCurrency cryptoCurrency;

        if (cryptoCurrencyService.existsByExternalId(cryptoCurrencyResponse.getExternalId())) {
            cryptoCurrency = cryptoCurrencyService.getBySymbol(cryptoCurrencyResponse.getSymbol());
            cryptoCurrency.setPriceUsd(cryptoCurrencyResponse.getPriceUsd());
            notificationService.notify(cryptoCurrency);
        } else {
            cryptoCurrency = cryptoCurrencyMapper.mapToEntity(cryptoCurrencyResponse);
        }

        cryptoCurrencyService.save(cryptoCurrency);
        log.info(String.format(UPDATE_LOG_MESSAGE, cryptoCurrency.getSymbol()));
    }

    private CryptoCurrencyResponse getCurrencyFromApi(Long externalId) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(String.format(API_URL_TEMPLATE, externalId), String.class);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<CryptoCurrencyResponse> currencyResponses;

        //todo WebClient??

        try {
            currencyResponses = objectMapper.readValue(jsonResponse, new TypeReference<List<CryptoCurrencyResponse>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ApiResponseMappingException(e);
        }
        //TODO empty list??
        return currencyResponses.get(0);
    }
}
