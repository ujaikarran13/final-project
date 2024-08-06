# Review Day 6 exercise

Congratulations—you're hired by a pet setting and grooming company called Pet Elevator. They're building their own software to use in-house.

Your job is to create classes for the Customer Relationship Management (CRM) system. A CRM system helps to manage customer information and other related data.

Since Pet Elevator is a pet sitting and grooming company, you'll need to create a class to represent the human customers and a class to represent their pets. You'll also refactor existing code to implement base classes and interfaces to reduce duplicated code and extend capabilities.

## Requirements

* All classes you create must be in the correct package.
* The project must not have any build errors.
* All unit tests pass as expected.
* Code uses appropriate variable names and data types.
* Code is in a clean, organized format.
* Code is appropriately encapsulated.
* The code meets the following specifications.

## Step One: Review the starting code

The software team has started building a system for the Human Resources (HR) department to manage employee, manager, and department information.

Take a moment to explore the starting code:

* `com.petelevator`
    * `Billable.java`: interface defining methods for objects that are "billable"—meaning someone that the system can bill for services
* `com.petelevator.hr`
    * `Department.java`: class to represent a department in the business
    * `Employee.java`: class to represent an employee of the company
    * `Manager.java`: class to represent a manager of the company
* `com.petelevator.crm`
    * This is the package where you'll add the `Customer` and `Pet` classes

## Step Two: Create the `Pet` class

Create the `Pet` class in the `com.petelevator.crm` package. This class represents a pet of a customer.

### Properties

| Property     | Data Type    | Get | Set | Description                        |
| ------------ | ------------ | --- | --- | ---------------------------------- |
| `name`         | `String`       | X   | X   | Name of pet.                       |
| `species`      | `String`       | X   | X   | Species of pet, like dog or cat.   |
| `vaccinations` | `List<String>` | X   |     | Vaccinations the pet has received. |

> Note: Remember to set `vaccinations` to a new initialized `ArrayList`. You can do this in the property declaration or constructor.

### Constructors

Create one constructor for `Pet` that accepts two `String`s to set `name` and `species`.

### Methods

| Method Name      | Return Type | Parameters |
| ---------------- | ----------- | ---------- |
| `listVaccinations` | `String`      | none       |
| `addVaccination`   | `void`       | `String`     |

The `listVaccinations` method returns the elements of `vaccinations` as a comma-delimited string. For example, if the `List` contains `["Rabies", "Distemper", "Parvo"]`, the output must be `"Rabies, Distemper, Parvo"`.

The `addVaccination` method accepts a `String` and adds it to `vaccinations`.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/crm/PetTest.java`.

## Step Three: Create the `Person` class

Another developer on the software team pointed out there's a lot of duplication in the `Employee` and `Manager` classes. Before the team adds yet another type of person, it might be a good idea to create a base `Person` class that encapsulates and defines some shared properties.

Since there wouldn't be a need for an instance of a generic `Person` in the application, the class must be `abstract`.

Create a `Person` class in the `com.petelevator` package, since the `crm` and `hr` packages are going to share the new class.

### Properties

| Property  | Data Type | Get | Set | Description                    |
| --------- | --------- | --- | --- | ------------------------------ |
| `firstName` | `String`    | X   | X   | The first name of a person.    |
| `lastName`  | `String`    | X   | X   | The last name of a person.     |
| `email`     | `String`    | X   | X   | The email address of a person. |

### Constructors

Create one constructor for `Person` that accepts two `String`s to set `firstName` and `lastName`.

### Methods

| Method Name | Return Type | Parameters |
| ----------- | ----------- | ---------- |
| `getFullName` | `String`      | none       |

The `getFullName` method returns a `String` of the first name and last name concatenated together with a space between them.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/PersonTest.java`.

## Step Four: Create the `Customer` class

Now you'll create the `Customer` class that inherits the `Person` class you just created.

### Properties

