package ru.alfabank.test.task.entities;


import java.util.Map;

public class OpenExchangeRatesResponse {

    private Map<String, String> rates;

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

}
