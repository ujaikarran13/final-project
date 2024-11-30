/*
1. **sumDouble** Given two int values, return their sum. Unless the two values are the 
    same, then return double their sum.

		sumDouble(1, 2) → 3
		sumDouble(3, 2) → 5
		sumDouble(2, 2) → 8

		function sumDouble(x, y) {
			// do logic here
			// return result;
			return x + y;
        }
*/
function sumDouble(x, y) {
    // If x and y are the same, return double their sum
    if (x === y) {
        return 2 * (x + y);
    }
    // If x and y are different, return their sum
    return x + y;
}

/*
2. **hasTeen** We'll say that a number is "teen" if it is in the range 13..19 inclusive. 
    Given 3 int values, return true if 1 or more of them are teen.

		hasTeen(13, 20, 10) → true
		hasTeen(20, 19, 10) → true
		hasTeen(20, 10, 13) → true
*/
function hasTeen(a, b, c) {
    // Check if any of the values a, b, or c are in the range of 13 to 19
    return (a >= 13 && a <= 19) || (b >= 13 && b <= 19) || (c >= 13 && c <= 19);
}

/* 
3. **lastDigit** Given two non-negative int values, return true if they have the same 
    last digit, such as with 27 and 57.

		lastDigit(7, 17) → true
		lastDigit(6, 17) → false
		lastDigit(3, 113) → true
*/
function lastDigit(a, b) {
    // Check if the last digit of a and b are the same
    return (a % 10 === b % 10);
}

/*
4. **seeColor** Given a string, if the string begins with "red" or "blue" return that color 
    string, otherwise return the empty string.

		seeColor("redxx") → "red"
		seeColor("xxred") → ""
        seeColor("blueTimes") → "blue"
*/
function seeColor(str) {
    // Check if the string starts with "red" or "blue"
    if (str.startsWith("red")) {
        return "red";
    } else if (str.startsWith("blue")) {
        return "blue";
    } else {
        return "";
    }
}

/*
5. **oddOnly** Write a function that given an array of integer of any length, removes
    the even numbers, and returns a new array of just the the odd numbers.

		oddOnly([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]) → [1, 3, 5, 7, 9, 11];
		oddOnly([2, 4, 8, 32, 256]); → []
*/
function oddOnly(arr) {
    // Use the filter method to return a new array with only odd numbers
    return arr.filter(num => num % 2 !== 0);
}

/*
6. **frontAgain** Given a string, return true if the first 2 chars in the string also appear 
    at the end of the string, such as with "edited".

		frontAgain("edited") → true
		frontAgain("edit") → false
		frontAgain("ed") → true
*/
function frontAgain(str) {
    // Check if the string has at least 2 characters and if the first two match the last two
    return str.length >= 2 && str.substring(0, 2) === str.substring(str.length - 2);
}


/*
7. **cigarParty** When squirrels get together for a party, they like to have cigars. 
A squirrel party is successful when the number of cigars is between 40 and 60, inclusive. 
Unless it is the weekend, in which case there is no upper bound on the number of cigars. 
Write a squirrel party function that return true if the party with the given values is successful, 
or false otherwise.

		cigarParty(30, false) → false
		cigarParty(50, false) → true
		cigarParty(70, true) → true
*/
function cigarParty(cigars, isWeekend) {
    // Check if it's a weekend or if the cigars count is within the acceptable range for weekdays
    if (isWeekend) {
        return cigars >= 40; // No upper limit for weekends
    } else {
        return cigars >= 40 && cigars <= 60; // Between 40 and 60 for weekdays
    }
}

/*
8. **fizzBuzz** Given a number, return a value according to the following rules:
If the number is multiple of 3, return "Fizz."
If the number is a multiple of 5, return "Buzz." 
If the number is a multiple of both 3 and 5, return "FizzBuzz."
In all other cases return the original number. 

	fizzBuzz(3) → "Fizz"
	fizzBuzz(1) → 1
	fizzBuzz(10) → "Buzz"
	fizzBuzz(15) → "FizzBuzz"
	fizzBuzz(8) → 8
*/
function fizzBuzz(n) {
    // Check if the number is divisible by both 3 and 5 first
    if (n % 3 === 0 && n % 5 === 0) {
        return "FizzBuzz";
    }
    // Check if the number is divisible by 3
    else if (n % 3 === 0) {
        return "Fizz";
    }
    // Check if the number is divisible by 5
    else if (n % 5 === 0) {
        return "Buzz";
    }
    // If none of the above, return the number itself
    else {
        return n;
    }
}

/*
9. **filterEvens** Write a function that filters an array to only include even numbers.

	filterEvens([]) → []
	filterEvens([1, 3, 5]) → []
	filterEvens([2, 4, 6]) → [2, 4, 6]
	filterEvens([100, 8, 21, 24, 62, 9, 7]) → [100, 8, 24, 62]
*/
function filterEvens(arr) {
    // Use the filter method to return an array containing only even numbers
    return arr.filter(num => num % 2 === 0);
}

/*
10. **filterBigNumbers** Write a function that filters numbers greater than or equal to 100.

	filterBigNumbers([7, 10, 121, 100, 24, 162, 200]) → [121, 100, 162, 200]
	filterBigNumbers([3, 2, 7, 1, -100, -120]) → []
	filterBigNumbers([]) → []
*/
function filterBigNumbers(arr) {
    // Use the filter method to return an array of numbers >= 100
    return arr.filter(num => num >= 100);
}

/*
11. **filterMultiplesOfX** Write a function to filter numbers that are a multiple of a 
parameter, `x` passed in.

	filterMultiplesOfX([3, 5, 1, 9, 18, 21, 42, 67], 3) → [3, 9, 18, 21, 42]
	filterMultiplesOfX([3, 5, 10, 20, 18, 21, 42, 67], 5) → [5, 10, 20]
*/
function filterMultiplesOfX(arr, x) {
    // Use the filter method to return an array of numbers that are multiples of x
    return arr.filter(num => num % x === 0);
}

/*
12. **createObject** Write a function that creates an object with a property called 
firstName, lastName, and age. Populate the properties with your values.

	createObject() →

	{
		firstName,
		lastName,
		age
	}
*/
function createObject() {
    // Return an object with the properties and values
    return {
        firstName: "YourFirstName",  // Replace with your actual first name
        lastName: "YourLastName",    // Replace with your actual last name
        age: 25                      // Replace with your actual age
    };
}
