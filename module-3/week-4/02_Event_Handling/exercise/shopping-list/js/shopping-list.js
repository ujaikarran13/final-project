let allItemsIncomplete = true;
const pageTitle = 'My Shopping List';
const groceries = [
  { id: 1, name: 'Oatmeal', completed: false },
  { id: 2, name: 'Milk', completed: false },
  { id: 3, name: 'Banana', completed: false },
  { id: 4, name: 'Strawberries', completed: false },
  { id: 5, name: 'Lunch Meat', completed: false },
  { id: 6, name: 'Bread', completed: false },
  { id: 7, name: 'Grapes', completed: false },
  { id: 8, name: 'Steak', completed: false },
  { id: 9, name: 'Salad', completed: false },
  { id: 10, name: 'Tea', completed: false }
];

/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function setPageTitle() {
  const title = document.getElementById('title');
  title.textContent = pageTitle;
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() {
  const ul = document.querySelector('ul');
  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.textContent = item.name;

    if (item.completed) {
      li.classList.add('completed');
    }
    const checkCircle = document.createElement('i');
    checkCircle.setAttribute('class', 'far fa-check-circle');
    checkCircle.addEventListener('click', () => toggleCompletion(item, li, checkCircle));
    li.appendChild(checkCircle);
    ul.appendChild(li);
  });

  function toggleCompletion(item, li, checkCircle) {
    item.completed = !item.completed;
    if (item.completed) {
      li.classList.add('completed');
      checkCircle.classList.remove('far');
      checkCircle.classList.add('fas');
    } else {
      li.classList.remove('completed');
      checkCircle.classList.remove('fas');
      checkCircle.classList.add('far');
    }
  }
  function markCompleted() {
    groceries.forEach(item => {
      item.completed = allItemsIncomplete;
    });
    allItemsIncomplete = !allItemsIncomplete;
    displayGroceries();
  }
  setPageTitle();
  displayGroceries();

  document.querySelector('.btn').addEventListener('click', markCompleted);
}
