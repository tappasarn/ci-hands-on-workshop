# Unit Testing
In the projects you will see `App.java` and `CurrencyFormatter.java`.

Currently the implementation of `CurrencyFormatter.java` is relying on `DecimalFormat` class.

Try execute our program with the following commands.

```
./gradlew run --args "100"
./gradlew run --args "1000"
./gradlew run --args "1000000"
```
You should see 100, 1,000 and 1,000,000 as its output

## Making changes

In this section we will assume that our current implementation of `CurrencyFormatter.java` is facing with the performance problem from the usage of `DecimalFormat`. Therefore, you might want to fix the performance issue in `CurrencyFormatter.java`. 

After your friend know about this problem. He comes up with the following code.
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
## Let's make some change !
1. Delete the code in `CurrencyFormatter.java` and replace with the code above.

2. Now lets verify the code that we just applied by running the following commands.
```
./gradlew run --args "100"
./gradlew run --args "1000"
./gradlew run --args "1000000"
```
3. You will see the output of `1,00` `1,000` and `1,000,000`. Of course, `1,00` is not correct.

## Problems
With the changes above our code base is facing with 2 big problems.

1. We have to manually run and give parameters to our program in all the possible cases. If our app has more functions, we also have more combination to test. It will not be just "100" "1000" "1000000".

2. This code can be easily merged into our master branch. It can break our perfectly running code !

## Adding the unit tests
1. Visit `CurrencyFormatterTests.java` and add the following code into the file.

```java
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
```
2. Now rebuild the project with
```
./gradlew build
```
3. Your build has failed with an error saying that your test for `ShouldReturnFormattedNumberWithNoComma` is not passing.
```sh
> Task :test FAILED

utils.CurrencyFormatterTests > ShouldReturnFormattedNumberWithNoComma FAILED
    org.junit.ComparisonFailure at CurrencyFormatterTests.java:26

3 tests completed, 1 failed

FAILURE: Build failed with an exception.
```
## What we gain by adding 3 cases
1. You do not have to run the application, and type in input cases manually anymore.
2. You can ensure that anyone who try to change your code will not be able to successfully build the application unless they keep the correct behavior of program.
3. Does not matter how many functions we have in our application. It verifies all of them ! 

Although we already gain benefits from adding unit tests into our application, defects can and always slip into master branch. That is what we will handle in the next section with Continuous Integration.
