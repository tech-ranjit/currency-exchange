package com.cpay.currency.exchange.controller;

import com.cpay.currency.exchange.model.Currency;
import com.cpay.currency.exchange.model.CurrencyExchangeDto;
import com.cpay.currency.exchange.service.ExchangeConversationService;
import com.cpay.currency.exchange.service.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    ExchangeConversationService exchangeConversationService;

    @Autowired
    ExchangeRateService exchangeRateService;


    @GetMapping(value = "currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeDto getCurrencyExchangeRate(@PathVariable String from,
                                                       @PathVariable String to) {
        return exchangeRateService.getCurrencyExchangeRate(from, to);
    }

    @GetMapping("currency-conversion/from/{from}/to/{to}/{quantity}")
    public Currency getConversionDetails(@PathVariable("from") String from,
                                         @PathVariable("to") String to,
                                         @PathVariable("quantity") BigDecimal quantity) {

        return exchangeConversationService.getConversionDetails(from, to, quantity);
    }
}
