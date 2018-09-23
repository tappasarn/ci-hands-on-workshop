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