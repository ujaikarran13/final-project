const shoppingList = document.getElementById('groceries');

let allItemsIncomplete = true;
let groceries = [];

function init(){
 const pageTitle = 'My Shopping List';
groceries = [
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
}


function setPageTitle() {
  const pageTitle = document.querySelector('#title'); 
  pageTitle.textContent = "My Shopping List";
}

function displayGroceries() {
  const ul = document.querySelector('ul');
  ul.innerHTML= '';

  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.setAttribute('data-id', item.id); 
    li.textContent = item.name;

    const checkCircle = document.createElement('i');
    checkCircle.classList.add('far'); 

    if (item.completed) {
      checkCircle.classList.remove('far');
      checkCircle.classList.add('fas');
      li.classList.add('completed');
    }

    li.appendChild(checkCircle);  
    ul.appendChild(li);  
  });
}

function markItemComplete(item, li, checkCircle) {
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

function toggleAllItems() {
  const button = document.querySelector('#toggleAll');

  if (allItemsIncomplete) {
    groceries.forEach(item => {
      item.completed = true; 
    });
    button.textContent = 'Mark All Incomplete';  
  } else {
    groceries.forEach(item => {
      item.completed = false;
    });
    button.textContent = 'Mark All Complete'; 
  }

  allItemsIncomplete = !allItemsIncomplete; 
  displayGroceries();  
}
document.addEventListener('DOMContentLoaded', () => {
  init();
  setPageTitle();
  displayGroceries();
  const ul = document.querySelector('ul');
  ul.addEventListener('click', (event) => {
    const target = event.target;
    const li = target.closest('li');
    const itemId = li?.getAttribute('data-id');
    const item = groceries.find(grocery => grocery.id == itemId);
    const checkCircle = li.querySelector('i');
    if (target === checkCircle) {
      markItemComplete(item, li, checkCircle);
    }
  });
  ul.addEventListener('dblclick', (event) => {
    const target = event.target;
    const li = target.closest('li');
    const itemId = li?.getAttribute('data-id');
    const item = groceries.find(grocery => grocery.id == itemId);
    const checkCircle = li.querySelector('i');
    
   
    if (target === checkCircle) {
      markItemIncomplete(item, li, checkCircle);
    }
  });
  const toggleButton = document.querySelector('#toggleAll');
  toggleButton.addEventListener('click', toggleAllItems);
});