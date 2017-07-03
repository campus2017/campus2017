package com.campus.homework;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ExchangeRateTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExchangeRateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExchangeRateTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testExchangeRate()
    {
        assertTrue( true );
    }
}
