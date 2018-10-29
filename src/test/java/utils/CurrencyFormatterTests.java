package utils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CurrencyFormatterTests {
  @Test public void ShouldReturnFormattedNumberWithSingleComma() {
      CurrencyFormatter c = new CurrencyFormatter();

      String result = c.format("1234");

      assertEquals("1,234", result);
  }
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

  @Test public void ShouldReturnFormattedNumberWithNoComma() {
      CurrencyFormatter c = new CurrencyFormatter();

      String result = c.format("12344");

      assertEquals("12,344", result);
  }
}
