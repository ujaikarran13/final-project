import axios from 'axios';

export default {
  getFacilities(){
    return axios.get('/facilities');
  },  
  getFacilitiesByID(facilityId){
    return axios.get(`/facilities/${facilityId}`);
  },
  searchFacilities(name){
    return axios.get(`/facilities/search?name=${name}`);
  },
  addFacility(facility){
    return axios.post('/facilities', facility);
  },
  updateFacility(facilityId, modifiedFacility){
    return axios.put(`/facilities/${facilityId}`, modifiedFacility);
  },  
  deleteFacilityById(facilityId){
    return axios.delete(`/facilities/${facilityId}`);
  },
  getPhoneNumbersForFacility(facilityId){
    return axios.get(`/facilities/${facilityId}/phoneNumber`);
  },
  getAllNumbers() {
    return axios.get('/facilities/phoneNumber');
  }
};