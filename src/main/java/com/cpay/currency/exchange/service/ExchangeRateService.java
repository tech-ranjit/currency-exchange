package com.cpay.currency.exchange.service;

import com.cpay.currency.exchange.model.CurrencyExchangeDto;

public interface ExchangeRateService {

    CurrencyExchangeDto getCurrencyExchangeRate(String from,
                                                String to);
}
