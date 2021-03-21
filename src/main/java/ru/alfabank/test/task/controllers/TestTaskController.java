package ru.alfabank.test.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfabank.test.task.services.GiphyProviderService;

@RestController
public class TestTaskController {

    @Autowired
    private GiphyProviderService giphyProviderService;

    @GetMapping(path = "/currency")
    public String getGiphy(@RequestParam("currency") String currencyCode) {
//        TODO:  сделать валидацию переданного кода валюты
        return giphyProviderService.getGiphy(currencyCode);
    }
}
