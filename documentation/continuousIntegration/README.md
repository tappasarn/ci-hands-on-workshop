# Connect Continuous Integration with GitHub
In this workshop, we will introduce you to 2 CI services which are...
* CircleCI
* TravisCI 
## CircleCI
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
## TravisCI

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

* You will see TravisCI start showing up on your pull requests !
![travis pr](https://user-images.githubusercontent.com/11821799/45927526-8e4c5b80-bf5e-11e8-81af-3d7f3e54f6a4.png)

* Same as CircleCI example from above, now Travis is also running on all the branches that get pushed into the origin. If you want to enable the build only on pull requests, here is how.

* Go to your project in https://travis-ci.org

* Click at 'more options' and then settings
![travis settings](https://user-images.githubusercontent.com/11821799/45927558-424de680-bf5f-11e8-914b-b67a213a67cf.png)

* Disable 'build pushed branches'
![travis pr only](https://user-images.githubusercontent.com/11821799/45927572-70332b00-bf5f-11e8-87ca-7b0b8d8d5bdc.png)