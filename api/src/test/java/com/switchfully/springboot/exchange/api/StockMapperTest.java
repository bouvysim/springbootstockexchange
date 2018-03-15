package com.switchfully.springboot.exchange.api;

import com.switchfully.springboot.exchange.domain.Stock;
import com.switchfully.springboot.exchange.domain.StockPrice;
import org.junit.Test;

import java.math.BigDecimal;

import static com.switchfully.springboot.exchange.domain.StockCurrency.EUR;
import static org.assertj.core.api.Assertions.assertThat;

public class StockMapperTest {

    @Test
    public void mapToDto_givenEnrichedStock_thenMapAllDataToStockDto()  {
        Stock stock = new Stock("ABC", "AyBeCe");
        stock.setPrice(new StockPrice(new BigDecimal(10), EUR));

        StockMapper stockMapper = new StockMapper();
        StockDto stockDto = stockMapper.mapToDto(stock);

        assertThat(stockDto.getId()).isEqualTo("ABC");
        assertThat(stockDto.getName()).isEqualTo("AyBeCe");
        assertThat(stockDto.getPrice()).isEqualTo(new BigDecimal(10));
        assertThat(stockDto.getCurrency()).isEqualTo(EUR.getLabel());
    }

    @Test
    public void mapToDto_givenUnknownStockWithoutPrice_thenMapIdAndNameToStockDto()  {
        Stock stock = new Stock("ABC", "AyBeCe");

        StockMapper stockMapper = new StockMapper();
        StockDto stockDto = stockMapper.mapToDto(stock);

        assertThat(stockDto.getId()).isEqualTo("ABC");
        assertThat(stockDto.getName()).isEqualTo("AyBeCe");
        assertThat(stockDto.getPrice()).isEqualTo(new BigDecimal(0));
        assertThat(stockDto.getCurrency()).isEqualTo("Unknown");
    }

}