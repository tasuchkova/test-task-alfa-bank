package ru.alfabank.test.task.gateways;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        url = "${openExchangeRates.basePath}",
        path = "${openExchangeRates.endpoint}",
        name = "openExchangeRatesGateway"
)
public interface OpenExchangeRatesGateway {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    String getOpenExchangeRate(@RequestParam() String appId);

}
