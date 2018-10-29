# Format currency correctly

From the previous steps, we made sure that only the changes that do not break our program are allowed to be merged into the `master` branch.

Right now, `CurrencyFormatter.java` is causing the tests to fail meaning that it does not work correctly. Therefore, we need to fix it.

## Making changes

It is fine to try spotting mistakes but that is not our focus for today's workshop, so let's move on.  
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

Now that we have fixed our `format(String)` method.  

It is time to verify the correctness of the results. Try running the following command to build your project.

```
./gradlew build
```

You should see `BUILD SUCCESSFUL` message.

Now, we are ready to move on to the last step.

## Next 

[Code Quality](../codeQuality)
