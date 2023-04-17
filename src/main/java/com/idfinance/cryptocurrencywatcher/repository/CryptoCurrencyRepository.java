package com.idfinance.cryptocurrencywatcher.repository;

import com.idfinance.cryptocurrencywatcher.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Optional<CryptoCurrency> findBySymbol(String symbol);

    boolean existsByExternalId(Long externalId);
}
