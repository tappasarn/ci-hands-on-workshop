# Setup

### 1. Create a github account
### 2. Install JDK

##### For macOS,
Install [Homebrew](https://brew.sh/).  
Then, run the following command in Terminal.
```
$ brew tap caskroom/versions
$ brew cask install java8
```

##### For Windows,
Download and install JDK8 from [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

### 3. Install text editor

We recommend [VSCode](https://code.visualstudio.com/). Still, other text editor is fine.

### 4. Fork the project
Since you will be setting up your own CI system. You have to fork this project into your own github account.
Go to https://github.com/tappasarn/ci-hands-on-workshop and click fork on the top right.

### 5. Install git

##### For macOS,
You alreay have `git` installed from [Step 2](https://github.com/tappasarn/ci-hands-on-workshop/new/master#2-install-jdk).

##### For Windows,
Download and install git [here](https://git-scm.com/download/win).


### 6. Clone the repository

##### For Windows,

In git bash, run the following commands,

Change directory to desktop,
```
$ cd ~/Desktop/
```

Clone source code in to a folder.
```
$ git clone https://github.com/your-github-account/ci-hands-on-workshop.git
```

After this, you should have a folder called `ci-hands-on-workshop` on your desktop.

### 5. Try to build the project
```
./gradlew build
```
# The Workshop
* https://docs.google.com/presentation/d/16pp2UfPNYjdIaRMsIUy_HeCg9PsXsAOOK_5yndCaKrQ/edit#slide=id.g42721b553c_0_59
## Unit Testing
In the projects you will see 'App.java' and CurrencyFormatter.java

Currently the implementation of CurrencyFprmatter.java is relying on DecimalFormat class.

Try execute our program with the following commands

```
./gradlew run --args "100"
./gradlew run --args "1000"
./gradlew run --args "1000000"
```
You should see 100, 1,000 and 1,000,000 as its output

### Making changes

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

### Problems
With the changes above our code base is facing with 2 big problems

* We have to manually run and give parameters to our program in all the posible cases. If our app has more functions, we also have more cobination to test. It will not be just "100" "1000" "1000000".

* This code can be easily merged into our master branch. It can break our perfectly running code !

### Adding the unit tests
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

### What we gain by adding 3 cases
* You do not have to run the application with many input cases anymore
* You can ensure that anyone who try to change your code will not be able to successfully build the application unless they keep the correct behaviour of program
* Does not matter how many functions we have in our application. It verifies all of them ! 

Althogh we already gain benefits from the unit tests we just added into our application, nothing yet ensure that the defected code is not going to be merge into our master branch. That is what we will handle in the next section with Continuous Integration.

## Connect Continuous Integration with GitHub
In this workshop, we will introduce you to 2 CI services which are...
* CircleCI
* TravisCI 
### CircleCI
check out [this document](https://circleci.com/docs/2.0/language-guides/) for different setting type for your favorite project languages

For us, we will use Java with gradle.

* Create .circleci in your project root directory (if you have been following the tutorial without renaming anything, your root will be ci-hands-on-workshop).

* Under .circleci folder create a config file name 'config.yml'. This file will be the place where we add the configuration for our project continuous integration.

* Go to https://circleci.com then sign up with your github account.
![circleci sing up](https://user-images.githubusercontent.com/11821799/45926980-ed59a280-bf55-11e8-817d-77eea8c2e1df.png)

* Add this project.
![circleci add project](https://user-images.githubusercontent.com/11821799/45926981-edf23900-bf55-11e8-9a04-6502f0012190.png)

* Copy and paste the follwing code into your config.yml (this is the same as the sample that circleci.com gave to you. I only add a bit more comments).
```yaml
# Java Gradle CircleCI 2.0 configuration file
#
# Check https://circleci.com/docs/2.0/language-java/ for more details
#
# according to the document in above link, we always start with version 2 !
version: 2
#
# jobs : each phase of your deployment process
# for this project, we only have build phase
jobs:
  # our one and only phase begin here !
  build:
    # working directory for the job
    # each job needs it own working directory
    working_directory: ~/repo
    #
    # circleCI build happen in a container
    # we need to give it an image !
    docker:
      # specify the version you desire here
      # for us, we use JAVA 8
      - image: circleci/openjdk:8-jdk
    
    environment:
      # Customize the JVM maximum heap limit
      JVM_OPTS: -Xmx3200m
      TERM: dumb
    #
    # after we finish preparing
    # add your build steps
    steps:
      # checkout the codebase
      - checkout

      # Download and cache dependencies
      # just like NPM for js and NuGet for c#
      - restore_cache:
          keys:
          - v1-dependencies-{{ checksum "build.gradle" }}
          # fallback to using the latest cache if no exact match is found
          - v1-dependencies-

      - run: gradle dependencies

      - save_cache:
          paths:
            - ~/.gradle
          key: v1-dependencies-{{ checksum "build.gradle" }}
        
      # run tests!
      - run: gradle test
```

* Push code and hit start building button on circleci.com. It will create github webhook for us
![circleci start](https://user-images.githubusercontent.com/11821799/45926982-edf23900-bf55-11e8-930b-459d935c84c6.png)

* Right now, CircleCI will be lintening to all the branches and run the build everytime the new code get push into our github origin

* Maybe we would not want to go that far (it is totally up to you). If you want CircleCI to start building only for pull requests, here is how.

* On your project, click at setting
![circleci setting](https://user-images.githubusercontent.com/11821799/45927222-dd43c200-bf59-11e8-858c-e1b2d9e8067c.png)

* Go to advance setting, then click and 'on' for 'only build for pull requests'
![circleci pr only](https://user-images.githubusercontent.com/11821799/45927231-ff3d4480-bf59-11e8-8405-a16d54a6c014.png)
### TravisCI

In case you want to read more about getting started with travisCI, check out [this link](https://docs.travis-ci.com/user/getting-started/). It has information about your favorite languages.

again for us, we are using Java with Gradle !

* Visit https://travis-ci.org/ and sign up with your github account
![travis sign up](https://user-images.githubusercontent.com/11821799/45927104-f51a4680-bf57-11e8-8850-3237cd0dc7df.png)

* Enable the project that you want to activate TravisCI
![travis enable project](https://user-images.githubusercontent.com/11821799/45927259-af12b200-bf5a-11e8-9ad3-2432a3e81907.png)

* Similar to CircleCI example from above, TravisCI also needs a configuration file. In your project's root directory (if you have been following the tutorial without renaming anything, your root will be ci-hands-on-workshop) create a file name '.travis.yml'.

* Click [here](https://docs.travis-ci.com/user/languages/) for a full list of your favorite languages configuration file.

* For our workshop please select Java. You will have to add the following code into .travis.yml file.
```yaml
language: java
```

* Yes ! just a single line. TravisCI happen to be very smart, just adding a single line will helps us get the project running. You can optimize the build further but we will not cover the optimization in this workshop. Click [here](https://docs.travis-ci.com/user/languages/java/) to read more about Java build

* Commit and push !
## Protect your master with GitHub protected branch

## SonarClound