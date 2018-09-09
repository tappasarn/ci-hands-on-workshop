package utils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class CurrencyFormatter {
    public String format(String amount) {
        Double dAmount = Double.parseDouble(amount);
        DecimalFormat d = new DecimalFormat("#,###.##");
        return d.format(dAmount);
    }
}
