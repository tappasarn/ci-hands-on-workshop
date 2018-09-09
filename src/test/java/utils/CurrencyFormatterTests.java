package utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyFormatterTests {
    @Test public void ShouldReturnCorrectAmount() {
        CurrencyFormatter c = new CurrencyFormatter();

        String result = c.format("1234", "en_Us");

        assertEquals("123", result);
    }

}
