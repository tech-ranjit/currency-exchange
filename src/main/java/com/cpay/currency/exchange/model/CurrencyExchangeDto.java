package com.cpay.currency.exchange.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyExchangeDto {

    private String from;

    private String to;

    private BigDecimal exchangeRate;

}
