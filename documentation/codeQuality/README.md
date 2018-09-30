# SonarCloud
SonarCloud can be viewed as a Continuous Inspection service for our code base. Its jobs is to verify that that code we wrote are implemented in such a way that meets the standard of that particular language. Moreover, it looks into the test coverage percentage of our code as well. SonarCloud is a could service that is base on SonarQube but without the complexity of maintainability.

## Start using SonarCloud
1. Visit https://sonarcloud.io/about and click at "Start using SonarCloud".

![start using](https://user-images.githubusercontent.com/11821799/46253610-04464a80-c4aa-11e8-8f64-c8ffa308f96d.png)

2. Login with your GitHub account.

3. Now, we will connect our GitHub repository (or repositories) with SonarCloud. Create new project by clicking at the following.

![new proj](https://user-images.githubusercontent.com/11821799/46253629-d281b380-c4aa-11e8-8bbe-348d269ff29e.png)
![connect github](https://user-images.githubusercontent.com/11821799/46253636-0361e880-c4ab-11e8-9a31-779adc9eb2c3.png)

4. You will see an option asking you to select whether you want to connect SonarCloud to all of your repositories or just specific one. For now, let's just connect it to our workshop repository.

![workshow repo](https://user-images.githubusercontent.com/11821799/46253657-8a16c580-c4ab-11e8-9c5e-15ebf9e0dbdd.png)

5. After you've created a project. It will ask you to generate token for the repository's analysis.

![create token](https://user-images.githubusercontent.com/11821799/46253675-1e812800-c4ac-11e8-9d0d-e524bfae03cd.png)

6. Next, we will have to provide the programming language of our project and the build technology to SonarCloud. Then it will display 2 commands for us to follow.

![language and build](https://user-images.githubusercontent.com/11821799/46253702-ee865480-c4ac-11e8-969b-4447f28baa48.png)

7. Copy the first command and make the following change to our build.gradle
```
plugins {
    // Apply the java plugin to add support for Java
    id 'java'

    // Apply the application plugin to add support for building an application
    id 'application'

    // Add the SonarQube plugin here
    // This this what you copied from the command SonarCloud's website had provided to you.
    id "org.sonarqube" version "2.6"
}
```
8. Now copy and run the second command
    * For Windows user, run the second command on GitBash (there was a problem when I tried to run it with cmd and powerShell).

9. After the second command finished executing, reload the SonarCloud's website. It will display analytic results to you.

![analytic results](https://user-images.githubusercontent.com/11821799/46253852-e1b73000-c4af-11e8-9309-8a9e7676958e.png)

10. What just happened ? We have just push our code in the working branch to SonarCloud to analyze the standard of our code quality.

11. Is the code that is being analyze comes from the origin or local changes ? Try adding the following lines into your App.java. Save the file without committing or pushing the code change.
```java
    public static void main(String[] args) {
        String amount = args[0];

        // Add this line
        double result = 1.1 + 1.2;

        CurrencyFormatter currencyFormatter = new CurrencyFormatter();

        System.out.println(currencyFormatter.format(amount));
    }
```
12. Rerun the analyze command again !

## More problems !
We have said early on that SonarCloud does not only analyze your code base, however it actually reports the unit test coverage of our project. 

Having unit test coverage helps us to have visibility on how trustful our software is. With test coverage report, we should be able to easily tell where unit test are missing and priority them in the development team.

However, right now, even we have added some unit test in the beginning of this workshop SonarCloud still reports 0% coverage for our project.

![no coverage](https://user-images.githubusercontent.com/11821799/46254263-98b6aa00-c4b6-11e8-8686-3fd9d019ace6.png)

## Get test coverage to display
If we take a look at our build.gradle, we will see that we do not have any plugin for code coverage. That is why SonarCloud were not able to pick up the result and display the report to us.

### Let's add JaCoCo (Java Code Coverage)
JaCoCo is a gradle's plugin that helps provide code coverage metrics to us. You can read more about JaCoCo [here](https://docs.gradle.org/current/userguide/jacoco_plugin.html).


## What have we done so far ?
Up until now, we are able to analyze our code base using the service provide by SonarCloud. However, the analyze process is not yet automated. We are back to the same issue that we used to have with unit tests early in the beginning of this workshop. Every time we have made some changes into our code, we will have to use SonarCloud's analyze command and manually run it by ourselves. I'm sure you would not want to do it. Most of us might not even want to remember the command itself !