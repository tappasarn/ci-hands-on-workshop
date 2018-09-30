# Protect your master with GitHub protected branch
We now have completed steps to connect continuous integration services with our github repository.
However, we have not yet added any protection for our master branch.

As we can see from the image below, even though, both on the CI services complain that something is wrong in our pull request, we can still merge them into our master branch
![can merge](https://user-images.githubusercontent.com/11821799/45927907-2e0ce800-bf65-11e8-92a6-743264cf17f6.png)

In this section, we want to block the defected pull requests from being able to merge into our master branch. And here is how.

1. In the project, click at setting on the top right and then branches on the left.
![branch](https://user-images.githubusercontent.com/11821799/45927965-26017800-bf66-11e8-9a10-241e39ec0be4.png)

2. After selecting branch, you will see a section for 'branch protection rules'. Here is the place where we can add many configurations that can ensure the safety of the specify branch.

3. Click at 'add rule'. You will then see 2 sections showing up which are

    1. Apply rule to : this is a text box that allow you to type the name of the branch that you want to apply the rule to. Mostly, you would want to apply the rule to master branch because we want to keep it protected.
    ![apply to](https://user-images.githubusercontent.com/11821799/45928196-d15ffc00-bf69-11e8-8f1d-610faf6c775d.png)

    2. Next we will focus on 'rule settings' section. In this section it contains many interesting configurations that you might want to apply to your protected branch. But for this workshop we will focus on the following.
    ![rules](https://user-images.githubusercontent.com/11821799/45928246-c8bbf580-bf6a-11e8-8b43-f97f7e873990.png)

4. Now you will not be able to merge defected pull requests that are being reported from your CI service into the master branch. Everyone in the team will have to follow this rules even if you are the admin of the project.