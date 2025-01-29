<template>
  <div>
    <header class="view-header">
    <h2>Doctors Offices & Medical Facilities</h2>
    <button class="icon-button" v-on:click="addFacility">
      Add New Facility&nbsp;
      <font-awesome-icon icon="fa-solid fa-circle-plus" title="Add Facility" />
    </button>
    </header>
    <loading-spinner v-if="isLoading" />
    <div v-else id="view-content">
      <add-edit-facility-form 
      v-show="showAddEditForm" 
      v-on:close="resetAddEditForm" 
      v-bind:title="'Add Facility'" 
      v-bind:facility="{}" />
      <facility-table v-bind:Facilities="Facilities" v-on:refresh:Facilities="getFacilities" />
    </div>
  </div>
</template>

<script>
import facilityService from "../services/FacilityService";
import AddEditFacilityForm from '../components/AddEditFacilityForm.vue';
import NotificationList from '../components/NotificationList.vue';
import FacilityTable from '../components/FacilityTable.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'

export default {
  components: { FacilityTable, AddEditFacilityForm, LoadingSpinner },
  created() {
    this.getFacilities();
  },
  data(){
    return {
      isLoading: true,
      Facilities: [],
      showAddEditForm: false,
      searchQuery: ''
    }
  },
methods: {
  addFacility() {
    this.showAddEditForm = true;
  },
  getFacilities() {
    this.isLoading=true;
    facilityService.getFacilities()
    .then(response => {
      this.Facilities = response.data
      this.isLoading=false;
    })
    .catch(error => {
    this.isLoading = false;
    if (error.response) {
      const response = error.response;
      if (response.status === 401) {
        this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Session timeout. Please login again' });
        this.$store.commit("LOGOUT");
        this.$router.push({ name: "login" });
      } else {
        this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.' });
        console.error('Error fetching facilities: ', response.message);
      }
    } else {
      this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Network error. Please check your connection.' });
      console.error('Network error: ', error);
    }
  });
},
searchFacilities() {
  if (this.searchQuery.trim() !== '') {
        this.isLoading = true;
        facilityService.searchFacilities(this.searchQuery)
          .then(response => {
            this.Facilities = response.data;
            this.isLoading = false;
          })
          .catch(error => {
            this.isLoading = false;
            this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Error searching for facilities' });
            console.error('Error searching facilities: ', error);
          });
      }
    },
 
  addFacilityFromForm(facility){
    this.isLoading = true;
      facilityService.addFacility(facility)
        .then(response => {
          this.Facilities.push(response.data);
          this.isLoading = false;
          this.showAddEditForm = false;
  })
  .catch(error => {
          this.isLoading = false;
          this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Error adding new facility' });
          console.error('Error adding facility: ', error);
        });
    },
    deleteFacility(facilityId) {
      this.isLoading = true;
      facilityService.deleteFacilityById(facilityId)
        .then(response => {
          this.Facilities = this.Facilities.filter(facility => facility.facilityId !== facilityId);
          this.isLoading = false;
        })
        .catch(error => {
          this.isLoading = false;
          this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Error deleting facility' });
          console.error('Error deleting facility: ', error);
        });
    },
    getPhoneNumbersForFacility(facilityId) {
      this.isLoading = true;
      facilityService.getPhoneNumbersForFacility(facilityId)
        .then(response => {
          console.log('Phone number for facility: ', response.data);
          this.isLoading = false;
        })
        .catch(error => {
          this.isLoading = false;
          this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Error fetching phone number' });
          console.error('Error getting phone number: ', error);
        });
    },

    getAllNumbers() {
      this.isLoading = true;
      facilityService.getAllNumbers()
        .then(response => {
          console.log('All phone numbers: ', response.data);
          this.isLoading = false;
        })
        .catch(error => {
          this.isLoading = false;
          this.$store.commit("ADD_NOTIFICATION", { type: 'error', message: 'Error fetching all phone numbers' });
          console.error('Error fetching all phone numbers: ', error);
        });

      },

  resetAddEditForm(){
    this.showAddEditForm=false;
    this.getFacilities()
  }
}
};
</script>

<style scoped>
.view-header {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  flex-wrap: wrap;
  padding: 10px;
}

.icon-button {
  display: inline-flex;
  align-items: center;
  padding: 8px;
  margin: 5px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.icon-button:hover {
  background-color: #45a049;
}

#view-content {
  padding: 20px;
}

@media (max-width: 1040px) {
  .view-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .icon-button {
    margin-top: 10px;
  }
}
@media (max-width: 425px) {
  .view-header h2 {
    font-size: 18px; 
    text-align: center;
  }
  .icon-button {
    width: 100%; 
    text-align: center;
    margin-top: 10px;
  }
  #view-content {
    padding: 10px; 
  }
}
</style>../components/AddEditFacilityForm.vue
