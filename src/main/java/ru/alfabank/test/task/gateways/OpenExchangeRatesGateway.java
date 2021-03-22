package ru.alfabank.test.task.gateways;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.test.task.entities.OpenExchangeRatesResponse;

import java.util.List;

@FeignClient(
        url = "${openExchangeRates.basePath}",
        name = "openExchangeRatesGateway"
)
public interface OpenExchangeRatesGateway {

    @GetMapping(value = "${openExchangeRates.endpoint}")
    OpenExchangeRatesResponse getExchangeRates(
            @PathVariable("date") String date,
            @RequestParam("app_id") String appId,
//            @RequestParam("base") String base,
//            Закомментировано, так как в бесплатной версии OpenExchangeRatesAPI функционал недоступен.
            @RequestParam("symbols") List<String> symbols
    );

}
