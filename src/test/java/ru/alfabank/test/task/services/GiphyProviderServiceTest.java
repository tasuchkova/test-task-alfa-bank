package ru.alfabank.test.task.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.test.task.entities.Data;
import ru.alfabank.test.task.entities.GiphyResponse;
import ru.alfabank.test.task.gateways.GiphyGateway;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class GiphyProviderServiceTest {

    private static final String RICH_TAG = "rich";
    private static final String BROKE_TAG = "broke";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GiphyProviderService giphyProviderService;

    @MockBean
    private GiphyGateway giphyGateway;

    @Test
    public void shouldCallGiphyGatewayWithRichTag() {
        given(this.giphyGateway.gitGiphy(anyString(), anyString())).willReturn(getGiphyResponse());
        giphyProviderService.getGiphy(true);
        verify(giphyGateway, times(1)).gitGiphy(anyString(), ArgumentMatchers.eq(RICH_TAG));
    }

    @Test
    public void shouldCallGiphyGatewayWithBrokeTag() {
        given(this.giphyGateway.gitGiphy(anyString(), anyString())).willReturn(getGiphyResponse());
        giphyProviderService.getGiphy(false);
        verify(giphyGateway, times(1)).gitGiphy(anyString(), ArgumentMatchers.eq(BROKE_TAG));
    }

    private GiphyResponse getGiphyResponse() {
        GiphyResponse giphyResponse = new GiphyResponse();
        giphyResponse.setData(getData());
        return giphyResponse;
    }

    private Data getData() {
        Data data = new Data();
        data.setEmbedUrl("https://giphy.com/embed/ljlkjlijlkj876ou");
        return data;
    }

}