# Unit Testing
In the projects you will see 'App.java' and CurrencyFormatter.java

Currently the implementation of CurrencyFprmatter.java is relying on DecimalFormat class.

Try execute our program with the following commands

```
./gradlew run --args "100"
./gradlew run --args "1000"
./gradlew run --args "1000000"
```
You should see 100, 1,000 and 1,000,000 as its output

## Making changes

In this section we will assume that our current implemetation of CurrencyFormatter.java is facing with the performace problem from the usage of DecimalFormat. Therefore, there is a task for you to come up with your own implementation to fix the performace issue in CurrencyFormat.java

After your friend know about this problem. He comes up with the following code
```java
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
```

Delete the code in CurrencyFormat.java and replace with the code above.

Now lets verify the code that we just applied by running the following commands.
```
./gradlew run --args "100"
./gradlew run --args "1000"
./gradlew run --args "1000000"
```
You will see the output of 1,00 1,000 and 1,000,000. Of cause, 1,00 cannot be correct.

## Problems
With the changes above our code base is facing with 2 big problems

* We have to manually run and give parameters to our program in all the posible cases. If our app has more functions, we also have more cobination to test. It will not be just "100" "1000" "1000000".

* This code can be easily merged into our master branch. It can break our perfectly running code !

## Adding the unit tests
Visit CurrencyFormatterTests.java and add the following code into the file.

```java
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
```
Now rebuild the project with 
```
./gradlew build
```
Your build has failed with an error saying that your test for ShouldReturnFormattedNumberWithNoComma is not passing.

## What we gain by adding 3 cases
* You do not have to run the application with many input cases anymore
* You can ensure that anyone who try to change your code will not be able to successfully build the application unless they keep the correct behaviour of program
* Does not matter how many functions we have in our application. It verifies all of them ! 

Althogh we already gain benefits from the unit tests we just added into our application, nothing yet ensure that the defected code is not going to be merge into our master branch. That is what we will handle in the next section with Continuous Integration.