<template>
  <header>
    Welcome to your User Dashboard!
  </header>
  <div id="bookAppointment">
    <form v-on:submit.prevent="register">
      <h1>Request an Appointment</h1>
      <div id="fields">
        <label for="firstname">First Name</label>
        <input
          type="text"
          id="firstname"
          placeholder="Enter first name"
          v-model="AppointmentRequest.firstname"
          required
          autofocus
        />
        <label for="lastname">Last Name</label>
        <input
          type="text"
          id="lastname"
          placeholder="Enter last name"
          v-model="AppointmentRequest.lastname"
          required
        />
        <label for="dob">Date Of Birth</label>
        <input
          type="text"
          id="dob"
          placeholder="Enter date of birth"
          v-model="AppointmentRequest.dob"
          required
        />
        <label for="appointment">Choose an appointment date:</label>
        <input 
        type="date" 
          id="appointmentDate" 
          v-model="AppointmentRequest.appointmentDate"
          required />
        <div></div>
        <div>
          <button type="submit">Request Appointment</button>
        </div>
      </div>
      <hr />
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      AppointmentRequest: {
        firstname: "",
        lastname: "",
        dob: "",
        appointmentDate: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
     
      console.log(this.AppointmentRequest);
           
    },
  },
};
</script>

<style scoped>

#bookAppointment {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  background-color: rgb(192, 245, 192);
  border-radius: 8px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 2em;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Dancing Script', cursive;
}
header {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
#fields {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

label {
  font-size: 1.1em;
  color: #333;
  margin-bottom: 5px;
  font-family: 'Dancing Script', cursive;
}

input {
  padding: 10px;
  font-size: 1em;
  border: 1px solid #a4cd9e;
  border-radius: 5px;
  margin-bottom: 15px;
  width: 100%;
  box-sizing: border-box;
}

button {
  padding: 12px;
  font-size: 1.1em;
  font-family: 'Dancing Script', cursive;
  background-color: #74cd80;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
}

button:hover {
  background-color: #36c74b;
}


</style>
