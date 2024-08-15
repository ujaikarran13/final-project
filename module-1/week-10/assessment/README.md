# Module Assessment

## Introduction

This assessment verifies the competencies that you learned during the module. It's a hands-on assessment that you'll complete inside your IDE. The assessment has two parts:

* **Part One** covers foundational programming concepts such as data types, branching, loops, arrays, and collections. These code challenges look like the exercises you completed in the associated units. The project includes tests that verify that you have completed each challenge.

* **Part Two** includes a written specification. You'll build a class by adding fields and methods. The provided tests verify that you correctly coded the class according to specification, much like the object-oriented programming exercises you completed earlier in the module. You'll find a detailed specification of the requirements of this class in a later section of this document.

**After completing both parts, you need to `add`, `commit`, and `push` the solution code to your repository. Then submit your assessment from BootcampOS.**

## Hints and suggestions

* You can freely switch between both parts. If you get stuck on one part, you may want to move to the other.
* `push` your code often. Whenever you reach a point where you feel like you've made good progress and your code builds, commit and push your changes.
* The code you submit must build properly to get scored. _Please make sure you don't have any build errors._

### Running the tests

The project includes tests so you can verify your code. You can find the tests under `src/test/java/com.techelevator/`. The tests in the `AssessmentPart1Test` class are for part one, and the tests in the `HotelReservationTest` class are for part two. **Be sure to run all of these tests.**

> To run all tests in _IntelliJ_, you can right-click in the `java` folder beneath `test`, and select **Run 'All Tests'**.

## Part One: Code challenges

You can find the project for this assessment in your student repository. When you find the project folder, launch the project in IntelliJ.

All of the challenges are in `src/main/java/com.techelevator/AssessmentPart1.java`. Work through each challenge, running tests as you see fit.

After finishing your work, commit, and push the code to your repository.

## Part Two: Class specification

In this part, you'll build a `HotelReservation` class in `src/main/java/com.techelevator/HotelReservation.java`. As you work through the following list, you'll be able to run tests at any point to see your progress.

* `HotelReservation` must have the following attributes:
  * `name` (`String`) indicates the customer's name on the reservation. _Public getter and setter._
  * `numberOfNights` (`int`) indicates how many nights the reservation is for. _Public getter and setter._
  * `nightlyRate` (`int`) indicates the price (in dollars) of the room per night. _Public getter and setter._
  * `estimatedTotal` (`int`) indicates the estimated total (in dollars) before considering additional fees. _Derived: public getter only._ Calculate the value of this attribute using `numberOfNights` times `nightlyRate`.

* `HotelReservation` must have a public constructor which accepts parameters `name`, `numberOfNights`, and `nightlyRate` (in that order), with the appropriate data types for their corresponding fields. Set the values of the object's fields using the arguments passed into the constructor.

* `HotelReservation` must have a public method called `getActualTotal` that takes two `boolean` input parameters: `requiresCleaning` and `usedMinibar`  (in that order). This method calculates the actual total using this algorithm:
    * Start with the value of `estimatedTotal`.
    * If `requiresCleaning`is `true`, add a cleaning fee of $25.
    * If `usedMinibar` is `true`, add a mini-bar fee of $15, and _double the cleaning fee_.

* If a user calls `toString()` on an object of type `HotelReservation`, it must return a String like `{name}:{estimated total}` where `{name}` and `{estimated total}` are placeholders for the actual object values. Don't include any extra spaces or a `$`.

  > For example: `Leonard Sullivan:125`

After finishing your work, commit, and push the code to your repository.

## Submit your work

When you have completed the coding portion of the assessment, be sure the project builds. Then push the code to your repository and submit the assessment from BootcampOS.
