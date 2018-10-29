# Format currency correctly

From the previous steps, we made sure that only when our program works correctly, it will be allowed to merge into the master branch.

Since our `CurrencyFormatter.java` does not work correctly, we need to make some changes to it.  

## Making changes

It is fine to try spotting the mistakes but that is not our focus for today's workshop, so let's move on.  
Update `src/main/java/utils/CurrencyFormatter.java` to match the code below.

```java
package utils;
import java.text.DecimalFormat;

public class CurrencyFormatter {
    public String format(String amount) {
        Double dAmount = Double.parseDouble(amount);
        DecimalFormat d = new DecimalFormat("#,###.##");
        return d.format(dAmount);
    }
}
```

## Try building our project

Now that we have fixed out `format(String)` method.  

It is time to verify that it produces the correct results. Try running the following command to build your project.

```
./gradlew build
```

You should see `BUILD SUCCESSFUL` message.

Now, we are ready to move on to the last step.

## Next 

[Code Quality](../codeQuality)
