package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CurrencyFormatterTests {
    // @Test public void ShouldReturnFormattedNumberWithSingleComma() {
    //     CurrencyFormatter c = new CurrencyFormatter();
    
    //     String result = c.format("1234");
    
    //     assertEquals("1,234", result);
    // }
    @Test public void ShouldReturnFormattedNumberWithMultipleCommas() {
        CurrencyFormatter c = new CurrencyFormatter();
    
        String result = c.format("1234567");
    
        assertEquals("1,234,567", result);
    }
    @Test public void ShouldReturnFormattedNumberWithNoComma() {
        CurrencyFormatter c = new CurrencyFormatter();
    
        String result = c.format("123");
    
        assertEquals("123", result);
    }
    
}
