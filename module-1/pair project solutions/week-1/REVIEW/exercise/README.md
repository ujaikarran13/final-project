# Review Day 1 Exercise

Review day exercises are an opportunity for you to cement the knowledge and practice the skills you've learned over the past week. They're intended to have pairs of students complete the work together.

## Pair-programming

Traditionally, *pair-programming* meant two programmers working side-by-side on a single computer where one programmer sat at the keyboard writing the code while the other kept an eye on the quality and overall direction of the implementation. The programmer at the keyboard became known as the *driver*, the other as the *navigator*. Back then and now, the only rule to pair-programming is the programmers must periodically switch roles. The driver becomes the navigator, and the navigator becomes the driver.

Given the prevalence of truly personal computers in the form of laptops, and the increasingly distributed nature of software development, the idea of a single shared keyboard seems quaint if not outright impossible. Today, pair-programming is more about sharing a source code repository than a keyboard. The two programmers still work side-by-side, although it may be through web-conferencing software. One is still the driver and the other navigator, and the two still periodically switch roles.

One key difference between then and now is before switching roles the driver pushes the changes made on their computer to the shared code repository and the navigator pulls them down to their computer after the push. Once you complete the push and pull, the navigator can take over as the driver with the most recent changes to the code.

You and your partner need to first read through the rest of this README to review the work you'll do, and then discuss how you'll share the work in terms of switching between *driver* and *navigator* roles.

You and your partner must split the work between yourselves. Your instructor examines the project's Git history to judge how you and your partner shared the work load, so don't forget to push and pull before switching roles. See [Pairs and git](#pairs-and-git) for more information regarding using Git as a pair.

>Note: classes may have an odd number of students, in which case, one pair in the class could actually be a trio. The expectation is that you rotate among the three of you with each student in the trio driving two exercises and navigating the other four.

## Completing exercises

There are six exercises in six Java files:

* `Exercise01_HighestOfThree.java`
* `Exercise02_LowestOfThree.java`
* `Exercise03_MiddleOfThree.java`
* `Exercise04_IsOrdered.java`
* `Exercise05_CigarParty.java`
* `Exercise06_YourJustDesserts.java`

You complete the problems in the same manner as your daily exercises: read the problem statement, write your solution, and run the tests to verify your work.

Although they're numbered, you can complete the exercises in any order. Feel free to decide as a pair the order in which you tackle them. Just remember to split the roles of driver and navigator evenly among yourselves.

## Pairs and Git

In addition to the basic review, the purpose of the pair exercises is to help you hone your skills in sharing code with other developers using Git.

Assuming everyone in the pair or trio has opened a terminal and changed to the Review Day 1 Exercise repository directory, you must run the following Git operations at the end of **every** successful completion of a problem:

**driver**
```bash
git add -A
git commit -m "A meaningful comment."
git push
```

Once the *driver* has pushed:

**navigators**
```bash
git pull
```

Provided the driver always commits and pushes, and the navigators always pull, switching roles is as simple as deciding whose machine to use next, making them the driver, while everyone else become navigators.
