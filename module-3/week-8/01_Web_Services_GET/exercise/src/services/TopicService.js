import axios from "axios";

const API_URL = "http://localhost:3000";

export default {
    list() {
        return axios.get(`${API_URL}/topics`)
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching topics:', error);
            throw error;
          });
      },
      get(id) {
        return axios.get(`${API_URL}/topics/${id}`)
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching topic details:', error);
            throw error;
          });
      }
};


