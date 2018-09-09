package utils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class CurrencyFormatter {

    public String formatWithDecimalFormat(String amount) {
        Double dAmount = Double.parseDouble(amount);
        DecimalFormat d = new DecimalFormat("#,###.##");
        return d.format(dAmount);
    }

    public String format(String amount) {
        char[] amountArr = amount.toCharArray();
        ArrayList<Character> convertedList = new ArrayList<>();

        int y = 0;
        for(int i = amountArr.length-1; i >= 0; i--){
            if(y==3) {
                convertedList.add(',');
                y=0;
            }
            convertedList.add(amountArr[i]);
            y++;
        }

        Collections.reverse(convertedList);

        StringBuilder builder = new StringBuilder(convertedList.size());
        for(Character ch: convertedList)
        {
            builder.append(ch);
        }

        return builder.toString();
    }
}
