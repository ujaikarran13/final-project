package com.techelevator.dao;

import com.techelevator.model.DoctorFacility;
import com.techelevator.model.Facility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcDoctorFacilityDao implements DoctorFacilityDao{

    private final String DOCTOR_FACILITY_SELECT = "SELECT d.facility_ID, d.doctor_ID";
    private final JdbcTemplate jdbcTemplate;

    public JdbcDoctorFacilityDao (DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcDoctorFacilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DoctorFacility> getDoctorsAndFacilities(int DoctorID, int FacilityID) {

        List<DoctorFacility> allDoctorsAndFacilities = new ArrayList<>();
        String sql = DOCTOR_FACILITY_SELECT +
                "JOIN DoctorFacilities df ON a.doctor_id = df.doctor_id  " +
                "WHERE df.doctor_id = ? ";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, DoctorID);
        while (results.next()){
            DoctorFacility doctorFacilityResult = mapRowToDoctorFacility(results);
            allDoctorsAndFacilities.add(doctorFacilityResult);
        }
        return allDoctorsAndFacilities;
    }

    private DoctorFacility mapRowToDoctorFacility(SqlRowSet results) {

        DoctorFacility doctorFacility = new DoctorFacility();
        doctorFacility.setDoctorId(results.getInt("facility_ID"));
        doctorFacility.setFacilityId(results.getInt("doctor_ID"));

        return doctorFacility;

    }
}
