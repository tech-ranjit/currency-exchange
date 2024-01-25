package com.cpay.currency.exchange.service.impl;

import com.cpay.currency.exchange.constants.CurrencyConstants;
import com.cpay.currency.exchange.exception.RestServiceException;
import com.cpay.currency.exchange.model.Currency;
import com.cpay.currency.exchange.service.ExchangeConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
@ConfigurationProperties(prefix = "currency.exchange")
public class ExchangeConversationServiceImpl implements ExchangeConversationService {

    private final Logger LOG = LoggerFactory.getLogger(ExchangeConversationServiceImpl.class);

    @Value("${currency.exchange.url}")
    private String exchangeUrl;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public Currency getConversionDetails(String from, String to, BigDecimal quantity) {
        LOG.info("Fetch conversion details for " + from + " to " + to + " and quantity : " + quantity);
        Currency currencyResponse = getCurrencyExchangeRateDetails(from, to);

        Currency currency = new Currency.CurrencyBuilder()
                .from(currencyResponse.getFrom())
                .to(currencyResponse.getTo())
                .quantity(quantity)
                .exchangeRate(currencyResponse.getExchangeRate())
                .totalCalcAmount(quantity.multiply(currencyResponse.getExchangeRate()))
                .build();

        LOG.debug("Conversation details fetched successfully.");
        return currency;
    }

    public Currency getCurrencyExchangeRateDetails(String from,
                                                   String to) {

        ResponseEntity<Currency> responseEntity;
        try {
            Map<String, String> uriVariables = prepareUriVariablesForExchangeRate(from, to);
            responseEntity = new RestTemplate().getForEntity("http://localhost:20100/CurrencyExchange/currency-exchange/from/{from}/to/{to}",
                    Currency.class,
                    uriVariables);
        } catch (RestClientException ex) {
            throw new RestServiceException(CurrencyConstants.SERVICE_NAME,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CurrencyConstants.CURRENCY_EXCHANGE_ERROR_MESSAGE
            );
        }

        return responseEntity.getBody();
    }

    private Map<String, String> prepareUriVariablesForExchangeRate(String from, String to) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        return uriVariables;
    }

}
