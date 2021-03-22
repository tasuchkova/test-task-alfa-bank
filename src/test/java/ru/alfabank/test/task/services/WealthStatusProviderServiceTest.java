package ru.alfabank.test.task.services;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfabank.test.task.entities.OpenExchangeRatesResponse;
import ru.alfabank.test.task.gateways.OpenExchangeRatesGateway;

import java.util.List;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class WealthStatusProviderServiceTest {

    private static final String CURRENCY_CODE = "EUR";
    private static final String EXCHANGE_RATE = "111.863334";
    private static final String HIGHER_EXCHANGE_RATE = "115.863334";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WealthStatusProviderService wealthStatusProviderService;

    @MockBean
    private OpenExchangeRatesGateway openExchangeRatesGateway;

    @Test
    public void shouldCallOpenExchangeRatesGatewayTwice() {
        given(this.openExchangeRatesGateway.getExchangeRates(anyString(), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        wealthStatusProviderService.isRich(CURRENCY_CODE);
        verify(openExchangeRatesGateway, times(2)).getExchangeRates(anyString(), anyString(), eq(List.of(CURRENCY_CODE)));
    }

    @Test
    public void shouldCallOpenExchangeRatesGatewayToGetTodayExchangeRates() {
        given(this.openExchangeRatesGateway.getExchangeRates(anyString(), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        wealthStatusProviderService.isRich(CURRENCY_CODE);
        verify(openExchangeRatesGateway, times(1)).getExchangeRates(eq(getTodayDate()), anyString(), eq(List.of(CURRENCY_CODE)));
    }

    @Test
    public void shouldCallOpenExchangeRatesGatewayToGetYesterdayExchangeRates() {
        given(this.openExchangeRatesGateway.getExchangeRates(anyString(), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        wealthStatusProviderService.isRich(CURRENCY_CODE);
        verify(openExchangeRatesGateway, times(1)).getExchangeRates(eq(getYesterdayDate()), anyString(), eq(List.of(CURRENCY_CODE)));
    }

    @Test
    public void shouldNotReturnRichStatusIfRatesRemainedTheSame() {
        given(this.openExchangeRatesGateway.getExchangeRates(anyString(), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        assertFalse(wealthStatusProviderService.isRich(CURRENCY_CODE));
    }

    @Test
    public void shouldNotReturnRichStatusIfRatesDecreased() {
        given(this.openExchangeRatesGateway.getExchangeRates(eq(getYesterdayDate()), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse(HIGHER_EXCHANGE_RATE));
        given(this.openExchangeRatesGateway.getExchangeRates(eq(getTodayDate()), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        assertFalse(wealthStatusProviderService.isRich(CURRENCY_CODE));
    }

    @Test
    public void shouldReturnRichStatusIfRatesIncreased() {
        given(this.openExchangeRatesGateway.getExchangeRates(eq(getYesterdayDate()), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse());
        given(this.openExchangeRatesGateway.getExchangeRates(eq(getTodayDate()), anyString(), eq(List.of(CURRENCY_CODE))))
                .willReturn(getOpenExchangeRatesResponse(HIGHER_EXCHANGE_RATE));
        assertTrue(wealthStatusProviderService.isRich(CURRENCY_CODE));
    }

    private String getYesterdayDate() {
        return now().minusDays(1)
                    .toString();
    }

    private OpenExchangeRatesResponse getOpenExchangeRatesResponse() {
        return getOpenExchangeRatesResponse(EXCHANGE_RATE);
    }

    private OpenExchangeRatesResponse getOpenExchangeRatesResponse(String exchangeRate) {
        OpenExchangeRatesResponse openExchangeRatesResponse = new OpenExchangeRatesResponse();
        openExchangeRatesResponse.setRates(ImmutableMap.of(CURRENCY_CODE, exchangeRate));
        return openExchangeRatesResponse;
    }

    private String getTodayDate() {
        return now().toString();
    }
}