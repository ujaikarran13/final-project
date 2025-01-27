import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: localStorage.getItem('token') || '',
      user: JSON.parse(localStorage.getItem('user')) || {},
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
    actions: {
      login({ commit }, user) {
        return axios.post('/login', user)
          .then(response => {
            commit('SET_AUTH_TOKEN', response.data.token);
          });
      },
      register({ commit }, user) {
        return axios.post('/register', user);
      }
    }
  });
  return store;
}