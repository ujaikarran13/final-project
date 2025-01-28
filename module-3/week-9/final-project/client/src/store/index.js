import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import FacilityService from '../services/FacilityService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      facilities: []
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
      },
      ADD_FACILITIES(state, facilities) {
        state.facilities.push = facilities;
    },
      CLEAR_FACILITIES(state){
        state.facilities = [];
      }
    },
    actions: {
      async addFacilities({ commit }, facilities){
        commit("ADD_FACILITIES", facilities);
      },
      clearFacilities({ commit }) {
        commit("CLEAR_FACILITIES");
      }
    },
      async getFacilities({ commit }){
        try {
          const response = await FacilityService.getFacilities();
          commit ('SET_FACILITIES', response.data);
        } catch (error){
        console.error('Error fetching facilities:', error);
        }
      },
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
  );
  return store;
}