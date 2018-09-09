package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyFormatter {
    public String format(String amount) {
        char[] amountArr = amount.toCharArray();
        ArrayList<Character> convertedList = new ArrayList<Character>();
        for(int i = 0; i < amountArr.length; i++){
            if(i % 3 == 0){
                convertedList.add(',');
                convertedList.add(amountArr[i]);
            }
            convertedList.add(amountArr[i]);
        }

        Collections.reverse(convertedList);
        return convertedList.toString();
    }
}
