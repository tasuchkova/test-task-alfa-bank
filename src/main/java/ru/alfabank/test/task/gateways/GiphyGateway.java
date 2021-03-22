package ru.alfabank.test.task.gateways;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.test.task.entities.GiphyResponse;


@FeignClient(
        url = "${giphy.endpoint}",
        name = "giphyGateway"
)
public interface GiphyGateway {

    @GetMapping()
    GiphyResponse gitGiphy(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}
