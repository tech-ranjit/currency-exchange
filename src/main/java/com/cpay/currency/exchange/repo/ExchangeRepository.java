package com.cpay.currency.exchange.repo;

import com.cpay.currency.exchange.entity.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {

    Optional<CurrencyExchangeEntity> findByFromAndTo(String from, String to);

}
