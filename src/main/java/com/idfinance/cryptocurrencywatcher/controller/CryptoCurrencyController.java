package com.idfinance.cryptocurrencywatcher.controller;

import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyDto;
import com.idfinance.cryptocurrencywatcher.dto.CryptoCurrencyInfoDto;
import com.idfinance.cryptocurrencywatcher.dto.UserSubscriptionDto;
import com.idfinance.cryptocurrencywatcher.mapper.CryptoCurrencyMapper;
import com.idfinance.cryptocurrencywatcher.mapper.UserSubscriptionMapper;
import com.idfinance.cryptocurrencywatcher.model.UserSubscription;
import com.idfinance.cryptocurrencywatcher.service.CryptoCurrencyService;
import com.idfinance.cryptocurrencywatcher.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto-currency-watcher")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;
    private final UserSubscriptionMapper userSubscriptionMapper;
    private final UserSubscriptionService userSubscriptionService;

    @GetMapping("/available")
    public List<CryptoCurrencyInfoDto> getAll() {
        return cryptoCurrencyService.getAllAvailable().stream()
                .map(cryptoCurrencyMapper::mapToInfoDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CryptoCurrencyDto getById(@PathVariable Long id) {
        return cryptoCurrencyMapper.mapToDto(cryptoCurrencyService.getById(id));
    }

    @PostMapping("/subscribe")
    public UserSubscriptionDto notify(@RequestBody UserSubscriptionDto userSubscriptionDto) {
        UserSubscription userSubscription = userSubscriptionService.subscribe(userSubscriptionDto.getSymbol(),
                userSubscriptionDto.getUsername());
        return userSubscriptionMapper.mapToDto(userSubscription);
    }
}
