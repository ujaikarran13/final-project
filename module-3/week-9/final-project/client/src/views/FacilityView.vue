<template>
  <div>
    <header class="facilities-list">
    <h2>Medical Facilities</h2>
    <button class="icon-button" v-on:click="addFacility">
      Add facility&nbsp;
      <font-awesome-icon icon="fa-solid fa-circle-plus" title="Add Facility" />
    </button>
    </header>
    <loading-spinner v-if="isLoading" />
    <div v-else id="view-facilities">
      <add-edit-facility-form v-show="showAddEditForm" v-on:close="resetAddEditForm" 
          v-bind:title="'Add Facility'" v-bind:facility="{}" />
      <facility-list />
      <facility-table v-bind:Facilities="Facilities" v-on:refresh:Facilities="getFacilites" />
    </div>
  </div>
</template>

<script>
import facilityService from "../services/FacilityService";
import AddEditFacilityForm from '../components/AddEditFacilityForm.vue';
import FacilityList from '../components/FacilityList.vue';
import FacilityTable from '../components/FacilityTable.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'

export default {
  components: { FacilityTable, FacilityList, AddEditFacilityForm, LoadingSpinner },
  created() {
    this.getFacilites();
  },
  data(){
    return {
      isLoading: true,
      Facilities: [],
      showAddEditForm: false
    }
  },
methods: {
  addFacility() {
    this.showAddEditForm = true;
  },
  getFacilites() {
    this.isLoading=true;
    facilityService.getFacilities()
    .then(response => {
      this.Facilities = response.data
      this.isLoading=false;
    })
    .catch(error => {
            this.isLoading=false;
            let response = error.response;
            if (response.status === 401) {
              this.$store.commit("ADD_NOTIFICATION",{type: 'error', message: 'Session timeout. Please login again'});
              this.$store.commit("LOGOUT");
              this.$router.push({name: "login"});
            } else {
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.'});
              let response = error.response;
              console.error('Getting facilities was unsuccessful: ', response.message);
            }
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
}

</style>../components/AddEditFacilityForm.vue
