<template>
  <div class="modal-backdrop">
    <div class="modal" role="dialog">
      <header class="modal-header">
        <h3>{{$props.title}}</h3>
        <button class="icon-button" v-on:click="close"><font-awesome-icon icon="fa-solid fa-xmark" title="Close" /></button>
      </header>

      <section class="modal-body">
        <form id="form-facility-add-edit" v-on:submit.prevent="submit">
          <div class="form-control">
            <label for="visibility">Visibility:</label>
            <select id="visibility" v-model="newFacility">
              <option v-bind:value="true">Private</option>
              <option v-bind:value="true">Public</option>
            </select>
          </div>
          <div class="form-control">
            <label for="name">Facility Name:</label>
            <input type="text" id="name" size=80 v-model="newFacility.name" required />
          </div>
          <div class="form-control">
            <label for="address">Address:</label>
            <input type="text" id="address" size=80 v-model="newFacility.address" required />
          </div>
          <div class="form-control">
            <label for="phone number">Phone Number:</label>
            <input type="text" id="phone number" size="80" v-model="newFacility.phoneNumber" required />
          </div>
          <div class="form-control">
            <label for="cost">Cost Per Hour:</label>
            <input type="int" id="cost" size="80" v-model="newFacility.costPerHour" required />
          </div>
          <div class="button-group">
            <button type="submit" class="btn-primary">Save</button>
            <button v-on:click="close">Cancel</button>
          </div>
        </form>
       </section>
    </div>
  </div>
</template>

  <script>
  import facilityService from '../services/FacilityService';

  export default {
    props: { 
      facility: {
        type: Object,
        default: () => ({})
      }
    },
  data() {
    return {
      selectedFacilities: []
      }
   },
  
  created(){
  facilityService.getFacilities()
  .then( response => {
          this.facilities = response.data;
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
            this.$store.commit("LOGOUT");
            this.$router.push({name: "login"});
          } else {
            console.error('Error getting facilities.', response.message)
          }
        })
        this.init();
      },

      methods: {
    close() {
 
      this.$emit('close');
    },
    init() {
      if (this.$props.facility.facilityId) {
       
        this.newFacility = {...this.$props.facility, facilities: []}
        
        facilityService.getIdsForFacility(this.newFacility)
            .then(response => this.newFacility.facilityId = response.data)
            .catch(error => console.error('Error getting facility ids', error.response.message))
      } else {
        
        this.newFacility = {
          visibility: false,
          name: '',
          address: '',
          phoneNumber: '',
          facilityId: []
        }
      }
    },
    submit() {
    
      this.$store.commit("CLEAR_ERROR_NOTIFICATIONS");
      
      if (this.newFacility.facilityId) {
      
        facilityService.updateFacility(this.newFacility)
            .then(() => {
              // Post success message & close modal
              this.$store.commit("ADD_NOTIFICATION", {type: 'success', message: 'Facility updated!'});
              this.close();
            })
            .catch(error => {
              const response = error.response;
              if (response.status === 401) {
                // Token expired
                this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
                this.$store.commit("LOGOUT");
                this.$router.push({name: "login"});
              } else {
                // Something else unexpected happened
                let response = error.response;
                console.error('Could not update facility.', this.newFacility, response.message);
                this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, facilities could not be updated at this time.'});
              }
            })
      } else {
        facilityService.addFacility(this.newFacility)
            .then(response => {
              // Need to add the tags separately
              let facility = response.data;
              facility.facilityId = this.newFacility.facilityId;
              facilityService.updateFacility(facility)
                  .then(() => {
                    // Post success message & close modal
                    this.$store.commit("ADD_NOTIFICATION", {type: 'success', message: 'Facility added!'});
                    this.close();
                  })
                  .catch(error => {
                    const response = error.response;
                    if (response.status === 401) {
                      // Token expired
                      this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
                      this.$store.commit("LOGOUT");
                      this.$router.push({name: "login"});
                    } else {
                      // Something else unexpected happened
                      console.error('Could not add new facility ids.', this.facility, response.message);
                      this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, the facility was created but ids could not be added at this time.'});
                    }
                  })
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
                console.error('Could not add new facility.', this.newFacility, response.message);
                this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, the facility could not be added at this time.'});
              }
            })
      }
    }
  }
  };

  </script>
  
  <style scoped>
 .modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99;
}
 
.modal {
  background: #FFFFFF;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
  z-index: 999;
}
.modal-header {
  position: relative;
  background-color: rgba(136, 193, 231, 0.4);
  font-size: 20px;
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  padding: 0.5rem;
}

.modal-header h3 {
  margin-bottom: 0;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

form {
  margin-bottom: 1rem;
}

label {
  text-align: right;
  display: inline-block;
  margin-right: 15px;
  width: 150px;
}

input {
  display: inline-block;
}


button {
  border: 1px solid black;
  border-radius: 5px;
  padding: 0.25rem 0.5rem;
}

.button-group {
  margin-left: 165px;
}

.button-group button:not(:last-child) {
  margin-right: 15px;
}

.btn-primary {
  background-color: rgba(136, 193, 231, 0.4);
}

.form-control {
  display: flex;
  margin-bottom: 1rem;
  align-items: baseline;
}
  </style>
  