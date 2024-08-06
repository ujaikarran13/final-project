# Review day 3 exercises

The purpose of this exercise is build more experience using Java collection objects. There are six exercises in total—three for `List`s and three for `Map`s. Each exercise includes instructions on what to do, as well as example input and output values. There are unit tests provided to validate your work.

## Getting started

1. Open the exercises project in IntelliJ.
2. Run all tests to see the results of your tests and which ones passed or failed.
3. Write your code in the provided classes in the project.
4. Provide enough code to get a test passing.
5. Repeat until all tests are passing.

## Tips and tricks

Here are some tips that may help you as you work on your exercises.

### Read the problem description carefully

Before each method, there's a description of the problem you need to solve and examples with the expected output. Use these examples to figure out the values you need to write your code around. It may help to keep track of the state of variables on a piece of paper as you work on your exercises.

For example, in the comments before the `noStartingVowels` method, there's a section that includes the method name and the expected value that's returned for each method call. The following example shows that when calling the method with `{"Tooth", "Easy", "Mirror"}`, it returns a `List<String>` that contains two elements: "Tooth" and "Mirror."

```java
noStartingVowels( {"Tooth", "Easy", "Mirror"} )  ->  {"Tooth", "Mirror"}
```

### Check test output if your tests are failing

If your tests fail, check the output of the test run. It provides helpful clues and information that could be valuable when troubleshooting. You can look at the unit tests to see the provided input, expected output, and actual output.

You can also run the tests in debug mode when running the tests. This allows you to set a "breakpoint", which stops the code at certain points in the editor. You can then look at the values of variables while the test is running, and can also see what code is getting run. Don't hesitate to use the debugging capabilities in IntelliJ to help resolve issues.

### Don't linger too long on one problem

If you find yourself stuck on a problem more than fifteen minutes, move on to the next, and try again later. You may figure out the solution after working through another problem or two.

### Read the documentation

As a developer, you'll find documentation for classes and libraries to be invaluable resources when completing your work. Reading and understanding documentation now can help you in the long run.

[List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html)
[ArrayList](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
[Map](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html)
[HashMap](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashMap.html)
