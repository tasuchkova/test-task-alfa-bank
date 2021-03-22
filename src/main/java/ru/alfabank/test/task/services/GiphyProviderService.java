package ru.alfabank.test.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alfabank.test.task.gateways.GiphyGateway;

@Component
public class GiphyProviderService {

    @Value("${giphy.api_key}")
    private String giphyGatewayAppId;

    @Autowired
    private GiphyGateway giphyGateway;

    public String getGiphy(boolean isRich) {
        String tag = isRich ? "rich" : "broke";
        return giphyGateway.gitGiphy(giphyGatewayAppId, tag).getData().getEmbedUrl();
    }
}
