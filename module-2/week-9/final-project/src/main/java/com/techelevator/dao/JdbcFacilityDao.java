package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Facility;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFacilityDao implements FacilityDao{

   private static List<Facility> facilities = new ArrayList<>();

   public JdbcFacilityDao(){
       if (facilities.size() == 0){
           setFacilities();
       }
   }


    @Override
    public Facility getFacilitiesByID(int facilityId) {
       for (Facility facility : facilities){
           if (facility.getFacilityId() == facilityId){
               return facility;
           }
       }
       return null;
    }

    @Override
    public Facility createFacility(Facility facility){
       facility.setFacilityId(getMaxIdPlusOne());
       facilities.add(facility);
       return facility;

    }


    @Override
public Facility updateFacility(Facility facility) {
        Facility result = facility;
        boolean finished = false;

        for (int i = 0; i < facilities.size(); i++) {
            if (facilities.get(i).getFacilityId() == facility.getFacilityId()) {
                facilities.set(i, result);
                finished = true;
                break;
            }
        }
        if (!finished) {
            throw new DaoException("Facility to update not found");
        }
        return result;
    }


        private void setFacilities () {
            facilities.add(new Facility(1,
                    "City Health Clinic",
                    "123 Main St, Springfield, IL 62701",
                    "555-1111",
                    100));
            facilities.add(new Facility(2,
                    "Downtown Medical Center",
                    "456 Elm St, Springfield, IL 62702",
                    "555-2222",
                    150));

        }

        private int getMaxIdPlusOne() {
            int maxId = 0;
            for (Facility facility : facilities) {
                if (facility.getFacilityId() > maxId) {
                    maxId = facility.getFacilityId();
                }
            }
            return maxId;
        }
    }

