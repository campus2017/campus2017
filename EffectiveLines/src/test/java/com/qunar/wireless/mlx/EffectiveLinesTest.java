package com.qunar.wireless.mlx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mlx on 2016-12-29.
 */
public class EffectiveLinesTest {
    @Test
    public void getLinesNum() throws Exception {
        assertEquals(50, new EffectiveLines("src/test/javaCases/javasrc01.java").getLinesNum());
        assertEquals(19, new EffectiveLines("src/test/javaCases/javasrc02.java").getLinesNum());
        assertEquals(17, new EffectiveLines("src/test/javaCases/javasrc03.java").getLinesNum());
    }

}