package com.idfinance.cryptocurrencywatcher.mapper;

import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyDto;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyResponse;
import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CryptoCurrencyMapper {

    CryptoCurrencyInfoDto mapToInfoDto(CryptoCurrency cryptoCurrency);

    CryptoCurrencyDto mapToDto(CryptoCurrency cryptoCurrency);

    CryptoCurrency mapToEntity(CryptoCurrencyResponse cryptoCurrencyResponse);
}
