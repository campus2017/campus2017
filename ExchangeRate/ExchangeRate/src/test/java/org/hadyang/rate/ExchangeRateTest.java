package org.hadyang.rate;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class ExchangeRateTest {
    @Test
    public void getRate() throws Exception {
        ExchangeRate exchangeRate = new ExchangeRate();
        List<List<Rate>> rates = exchangeRate.getRate();
        rates.forEach(System.out::println);
    }

}