<template>
    <p v-if="facilities.length === 0">
      No facilities to show. To get started, add a new Facility.
    </p>
    <div v-else>
      <add-edit-facility-form v-if="showAddEditForm" v-on:close="resetAddEditForm"
              v-bind:facility="selectedFacility" v-bind:title="'Edit Facility'" />
      <confirmation-dialog v-if="showDeleteConfirmation" 
              v-on:ok="deleteFacility" v-on:cancel="cancelDelete"
              okButton="Delete" cancelButton="Cancel"
              v-bind:message="'Are you sure you want to delete this facility?'" />
      <div id="facility-counts">{{ facilityCounts }}</div>
      <table v-if="!isLoading && !errorMessage">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Cost</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="facility in facilities" v-bind:key="facility.facilityId">
            <td>{{facility.facilityId}}</td>
            <td>{{facility.facilityName}}</td>
            <td>{{facility.address}}</td>
            <td>{{facility.phoneNumber}}</td>
            <td>{{facility.costPerHour}}</td>
            </tr>
        </tbody>
      </table>
      <AddEditFacilityForm v-if="showAddEditForm" />
      <confirmationDialog v-if="showDeleteConfirmation" />
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import facilityService from "../services/FacilityService";
  import confirmationDialog from './ConfirmationDialog.vue';
  import AddEditFacilityForm from './AddEditFacilityForm.vue';
  
  export default {
    components: {confirmationDialog, 
      AddEditFacilityForm},

    data() {
      return {
        facilities: [],  
        showAddEditForm: false,
        showDeleteConfirmation: false,
        isLoading: true,
      }
    },
    mounted(){
      this.fetchFacilities();
    },

    methods: {
      async fetchFacilities() {
      try {
        const response = await axios.get('http://localhost:9000/facilities');  
        this.facilities = response.data; 
        this.isLoading = false;  
      } catch (error) {
        this.isLoading = false;  
        this.errorMessage = 'Failed to load facilities. Please try again later.';
        console.error(error);
      }
    },
      editFacility(facility) {
        this.selectedFacility = facility;
        this.showAddEditForm = true;
      },
      togglePublic(facility) {
        // Make a copy of the facility to modify and send to server.
        let modified = {...facility};
        modified.private = !modified.private;
        facilityService.updateFacility(modified)
          .then(() => {
            // Tell the parent component to refresh facilities
            this.$emit('refresh:facilities');
            // Post success message
            this.$store.commit("ADD_NOTIFICATION", {type: 'success', message: 'Facility updated.'});
          })
          .catch(error => {
            let response = error.response;
            if (response.status === 401) {
              // Token expired
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
              this.$store.commit("LOGOUT");
              this.$router.push({name: "login"});
            } else {
              // Something else unexpected happened
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.'});
              console.error('Something unexpected happened when toggling Facility public/private', response.status, response.message);
            }
          });
      },
      promptDeleteFacility(facility) {
        this.selectedFacility = facility;
        this.showDeleteConfirmation = true;
      },
      cancelDelete() {
        this.selectedFacility={};
        this.showDeleteConfirmation=false;
      },
      deleteFacility() {
        facilityService.deleteFacilityById(this.selectedFacility.facilityId)
          .then(() => {
            // Tell the parent component to refresh Facilities
            this.$emit('refresh:facilities');
            // Post success message
            this.$store.commit("ADD_NOTIFICATION", {type: 'success', message: 'Facility deleted.'});
          })
          .catch(error => {
            let response = error.response;
            if (response.status === 401) {
              // Token expired
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
              this.$store.commit("LOGOUT");
              this.$router.push({name: "login"});
            } else {
              // Something else unexpected happened
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.'});
              console.error('Something unexpected happened when deleting Facility', response.status, response.message);
            }
          });
      },
      resetAddEditForm() {
        this.selectedFacility={};
        this.showAddEditForm=false;
        // Tell the parent component to refresh facilities
        this.$emit('refresh:facilities');
      }
    },
    computed: {
      facilityCounts() {
        const privateCount = this.facilities.filter(b => !b.public).length
        const privateString = `${privateCount} private ${privateCount == 1 ? "facility" : "facilities"}`;
        return `You have ${privateString}`;
      }
    }
  }
  </script>
  
  <style scoped>
  

  #facility-counts {
    font-size: 0.8rem;
    margin-bottom: 10px;
  }
  th, td {
    padding: 10px;
    text-align: left;
    border: 1px solid #050505;
    background-color: #add78b;
  }
  </style>