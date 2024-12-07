let display;
let previous = null;
let operator = null;
let operatorClicked = false;

/**
 * Calculates the operation and updates the display.
 */
function performOperation() {
  let result;
  const current = parseNumber(display.value);
  previous = parseNumber(previous);

  switch(operator) {
    case '+' :
      result = previous + current;
    break;
    case '-' :
        result = previous - current;
    break;
    case '*' :
        result = previous * current;
    break;
    case '/' :
        result = previous / current;
    break;
  }

  display.value = result;
  operator = null;
}

/**
 * Parses the display value into a number (float or int).
 * @param {String} num 
 */
function parseNumber(num) {
  return num.includes('.') ? parseFloat(num) : parseInt(num);
}

/**
 * Capture the previous value and the clicked operator
 * so that an operation can be performed.
 */
function clickOperator(event) {
  operator = event.target.value;
  previous = display.value;
  operatorClicked = true;
}

/**
 * Captures a number click and updates the display value.
 * @param {Event} event 
 */
function clickNumber(event) {
  const val = event.target.value;

  if( operatorClicked ) {
    display.value = val;
    operatorClicked = false;
  } else {
    display.value == 0 ? display.value = val : display.value += val;
  }

}

/**
 * Resets the display value.
 */
function clear() {
  display.value = 0;
}

// add event listener for when the DOM is loaded
document.addEventListener('LOADED_EVENT_GOES_HERE', () => {

  display = document.getElementById('display');

  const numberButtons = document.querySelectorAll('.number');
  numberButtons.forEach(button => {
    button.addEventListener('click', clickNumber);
  });
  // get a reference to the decimal point button
  const decimalButton = document.getElementById('decimal');
  // add a click event listener to call the function clickNumber
  decimalButton.addEventListener('click', clickNumber);

  // the decimal point is part of the number so you can call clickNumber for it 
  // as you would for a number

  // get a reference to the all clear button
  const clearButton = document.getElementById('clear');
  // add a click event listener to call the function clear  
  clearButton.addEventListener('click', clear);
  // get a reference to all of the operators;
  const operatorButtons = document.querySelectorAll('.operator');

  // loop over each of the operators
  operatorButtons.forEach(button => {
    button.addEventListener('click', clickOperator);
  });
  // add a click event listener to each operator to call the function clickOperator

  // add click event listener for the equal sign
  const equalButton = document.getElementById('equals');
  // should call the function performOperation
  equalButton.addEventListener('click', performOperation);
});

