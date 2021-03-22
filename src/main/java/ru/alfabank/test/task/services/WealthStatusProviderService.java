package ru.alfabank.test.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alfabank.test.task.entities.OpenExchangeRatesResponse;
import ru.alfabank.test.task.gateways.OpenExchangeRatesGateway;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

@Component
public class WealthStatusProviderService {

    @Value("${openExchangeRates.appId}")
    private String openExchangeRatesGatewayAppId;

//    @Value("${openExchangeRates.baseCurrency}")
//    private String getOpenExchangeRatesBaseCurrency;
//    Закомментировано, так как в бесплатной версии OpenExchangeRatesAPI функционал недоступен.

    @Autowired
    private OpenExchangeRatesGateway openExchangeRatesGateway;

    public boolean isRich(String currencyCode) {
        Double todayExchangeRate = getExchangeRate(currencyCode, now());
        Double yesterdayExchangeRate = getExchangeRate(currencyCode, now().minusDays(1));
        return todayExchangeRate > yesterdayExchangeRate;
    }

    private Double getExchangeRate(String currencyCode, LocalDate date) {
        OpenExchangeRatesResponse exchangeRates = openExchangeRatesGateway.getExchangeRates(
                date.toString(),
                openExchangeRatesGatewayAppId,
//                getOpenExchangeRatesBaseCurrency,
//    Закомментировано, так как в бесплатной версии OpenExchangeRatesAPI функционал недоступен.
                List.of(currencyCode)
        );
        return Double.parseDouble(exchangeRates.getRates()
                                               .get(currencyCode));
    }

}
