package com.idfinance.cryptocurrencywatcher.service;

import com.idfinance.cryptocurrencywatcher.exceptions.SubscriptionAlreadyExistsException;
import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import com.idfinance.cryptocurrencywatcher.model.UserSubscription;
import com.idfinance.cryptocurrencywatcher.repository.UserSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;
    private final CryptoCurrencyService cryptoCurrencyService;

    public List<UserSubscription> getSubscriptionsByCurrencyId(Long id) {
        return userSubscriptionRepository.findAllByCryptoCurrencyId(id);
    }

    public UserSubscription subscribe(String symbol, String username) {
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.getBySymbol(symbol);

        if (userSubscriptionRepository.existsByUsernameAndCryptoCurrencyId(username, cryptoCurrency.getId())) {
            throw new SubscriptionAlreadyExistsException(username, symbol);
        }

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUsername(username);
        userSubscription.setCryptoCurrency(cryptoCurrency);
        userSubscription.setSubscriptionPriceUsd(cryptoCurrency.getPriceUsd());
        return userSubscriptionRepository.save(userSubscription);
    }
}