| Property    | Data Type | Get | Set | Description                      |
| ----------- | --------- | --- | --- | -------------------------------- |
| `phoneNumber` | `String`    | X   | X   | Customer's phone number.         |
| `pets`        | `List<Pet>` | X   | X   | Collection of customer's pets.   |

> Note: Remember to set `pets` to a new initialized `ArrayList`. You can do this in the property declaration or constructors.

### Constructors

`Customer` needs two constructors:

* One that accepts three `String` parameters for first name, last name, and phone number.
  * This constructor must set the phone number property, and call the base class constructor to set the first and last name.
* One that accepts two `String` parameters for first name and last name.
  * This constructor must call the first constructor with an empty string for phone number.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/crm/CustomerTest.java`.

## Step Five: Refactor the `Employee` class

Since an `Employee` IS A `Person`, you can now refactor `Employee` to be a subclass of `Person`. Doing this allows you to reduce duplicated code and adhere to the DRY Principle (Don't Repeat Yourself).

Start with adding the declaration to make the class extend `Person`. From when you created the `Person` class, you'll recognize some of the properties and methods in `Employee` with the same names.

### Properties

You can remove the following properties from the `Employee` class now that it uses the `Person` class properties. You can also remove their getters and setters from `Employee`:

* `firstName`
* `lastName`
* `email`

### Constructor error

After you make the preceding change, you'll see errors in the constructor that takes four parameters. This is because `this.firstName` and `this.lastName` are no longer accessible to `Employee`.

Remove the two lines with `this.firstName` and `this.lastName` and add a call to the base class constructor, passing the `firstName` and `lastName` parameters to it.

### Override methods

The `Employee` class has a `getFullName()` method, but unlike the one in `Person`, this one returns last name before the first name. Since you want to keep this implementation of `getFullName()`, add the override annotation to the method.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/hr/EmployeeTest.java`.

## Step Six: Refactor the `Manager` class

Now you can refactor `Manager` to be a subclass of `Employee`. You could inherit from `Person` instead, since a `Manager` IS A `Person`, however a `Manager` IS AN `Employee` too. Inheriting from `Employee` allows you to reuse the capabilities of `Employee` and `Person`.

Start with adding the declaration to make the class extend `Employee`. Again you'll recognize some of the properties with the same names.

### Properties

Since all the properties in `Manager` are duplicates of the ones in `Employee` and `Person`, you can remove all of the properties from the `Manager` class now that it uses the `Employee` class properties and the `Person` class properties. You can also remove their getters and setters too.

### Constructor error

After you make the preceding change, you'll see errors in the constructor that takes four parameters. Now the class has no properties it can access with `this`. Since this constructor has the same four parameters as the one in `Employee`. Remove the four lines and replace it with a call to it's base class constructor passing the four parameters.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/hr/ManagerTest.java`.

## Step Seven: Implement the `Billable` interface

You received an additional requirement to implement the `Billable` interface on the `Customer` class and the `Employee` class because employees can also be customers.

The `Billable` interface defines a method with the signature `BigDecimal getBalanceDue(Map<String, BigDecimal>)`. You need to implement this method in the `Customer` and `Employee` classes.

### Methods

| Method Name   | Return Type | Parameters                 |
| ------------- | ----------- | -------------------------- |
| `getBalanceDue` | `BigDecimal`  | `Map<String, BigDecimal>`    |

The `getBalanceDue` method returns the total amount the customer owes.

It accepts one parameter, a `Map` of services rendered:
* The key is a `String` representing the type of service—for example, "Grooming", "Walking", or "Sitting."
* The value is a `BigDecimal` representing the price for each service.

Employees receive a 50% discount on walking services, but the discount isn't applied in the `Map` provided. In the `Employee` implementation of the method, you'll have to calculate the discount.

### Unit tests

You can confirm your work by running the unit tests in `src/test/java/com/petelevator/BillableTest.java`.
