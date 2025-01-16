<template>
  <div class="container">
    <table id="tblUsers">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Email Address</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="checkbox" id="selectAll" v-model="selectAllChecked" @change="toggleSelectAll" />
          </td>
          <td>
            <input id="firstNameFilter" type="text" v-model="filter.firstName" placeholder="First Name" />
          </td>
          <td>
            <input id="lastNameFilter" type="text" v-model="filter.lastName" placeholder="Last Name" />
          </td>
          <td>
            <input id="usernameFilter" type="text" v-model="filter.username" placeholder="Username" />
          </td>
          <td>
            <input id="emailFilter" type="text" v-model="filter.emailAddress" placeholder="Email" />
          </td>
          <td>
            <select id="statusFilter" v-model="filter.status">
              <option value>Show All</option>
              <option value="Active">Active</option>
              <option value="Inactive">Inactive</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="user in filteredList"
          v-bind:key="user.id"
          v-bind:class="{ 'deactivated': user.status === 'Inactive' }">
          <td>
            <input type="checkbox" v-bind:id="user.id" v-bind:value="user.id" v-model="selectedUserIds" />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.emailAddress }}</td>
          <td>{{ user.status }}</td>
          <td>
            <button class="btnActivateDeactivate" @click="toggleUserStatus(user.id)">
              {{ user.status === "Active" ? "Deactivate" : "Activate" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="all-actions">
      <button :disabled="selectedUserIds.length === 0" @click="activateUsers">Activate Users</button>
    <button :disabled="selectedUserIds.length === 0" @click="deactivateUsers">Deactivate Users</button>
    <button :disabled="selectedUserIds.length === 0" @click="deleteUsers">Delete Users</button>
    </div>

    <button @click="toggleNewUserForm">Add New User</button>
    <form id= "frmAddNewUser" v-show="showNewUserForm" @submit.prevent="addNewUser">
      <div class="field">
        <label for="firstName">First Name:</label>
        <input type="text" v-model="newUser.firstName" id="firstName" name="firstName" />
      </div>
      <div class="field">
        <label for="lastName">Last Name:</label>
        <input type="text" v-model="newUser.lastName" id="lastName" name="lastName" />
      </div>
      <div class="field">
        <label for="username">Username:</label>
        <input type="text" v-model="newUser.username" id="username" name="username" />
      </div>
      <div class="field">
        <label for="emailAddress">Email Address:</label>
        <input type="text" v-model="newUser.emailAddress" id="emailAddress" name="emailAddress" />
      </div>
      <button type="submit" class="btn save">Save User</button>
    </form>
  </div>
</template>

<script>
export default {
 
  data() {
    return {
      showNewUserForm: false,
      selectAllChecked: false,
      newUser: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: ""
      },
      nextUserId: 7,
      selectedUserIds: [],
      filter: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: ""
      },
      users: [
        {
          id: 1,
          firstName: "John",
          lastName: "Smith",
          username: "jsmith",
          emailAddress: "jsmith@gmail.com",
          status: "Active"
        },
        {
          id: 2,
          firstName: "Anna",
          lastName: "Bell",
          username: "abell",
          emailAddress: "abell@yahoo.com",
          status: "Active"
        },
        {
          id: 3,
          firstName: "George",
          lastName: "Best",
          username: "gbest",
          emailAddress: "gbest@gmail.com",
          status: "Inactive"
        },
        {
          id: 4,
          firstName: "Ben",
          lastName: "Carter",
          username: "bcarter",
          emailAddress: "bcarter@gmail.com",
          status: "Active"
        },
        {
          id: 5,
          firstName: "Katie",
          lastName: "Jackson",
          username: "kjackson",
          emailAddress: "kjackson@yahoo.com",
          status: "Active"
        },
        {
          id: 6,
          firstName: "Mark",
          lastName: "Smith",
          username: "msmith",
          emailAddress: "msmith@foo.com",
          status: "Inactive"
        }
      ]
    };
  },
  watch: {
    selectAllChecked(newValue) {
      if (newValue) {
        this.selectedUserIds = this.users.map(user => user.id);
      } else {
        this.selectedUserIds = [];
      }
    }
  },
  methods: {
    addNewUser(){
    this.newUser.id = this.getNextUserId();
    this.users.push({...this.newUser })
    this.clearNewUserForm();
  },
    toggleSelectAll(){
      this.selectAllChecked = !this.selectAllChecked;
    },
    activateUsers() {
      this.selectedUserIds.forEach(id => {
        const user = this.users.find(u => u.id === id);
        if (user) user.status = "Active";
      });
      this.clearSelection(); 
    },
    deactivateUsers() {
      this.selectedUserIds.forEach(id => {
        const user = this.users.find(u => u.id === id);
        if (user) user.status = "Inactive";
      });
      this.clearSelection(); 
    },
    deleteUsers() {
      this.users = this.users.filter(user => !this.selectedUserIds.includes(user.id));
      this.clearSelection(); 
    },
    clearSelection() {
      this.selectedUserIds = [];
    },

    toggleUserStatus(userId){
      const user = this.users.find(u => u.id === userId);
      if (user){
        user.status = user.status === "Active" ? "Inactive" : "Active";
      }
    },
    toggleNewUserForm(){
      this.showNewUserForm = !this.showNewUserForm;
    },
    getNextUserId() {
      return this.nextUserId++;
    },
  },
 
  clearNewUserForm(){
    this.newUser= {
      id: null,
      firstName: "",
      lastName: "",
      username: "",
      emailAddress: "",
      status: "Active"
    };
      this.showNewUserForm = false; 
    },
  

  computed: {
    filteredList() {
      let filteredUsers = this.users;
      if (this.filter.firstName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase())
        );
      }
      if (this.filter.lastName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      if (this.filter.emailAddress != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.emailAddress
            .toLowerCase()
            .includes(this.filter.emailAddress.toLowerCase())
        );
      }
      if (this.filter.status != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.status === this.filter.status
        );
      }
      return filteredUsers;
    }
  }
};
</script>

<style scoped>
table {
  margin-top: 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  margin-bottom: 20px;
}
th {
  text-transform: uppercase;
}
td {
  padding: 10px;
}
tr.deactivated {
  color: red;
}
input,
select {
  font-size: 16px;
}

form {
  margin: 20px;
  width: 350px;
}
.field {
  padding: 10px 0px;
}
label {
  width: 140px;
  display: inline-block;
}
button {
  margin-right: 5px;
}
.all-actions {
  margin-bottom: 40px;
}
.btn.save {
  margin: 20px;
  float: right;
}
</style>
