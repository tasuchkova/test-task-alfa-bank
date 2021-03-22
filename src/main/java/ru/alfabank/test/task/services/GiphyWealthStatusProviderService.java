package ru.alfabank.test.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiphyWealthStatusProviderService {

    @Autowired
    private WealthStatusProviderService wealthStatusProviderService;
    @Autowired
    private GiphyProviderService giphyProviderService;

    public String getGiphyWealthStatus(String currencyCode) {
        boolean isRich = wealthStatusProviderService.isRich(currencyCode);
        return giphyProviderService.getGiphy(isRich);
    }
}
