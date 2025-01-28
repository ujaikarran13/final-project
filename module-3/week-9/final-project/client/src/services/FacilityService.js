import axios from 'axios';

export default {
  getFacilities(){
    return axios.get('/facilities');
  },  
  searchFacilities(searchString){
    return axios.get('/facilities'+ searchString);
  },
  addFacility(facility){
    return axios.post('/facilities', facility);
  },
  updateFacility(facilityId, facility){
    return axios.put(`/facilities/${facilityId}`, facility);
  },  
  deleteFacilityById(facilityId){
    return axios.delete(`/facilities/${facilityId}`);
  },
  getPhoneNumbersForFacility(facility){
    return axios.get(`/facilities/${facility.facilityId}/phoneNumber`);
  },
  getAllNumbers() {
    return axios.get('')
  }
};