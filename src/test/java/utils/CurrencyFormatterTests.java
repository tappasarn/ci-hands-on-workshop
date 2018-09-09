package utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyFormatterTests {
    @Test public void ShouldReturnCorrectAmount() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.format("1234");

        assertEquals("1,234", result);
    }

    @Test public void ShouldReturnCorrectAmountWithBiggerNumber() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.format("123456789");

        assertEquals("123,456,789", result);
    }

    @Test public void UseDecimalFormatTest() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.formatWithDecimalFormat("1234");

        assertEquals("1,234", result);
    }

    @Test public void UseDecimalFormatWithBiggerNumberTest() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.formatWithDecimalFormat("123456789");

        assertEquals("123,456,789", result);
    }
}
