package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Availability;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAvailabilityDao implements AvailabilityDao {

    private static List<Availability> availabilities = new ArrayList<>();

//
//    public JdbcAvailabilityDao(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public JdbcAvailabilityDao() {
        if (availabilities.size() == 0){
            setAvailabilities();
        }
    }
@Override
public List<Availability> getAvailabilities(){
        return availabilities;
}


    @Override
    public List<Availability> getDoctorAvailabilityByDayOfWeek(String dayOfWeek) {
        List<Availability> matchAvailability = new ArrayList<>();
        if (!dayOfWeek.isEmpty()){
            for (Availability availability : availabilities){
                if(availability.getDayOfWeek().toLowerCase().contains(dayOfWeek.toLowerCase())){
                    matchAvailability.add(availability);
                }
            }
        }
        return matchAvailability;
    }

    private void setAvailabilities() {
        availabilities.add(new Availability(1,
                1,
                "Monday",
                "9:00:00",
                "17:00:00"));
        availabilities.add(new Availability(2,
                1,
                "Wednesday",
                "9:00:00",
                "17:00:00"));
        availabilities.add(new Availability(3,
                2,
                "Tuesday",
                "10:00:00",
                "18:00:00"));
        availabilities.add(new Availability(4,
                2,
                "Thursday",
                "10:00:00",
                "18:00:00"));

    }
}
