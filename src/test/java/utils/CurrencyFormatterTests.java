package utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyFormatterTests {
    @Test public void ShouldReturnCorrectAmount() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.format("1234");

        assertEquals("1,234", result);
    }
}
