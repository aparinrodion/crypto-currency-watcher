package com.idfinance.cryptocurrencywatcher.service;

import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import com.idfinance.cryptocurrencywatcher.model.UserSubscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private static final String MESSAGE_TEMPLATE = "%s, price change of %s is %s percent";
    private final UserSubscriptionService userSubscriptionService;
    private final BigDecimal limit = BigDecimal.ONE;

    public void notify(CryptoCurrency newPrice) {
        List<UserSubscription> userSubscriptionServiceList =
                userSubscriptionService.getSubscriptionsByCurrencyId(newPrice.getId());

        userSubscriptionServiceList.forEach(userSubscription -> {
            BigDecimal percentageChange = getPricePercentageChange(
                    userSubscription.getSubscriptionPriceUsd(),
                    newPrice.getPriceUsd());

            if (percentageChange.abs().compareTo(limit) > 0) {
                log.warn(String.format(MESSAGE_TEMPLATE,
                        userSubscription.getUsername(),
                        newPrice.getSymbol(),
                        percentageChange.toPlainString()));
            }
        });
    }

    private BigDecimal getPricePercentageChange(BigDecimal subscriptionPrice, BigDecimal newPrice) {
        return newPrice.subtract(subscriptionPrice)
                .multiply(BigDecimal.valueOf(100.))
                .divide(subscriptionPrice, RoundingMode.UP);
    }
}