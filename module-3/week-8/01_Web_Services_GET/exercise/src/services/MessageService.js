import axios from "axios";

const API_URL = "http://localhost:3000";

export default {
  get(id) {
    return fetch(`${API_URL}/messages/${id}`)
      .then(response => response.data())
      .catch(error => {
        console.error("Error fetching message:", error);
        throw error;  
      });
  }
};


