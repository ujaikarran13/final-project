# Module Practice Assessment

## Introduction

This practice assessment verifies the competencies that you learned during the module. It's a hands-on assessment that you'll complete inside your IDE. The assessment has two parts:

* **Part One** covers foundational programming concepts such as data types, branching, loops, arrays, and collections. These code challenges look like the exercises you completed in the associated units. The project includes tests that verify that you have completed each challenge.

* **Part Two** includes a written specification. You'll build a class by adding fields and methods. The provided tests verify that you correctly coded the class according to specification, much like the object-oriented programming exercises you completed earlier in the module. You'll find a detailed specification of the requirements of this class in a later section of this document.

**After completing both parts, you may `add`, `commit`, and `push` the code to your repository. Since this is a practice assessment, it's not graded and you don't need to submit this assessment to BootcampOS.**

## Hints and suggestions

* You can freely switch between both parts. If you get stuck on one part, you may want to move to the other.
* `push` your code often. Whenever you reach a point where you feel like you've made good progress and your code builds, commit and push your changes.
* The code you submit must build properly to get scored. _Please make sure you don't have any build errors._

### Running the tests

The project includes tests so you can verify your code. You can find the tests under `src/test/java/com.techelevator/`. The tests in the `PracticePart1Test` class are for part one, and the tests in the `ParkingLotTest` class are for part two. **Be sure to run all of these tests.**

> To run all tests in _IntelliJ_, you can right-click in the `java` folder beneath `test`, and select **Run 'All Tests'**.

## Part One: Code challenges

You can find the project for this assessment in your student repository. When you find the project folder, launch the project in IntelliJ.

All of the challenges are in `src/main/java/com.techelevator/PracticePart1.java`. Work through each challenge, running tests as you see fit.

After finishing your work, commit, and push the code to your repository.

## Part Two: Class specification

In this part, you'll build a `ParkingLot` class in `src/main/java/com.techelevator/ParkingLot.java`.

You're provided with a `Car` class `src/main/java/com.techelevator/Car.java` which you'll use in your implementation of the `ParkingLot` class.

* The `Car` class has the following attributes:
  * `type` (`String`) indicates the type of car: `COMPACT`, `MIDSIZE`, and `FULLSIZE`.  _Public getter and setter._
  * `license` (`String`) indicates the vehicle license for the car.  _Public getter and setter._

* The `Car` has one public constructor which accepts parameters `type` (`String`) and `license` (`String`) and sets the corresponding attributes.

* The `Car` has a single method, `toString()`.

The basic design of `ParkingLot` is to have multiple sets of slots in which `Car`s can park. There are three sets of slots, one for each type of car: `COMPACT`, `MIDSIZE`, and `FULLSIZE`. The number of slots in each set are independent of each other and you can set it when you instantiate a `ParkingLot`.

`Car`s may only park in a lot that's `open`. `Car`s may only park in slots designated for their `type`. The slots get occupied on a first-available basis, meaning the slots for the `Car`'s `type` get scanned from the beginning for the first one without another `Car` parked in it. If there's a free slot available, the `Car` is "parked" in the slot. If all slots are already filled, the `Car` isn't parked.

Exiting a `Car` from the parking lot starts with a search for the desired `Car`'s `license` among all of the `ParkingLot`'s `type` slots. If found, the `Car` gets removed from the slot which it was in, and the slot marked as free.

The implementation of the slots, marking them free or occupied, and parking and retrieving `Car`s is up to you, the programmer. Fixed length arrays of `Car` objects, or dynamically managed `List`s of `Car`s are two likely approaches, but many others are available.

As you work through the following list of requirements for the `ParkingLot` class, you'll be able to run tests at any point to see your progress.

* `ParkingLot` contains several public constant default values for the number of slots to create for each type of car:
  * `DEFAULT_NUMBER_OF_COMPACT_SLOTS` = `3`
  * `DEFAULT_NUMBER_OF_MIDSIZE_SLOTS` = `5`
  * `DEFAULT_NUMBER_OF_FULLSIZE_SLOTS`= `2`
> The unit tests in the `ParkingLotTest` class also use these constants. _**You must not change or remove these constants.**_

* `ParkingLot` must have the following attributes:
  * `name` (`String`) indicates the parking lot's name. _Public getter only, no setter._
  * `open` (`boolean`) indicates whether the parking lot is open or closed. _Defaults to `false`, meaning closed. Public getter and setter._
  * `numberOfCompactSlots` (`int`) indicates the total number of compact car slots the parking lot contains. _Defaults to `3`. Public getter only, no setter._
  * `numberOfMidsizeSlots` (`int`) indicates the total number of mid-size car slots the parking lot contains. _Defaults to `5`. Public getter only, no setter._
  * `numberOfFullsizeSlots` (`int`) indicates the total number of full-size car slots the parking lot contains. _Defaults to `2`. Public getter only, no setter._
  * `lotSize` (`int`) indicates the total number of slots the parking lot contains. _Derived: public getter only._ Calculate the value of this attribute using the sum of `numberOfCompactSlots`, `numberOfMidsizeSlots`, and `numberOfFullsizeSlots`.

* `ParkingLot` must have three public constructors:
  1. Accepts the parameter `name` (`String`), sets the corresponding attribute, and establishes the default values for `open` and `numberOfCompactSlots`, `numberOfMidsizeSlots`, and `numberOfFullsizeSlots`.
  2. Accepts the parameters `name` (`String`) and `open` (`boolean`), sets the corresponding attributes, and establishes the default values for `numberOfCompactSlots`, `numberOfMidsizeSlots`, and `numberOfFullsizeSlots`.
  3. Accepts the parameters `name` (`String`), `open` (`boolean`), `numberOfCompactSlots` (`int`), `numberOfMidsizeSlots` (`int`), and `numberOfFullsizeSlots` (`int`), and sets the corresponding attributes.

* `ParkingLot` must have the following public methods:
  * `numberOfAvailableSlots` which takes one parameter `carType` (`String`) and returns an `int` representing the difference between the number of slots for a given car type slot and the number of cars already parked in those slots.
  * `park` which takes one parameter `car` (`Car`) and returns `true` if the `Car` was successfully parked in a slot for its `Type`, or `false` if the `Car` wasn't parked. A car can't park if there's no available slots for `Car`'s `type`.
    * If someone attempts to park a `Car` in a closed parking lot (`isOpen() == false`), the method must throw a `ParkingLotException` with an appropriate message.
  * `exit` which takes one parameter `license` (`String`) and searches for the `Car` with given `license` in all the slots in the lot. It returns the `Car` if found, otherwise it returns `null` if the car wasn't successfully found.

## Submit your work

After completing the code, you may `add`, `commit`, and `push` the code to your repository. Since this is a practice assessment, it's not graded and you don't need to submit this assessment to BootcampOS.
