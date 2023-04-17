package com.idfinance.cryptocurrencywatcher.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "user_subscription")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CryptoCurrency cryptoCurrency;
    @Column(name = "subscription_price_usd")
    private BigDecimal subscriptionPriceUsd;
    private String username;
}
