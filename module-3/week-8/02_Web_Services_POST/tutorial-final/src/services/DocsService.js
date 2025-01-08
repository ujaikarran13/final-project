import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  list() {
    return http.get('/docs');
  },

  get(id) {
    return http.get(`/docs/${id}`)
  },

  create(doc) {
    return http.post(`/docs/`, doc);
  },

  update(id, doc) {
    return http.put(`/docs/${id}`, doc);
  },

  delete(id) {
    return http.delete(`/docs/${id}`);
  }

}
