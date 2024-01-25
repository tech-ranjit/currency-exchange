package com.cpay.currency.exchange.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Currency {

    private int id;

    private String from;

    private String to;

    private BigDecimal quantity;

    private BigDecimal exchangeRate;

    private BigDecimal totalCalcAmount;

    public Currency(CurrencyBuilder currencyBuilder) {
        this.from = currencyBuilder.from;
        this.to = currencyBuilder.to;
        this.quantity = currencyBuilder.quantity;
        this.exchangeRate = currencyBuilder.exchangeRate;
        this.totalCalcAmount = currencyBuilder.totalCalcAmount;
    }

    public static class CurrencyBuilder {

        private String from;

        private String to;

        private BigDecimal quantity;

        private BigDecimal exchangeRate;

        private BigDecimal totalCalcAmount;

        public static CurrencyBuilder newInstance() {
            return new CurrencyBuilder();
        }

        public CurrencyBuilder from(String from) {
            this.from = from;
            return this;
        }

        public CurrencyBuilder to(String to) {
            this.to = to;
            return this;
        }

        public CurrencyBuilder quantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public CurrencyBuilder exchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public CurrencyBuilder totalCalcAmount(BigDecimal totalCalcAmount) {
            this.totalCalcAmount = totalCalcAmount;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }

}
