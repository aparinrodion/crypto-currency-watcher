package com.idfinance.cryptocurrencywatcher.mapper;

import com.idfinance.cryptocurrencywatcher.dto.UserSubscriptionDto;
import com.idfinance.cryptocurrencywatcher.model.UserSubscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface UserSubscriptionMapper {

    @Mapping(target = "symbol", source = "cryptoCurrency.symbol")
    UserSubscriptionDto mapToDto(UserSubscription userSubscription);
}
