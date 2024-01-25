package com.cpay.currency.exchange.service;

import com.cpay.currency.exchange.model.Currency;

import java.math.BigDecimal;

public interface ExchangeConversationService {


    Currency getConversionDetails(String from,
                                  String to,
                                  BigDecimal quantity);

}
