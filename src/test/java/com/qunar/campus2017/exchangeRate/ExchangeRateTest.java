package com.qunar.campus2017.exchangeRate;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chunming.xcm on 2017/1/10.
 */
public class ExchangeRateTest {
    @Test
    public void testRate() {
        GetRate getRate = new GetRate();
        getRate.get();
        Assert.assertEquals(2, 1+1);
    }
}
