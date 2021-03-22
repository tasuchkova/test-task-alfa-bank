package ru.alfabank.test.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.test.task.services.GiphyWealthStatusProviderService;

@RestController
public class TestTaskController {

    @Autowired
    private GiphyWealthStatusProviderService giphyStatusProviderService;

    @GetMapping(path = "/currency")
    public String getGiphyAccordingToExchangeRates(@RequestParam("currency") String currencyCode) {
        return giphyStatusProviderService.getGiphyWealthStatus(currencyCode);
    }

    @GetMapping()
    public String getHealthCheck() {
        return "Hello, world! I am OK!";
    }
}
