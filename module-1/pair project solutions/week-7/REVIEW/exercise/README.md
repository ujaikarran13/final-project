# Review Day 7 exercise

In this exercise, you'll continue to work with Pet Elevator on their Customer Relationship Manager and HR system. After you built the classes for the system, a team of top-notch designers created a user interface for the application.

Your team lead is asking you to "harden" the application by writing unit tests, handle runtime errors, and add some defensive programming code. The remainder of this document summarizes the requests that your team leader has asked of you.

## Requirements

* All classes you create must be in the correct package.
* The project must not have any build errors.
* The unit tests provide sufficient coverage of features, and pass as expected.
* Code uses appropriate variable names and data types.
* Code is in a clean, organized format.
* Code is appropriately encapsulated.
* The code meets the following specifications.

## Step One: Run the application

To get familiar with the application you'll be working on, start by running it. Select the option to **Select a department** and choose one. Select each option from the department menu to understand how it prompts the user, and what data it displays.

## Step Two: Review the starting code

The software team has started building a system for the Human Resources (HR) department to manage employee, manager, and department information.

Take a moment to explore the starting code:

* You're familiar with the model code:
  * `com.petelevator.model`
    * `Billable.java`: interface defining methods for objects that are "billable"—meaning someone that the system can bill for services
    * `Person.java`: base class for `Customer` and `Employee`.
  * `com.petelevator.model.hr`
    * `Department.java`: class to represent a department in the business
    * `Employee.java`: class to represent an employee of the company
    * `Manager.java`: class to represent a manager of the company
  * `com.petelevator.model.crm`
    * `Customer.java`: class to represent a department in the business
    * `Pet.java`: class to represent an employee of the company
* The application code added by the UI team:
  * `com.petelevator`
    * `Application.java`: This is the application entry point, with the `main()` method.
    * `HRController.java`: The class responsible for orchestrating the user interaction and calls to the business object methods.
    * `HRView.java`: The class that handles all direct interaction with the user through the console.

## Step Three: Handle an exception in the Raise Salary feature

* Run the application again
* From the menu, choose **Select a department** and choose any department.
* From the department menu, select **Give a raise**.
* Select any employee from the subsequent menu.
* When prompted for the raise amount, type `-10` and press **Enter**. You'll see an error:

```
Enter the percentage increase (e.g. '5' for 5%): -10
Exception in thread "main" java.lang.IllegalArgumentException: Percentage must be a positive value.
	at com.petelevator.model.hr.Employee.raiseSalary(Employee.java:40)
	at com.petelevator.HRController.awardRaise(HRController.java:199)
	at com.petelevator.HRController.displayDepartmentMenu(HRController.java:103)
	at com.petelevator.HRController.displayHRMenu(HRController.java:58)
	at com.petelevator.HRController.run(HRController.java:44)
	at com.petelevator.Application.main(Application.java:18)

Process finished with exit code 1
```

The program exits abruptly. What you did in the preceding steps is "reproduce" an issue, sometimes shortened to "repro" (REE-pro). The program must never crash just because the user typed a bad value. Your task is to correct the issue.

The stack trace shows that the call to `Employee.raiseSalary` from `HRController.awardRaise` produced an `IllegalArgumentException`. Find the code in `HRController.awardRaise` which calls `Employee.raiseSalary`. Add the `try-catch` code needed to catch the `IllegalArgumentException`, and inform the user, "There was an error raising the employee's salary: ", and print the exception message after that.

> Note: to inform the user of an error, call `view.printErrorMessage(message)`.

When you've completed this step, try again to repro the issue. If you were successful, the program behaves like this:

```
Enter the percentage increase (e.g. '5' for 5%): -10
***There was an error raising the employee's salary: Percentage must be a positive value.***
---------------------------
Department 2 Marketing Menu
---------------------------
...
```

## Step Four: Add "defensive code" to avoid the error if possible

It's important to handle exceptions and report to the user gracefully, but what if you could prevent the error from occurring altogether? Whenever you write exception-handling code, also ask yourself, "is there something to do that can prevent this exception, instead of just reacting to it?" This is a technique called "defensive programming."

You have an opportunity to write defensive code in the `promptForRaiseAmount` method of `HRView`. That code currently returns any `Double` that the user enters. Your task is to re-write that method such that if the user enters a non-positive value, it tells the user "Percentage must be positive" and asks again for an amount.

After writing the code, try again to enter a negative raise amount. This time, the program responds like this:

```
Enter the percentage increase (e.g. '5' for 5%): -10
***Percentage must be positive***
Enter the percentage increase (e.g. '5' for 5%): 0
***Percentage must be positive***
Enter the percentage increase (e.g. '5' for 5%): 4
Paprocki, Lenna got a raise from 120000 to 124800.00.
```

## Step Five: Make "set department head" more informative

Here is the repro for this issue:

* Run the application
* From the menu, choose **Select a department** and choose any department.
* From the department menu, select **Set new dept head**.
* Enter employee information, but make sure the title **doesn't** begin with "Manager" or "Director."
* The program reports success:

  ```
  Please select: 6
  First name: Engie
  Last name: Engle
  Title: Engineer Extraordinaire
  Salary: 200000
  Engle, Engie has been added as head of Engineering
  ```
