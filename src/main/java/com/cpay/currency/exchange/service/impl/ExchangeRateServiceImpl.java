package com.cpay.currency.exchange.service.impl;

import com.cpay.currency.exchange.constants.CurrencyConstants;
import com.cpay.currency.exchange.entity.CurrencyExchangeEntity;
import com.cpay.currency.exchange.exception.RateNotFoundException;
import com.cpay.currency.exchange.model.CurrencyExchangeDto;
import com.cpay.currency.exchange.repo.ExchangeRepository;
import com.cpay.currency.exchange.service.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final Logger LOG = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Autowired
    ExchangeRepository exchangeRepository;

    @Override
    public CurrencyExchangeDto getCurrencyExchangeRate(String from, String to) {
        LOG.info("Fetch currency exchange rate for " + from + " to " + to);

        Optional<CurrencyExchangeEntity> currencyExchangeRate = exchangeRepository.findByFromAndTo(from, to);

        if (currencyExchangeRate.isEmpty()) {
            throw new RateNotFoundException(CurrencyConstants.EXCHANGE_RATE_NOT_FOUND);
        }

        LOG.debug("Conversation details fetched successfully.");
        return mapEntityToDto(currencyExchangeRate.get());
    }


    private CurrencyExchangeDto mapEntityToDto(CurrencyExchangeEntity exchangeEntity) {

        CurrencyExchangeDto exchangeDto = new CurrencyExchangeDto();
        exchangeDto.setFrom(exchangeEntity.getFrom());
        exchangeDto.setTo(exchangeEntity.getTo());
        exchangeDto.setExchangeRate(exchangeEntity.getExchangeRate());
        return exchangeDto;
    }

}
