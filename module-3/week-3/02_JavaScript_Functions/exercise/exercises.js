/**
 * Write a function called isAdmitted. It will check entrance
 * scores and return true if the student is admitted and
 * false if rejected. It takes three parameters:
 *
 *     * gpa
 *     * satScore (optional)
 *     * recommendation (optional)
 *
 * Admit them--return true--if:
 * gpa is above 4.0 OR
 * SAT score is above 1300 OR
 * gpa is above 3.0 and they have a recommendation OR
 * SAT score is above 1200 and they have a recommendation
 * OTHERWISE reject it
 *
 * @param {number} gpa the GPA of the student, between 4.2 and 1.0
 * @param {number} [satScore=0] the student's SAT score
 * @param {boolean} [recommendation=false] does the student have a recommendation
 * @returns {boolean} true if they are admitted
 */
function isAdmitted(gpa, satScore = 0, recommendation = false) {
    // Check the conditions for admission
    if (gpa > 4.0 || satScore > 1300) {
        return true; // Admit if GPA is above 4.0 or SAT score is above 1300
    } else if (gpa > 3.0 && recommendation) {
        return true; // Admit if GPA is above 3.0 and they have a recommendation
    } else if (satScore > 1200 && recommendation) {
        return true; // Admit if SAT score is above 1200 and they have a recommendation
    }
    return false; // Reject otherwise
}

/**
 * Write a function called useParameterToFilterArray that accepts a filter function
 * as a parameter. Use this function to filter unfilteredArray and return the result.
 *
 * @param {function} filterFunction the function to filter with
 * @returns {number[]} the filtered array
 */
let unfilteredArray = [1, 2, 3, 4, 5, 6];
function useParameterToFilterArray(filterFunction) {
    // Use the filter function passed as a parameter to filter the unfilteredArray
    return unfilteredArray.filter(filterFunction);
}
/**
 * Write a function called makeNumber that takes two strings
 * of digits, concatenates them together, and returns
 * the value as a number.
 *
 * So if two strings are passed in "42293" and "443", then this function
 * returns the number 42293443.
 *
 * @param {string} first the first string of digits to concatenate
 * @param {string} [second=''] the second string of digits to concatenate
 * @returns {number} the resultant number
 */
function makeNumber(first, second = '') {
    // Concatenate the two strings and convert to a number
    return Number(first + second);
}

/**
 * Write a function called addAll that takes an unknown number of parameters
 * and adds all of them together. Return the sum.
 *
 * @param {...number} num a series of numbers to add together
 * @returns {number} the sum of all the parameters (or arguments)
 */
function addAll(...num) {
    // Use reduce to sum up all numbers in the array
    return num.reduce((sum, current) => sum + current, 0);
}

/*
 * Write and document a function called makeHappy that takes
 * an array and prepends 'Happy ' to the beginning of all the
 * words and returns them as a new array. Use the `map` function.
 */

function makeHappy(words) {
    // Use map to create a new array with 'Happy ' prepended to each word
    return words.map(word => 'Happy ' + word);
}

/*
 * Write and document a function called getFullAddressesOfProperties
 * that takes an array of JavaScript objects. Each object contains the
 * following keys:
 *     * streetNumber
 *     * streetName
 *     * streetType
 *     * city
 *     * state
 *     * zip
 *
 * getFullAddressesOfProperties returns an array of strings. 
 * Each string is a mailing address generated from the data of a single JavaScript object. 
 * 
 * Each mailing address should have the following format:
 *    
 *  streetNumber streetName streetType city state zip
 *
 * Use `map` and an anonymous function.
 */
function getFullAddressesOfProperties(properties) {
    return properties.map(property => {
        // Concatenate the address information into a single string
        return `${property.streetNumber} ${property.streetName} ${property.streetType} ${property.city} ${property.state} ${property.zip}`;
    });
}
/** 
 * Write and document a function called findLargest that uses `forEach`
 * to find the largest element in an array.
 * The function must work for strings and numbers.
 * 
 * For strings, "largest" means the word coming last in lexographical order.
 * Lexographic is similar to alphabetical order except that 
 * capital letters come before lowercase letters: 
 * 
 * "cat" < "dog" but "Dog" < "cat"
 *
 * @param {number[]|string[]} searchArray the array to search
 * @returns {number|string} the number or string that is largest
 **/
function findLargest(searchArray) {
    let largest = searchArray[0]; // Initialize largest with the first element

    // Iterate through the array using forEach
    searchArray.forEach(item => {
        // Update largest if the current item is greater
        if (item > largest) {
            largest = item;
        }
    });

    return largest;
}

/*
 * CHALLENGE
 * Write and document a function called getSumOfSubArrayValues.
 *
 * Take an array of arrays, adds up all sub values, and returns
 * the sum. For example, if you got this array as a parameter:
 * [
 *   [1, 2, 3],
 *   [2, 4, 6],
 *   [5, 10, 15]
 * ];
 *
 * The function returns 48. To do this, you will use two nested `reduce`
 * calls with two anonymous functions.
 *
 * Read the tests to verify you have the correct behavior.
 */
function getSumOfSubArrayValues(arrayOfArrays) {
    // Outer reduce: Iterates over each sub-array, summing up its elements with inner reduce
    return arrayOfArrays.reduce((total, subArray) => {
        // Inner reduce: Sums up the elements of the sub-array
        return total + subArray.reduce((sum, value) => sum + value, 0);
    }, 0);
}