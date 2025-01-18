import { createRouter as _createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import MyBooksView from '../views/MyBooksView.vue';
import NewBookView from '../views/NewBookView.vue';
import BookDetailsView from '../views/BookDetailsView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/myBooks',
    name: 'myBooks',
    component: MyBooksView
  },
  {
    path: '/addBook',
    name: 'addBook',
    component: NewBookView
  },
  {
    path: '/book/:isbn',
    name: 'bookDetails',
    component: BookDetailsView
  },
];

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
