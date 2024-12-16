/*
    app.js

*/

import { getProducts, searchProducts } from './productService.js';

const searchBar = document.getElementById('searchBar');
const productCardsContainer = document.getElementById('product-cards');

searchBar.addEventListener('keyup', function () {
  const searchTerm = searchBar.value.trim().toLowerCase();
  const filteredProducts = searchTerm ? searchProducts(searchTerm) : getProducts();
  displayProducts(filteredProducts);
});


function formatCurrency(amount) {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
    }).format(amount);
  }

function createProductCard(product) {
  const card = document.createElement('div');
  card.classList.add('product-card');
  card.setAttribute('data-id', product.productId);

  card.innerHTML = `
  <div class = "product-image">
    <img src="${product.imageName}" alt="${product.name}">
  </div>
  <div class="product-price">$${product.price.toFixed(2)}</div>
  <div class="product-name action">${product.name}</div>
  <div class="product-description">${product.description}</div>
  <div class="productId">${product.productId}</div>
  <div class="productSku">${product.productSku}</div>
  <!-- Cart icon positioned at the bottom right -->
  <div class="add-to-cart">
    <i class="fa-solid fa-cart-plus"></i>
  </div>
`;

  addProductNameEventHandler(card);
  addCartEventHandler(card);
  return card;
}

function displayProducts(products) {
    productCardsContainer.innerHTML = ''; 
    products.forEach(product => {
      const card = createProductCard(product);
      productCardsContainer.appendChild(card);
    });
  }

  function addProductNameEventHandler(card) {
    const productName = card.querySelector('.product-name');
    productName.addEventListener('click', function (ev) {
      const productId = card.getAttribute('data-id');
     
      const id = Number(ev.currentTarget.closest('.product-card').getAttribute('data-id'));
      const product = getProducts().find(p => p.productId === id);
      displayMessage(`Product: ${product.name} - ${product.description}`);
    
    });
  }

  function addCartEventHandler(card) {
    const cartIcon = card.querySelector('.add-to-cart');
    cartIcon.addEventListener('click', function () {
      displayMessage('Item added to the cart!');
    });
  }

  const messageWindow = document.getElementById('message-window');

function displayMessage(message) {
    const messageWindow = document.getElementById('message-window');
    messageWindow.textContent = message;
    messageWindow.classList.remove('hidden');
  }

function hideMessage() {
    const messageWindow = document.getElementById('message-window');
    messageWindow.classList.add('hidden');
  }

  displayProducts(getProducts());
 







