package utils;
import java.util.ArrayList;
import java.util.Collections;

public class CurrencyFormatter {
    public String format(String amount) {
        char[] amountArr = amount.toCharArray();
        ArrayList<Character> convertedList = new ArrayList<>();

        for(int i = amountArr.length-1; i >= 0; i--){
            if(i % 3 == 0 && i != amountArr.length-1) {
                convertedList.add(',');
            }
            convertedList.add(amountArr[i]);
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