* Now select **Display department information**

  ```
  Engineering Department Information
  ----------------------------------
    Id: 1
    Dept. head: Darakjy, Josephine
    Number of employees: 4
  ```

The program reported success in changing the department head, but actually failed to do so. This is because the code in
`Department.setDepartmentHead()` currently only changes the department head if the title is valid:

```java
if (manager.getTitle().startsWith("Manager") || manager.getTitle().startsWith("Director")) {
    this.departmentHead = manager;
}
```

Your task is to throw an exception if the title is invalid so the caller knows whether the change really took place. To do this, you'll create a custom exception class and throw.

### Step 5a: Create the `UnqualifiedDepartmentHeadException` class

* This class must be in package `com.petelevator.model.hr`.
* This class extends `Exception`.
* Create the appropriate constructors.

### Step 5b: Throw `UnqualifiedDepartmentHeadException`

In `Department.setDepartmentHead()`, if the candidate's title doesn't start with "Manager" or "Director", throw an instance of `UnqualifiedDepartmentHeadException`. If the title is OK, then set the new department head and return.

### Step 5c: Catch the exception and report the error

Finally, in `HRController.setDepartmentHead()`, place the code that calls `Department.setDepartmentHead()` into a `try-catch` block. In the `catch`, report the issue to the user.

When complete, the interaction looks like this:

```
Please select: 6
First name: Engie
Last name: Engle
Title: Engineer Extraordinaire
Salary: 200000
***Engle, Engie could not be added: The candidate must have a title that begins with 'Manager' or 'Director'***
-----------------------------
Department 1 Engineering Menu
...
```

## Step Six: Write unit tests

Your team lead has also asked you to write unit tests for the classes in the `hr` package: `Department`, `Employee`, and `Manager`. Your tests must verify the features described in each of the following sections. You'll decide on the actual scenarios and edge cases to test.

### Department

Class `DepartmentTest` contains tests for the `Department` class methods. It must test for:

* The class constructor sets the `departmentId` and `departmentName` properties, and initializes an empty list of `departmentEmployees`.
* Method `transferEmployeeIn` removes an employee from their original department's `departmentEmployees` list, adds the employee to their new department's `departmentEmployees` list, and sets the `department` of the employee to the new department.
* Method `setDepartmentHead` assigns the specified `Manager` to the `departmentHead` property.
  * If the specified `Manager`'s `title` property doesn't start with either "Manager" or "Director", the method must throw an `UnqualifiedDepartmentHeadException`.

#### **Testing for an exception**

To test that an exception was actually thrown by the method under test, you may a couple techniques:

* Place the call to the method in a `try` block. On the line following the call to the method, still in the `try`, place a call to `fail(message)` to fail the test. Add a `catch` for the expected exception - in the `catch` block, there's no need to do anything. If the method throws the exception, your `catch` runs, and the test finishes without error. If the exception _doesn't_ happen, then the `fail()` line runs, failing your test. The code looks like:

```java
@Test
public void setDepartmentHead_BadTitle() {
  // arrange - do your setup here
  ...
  // act
  try {
    // call the method under test here
    ...
    // Then if program flow gets to here, the exception was not thrown
    fail("Manager title does not start with Manager or Director so UnqualifiedDepartmentHeadException is expected.");

  } catch (UnqualifiedDepartmentHeadException e) {
      // Test passes because the exception was thrown
  }
}
```

* Alternatively, you can modify the `@Test` attribute to state that the test _expects_ a particular exception. That code looks like this:

```java
@Test(expected = UnqualifiedDepartmentHeadException.class)
public void setDepartmentHead_BadTitle() throws UnqualifiedDepartmentHeadException{
  // arrange - do your setup here
  ...
  // act - call the method under test here
  ...
  // assert - there's nothing to assert. 
  //  If the method call succeeded with **no exception** the test fails. 
  //  If the exception was raised, the test passes.
}
```

### Employee

Create class `EmployeeTest` for the `Employee` test methods.

* The class constructors set the appropriate property values.
* Method `getFullName()` returns a `String` with last name, then ", ", then first name.
* Method `raiseSalary()` increases the employee's salary by the given positive percentage.
  * Expect the salary value rounded to the nearest cent (two decimal places).
  * If `raiseSalary()` receives an argument that's zero or negative, it throws an `IllegalArgumentException` with the message "Percentage must be a positive value."
* Method `getBalanceDue()` calculates the total value of services rendered from the `servicesRendered` parameter, which is a map of service names and prices.
  * For the Employee calculation, the price of the service "Walking" receives a 50% discount.

### Manager

Create class `ManagerTest` for the `Manager` test methods.

* The class constructors set the appropriate property values.
* Method `hireEmployee()` returns an `Employee` object with appropriate property values set.
  * The `department` property of the new `Employee` contains the correct department.
  * The `departmentEmployees` list on the employee's hiring department contains new `Employee`.
