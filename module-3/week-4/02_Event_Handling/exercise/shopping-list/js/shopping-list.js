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
  ul.innerHTML= '';

  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.textContent = item.name;

    if (item.completed) {
      li.classList.add('completed');
    }
    const checkCircle = document.createElement('i');
    checkCircle.addEventListener('click', () => markItemComplete(item, li, checkCircle));
    checkCircle.addEventListener('dblclick', () => markItemIncomplete(item, li, checkCircle));
    li.appendChild(checkCircle);
    ul.appendChild(li);
  });

  function markItemComplete(item, li, checkCircle) {
    item.completed = !item.completed;
    if (!item.completed) {
      item.completed = true;
      li.classList.add('completed');
      checkCircle.classList.remove('far');
      checkCircle.classList.add('fas');
    }
  }
  function markItemIncomplete(item, li, checkCircle) {
    if (item.completed) {
      item.completed = false;
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
    document.querySelector('.btn').textContent = allItemsIncomplete ? 'Mark All Complete' : 'Mark All Incomplete';
    displayGroceries(); 
  }
  document.addEventListener('DOMContentLoaded', () => {
    setPageTitle();
    displayGroceries();
  });
  document.querySelector('.btn').addEventListener('click', markCompleted);
}


