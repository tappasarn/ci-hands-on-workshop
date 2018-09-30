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

5. After you've created a project. It will ask you to generate token for the repository's analysis
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