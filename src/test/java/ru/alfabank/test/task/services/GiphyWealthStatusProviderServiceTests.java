package ru.alfabank.test.task.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class GiphyWealthStatusProviderServiceTests {

    private static final String CURRENCY_CODE = "EUR";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GiphyWealthStatusProviderService giphyWealthStatusProviderService;

    @MockBean
    private GiphyProviderService giphyProviderService;

    @MockBean
    private WealthStatusProviderService wealthStatusProviderService;

    @Test
    public void shouldCallGiphyProviderServiceWithRichStatus() {
        given(this.wealthStatusProviderService.isRich(CURRENCY_CODE)).willReturn(true);
        giphyWealthStatusProviderService.getGiphyWealthStatus(CURRENCY_CODE);
        verify(giphyProviderService, times(1)).getGiphy(eq(true));
    }

    @Test
    public void shouldCallGiphyProviderServiceWithBrokeStatus() {
        given(this.wealthStatusProviderService.isRich(CURRENCY_CODE)).willReturn(false);
        giphyWealthStatusProviderService.getGiphyWealthStatus(CURRENCY_CODE);
        verify(giphyProviderService, times(1)).getGiphy(eq(false));
    }

}
