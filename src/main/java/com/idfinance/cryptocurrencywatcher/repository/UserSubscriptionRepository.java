package com.idfinance.cryptocurrencywatcher.repository;

import com.idfinance.cryptocurrencywatcher.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

    List<UserSubscription> findAllByCryptoCurrencyId(Long id);

    boolean existsByUsernameAndCryptoCurrencyId(String username, Long id);
}
