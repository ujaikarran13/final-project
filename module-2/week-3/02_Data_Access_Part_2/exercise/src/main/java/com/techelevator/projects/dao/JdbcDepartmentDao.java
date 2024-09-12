package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Department;

public class JdbcDepartmentDao implements DepartmentDao {

	private final String DEPARTMENT_SELECT = "SELECT d.department_id, d.name FROM department d ";

	private final JdbcTemplate jdbcTemplate;

	public JdbcDepartmentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Department getDepartmentById(int id) {
		Department department = null;
		String sql = DEPARTMENT_SELECT +
				" WHERE d.department_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if (results.next()) {
			department = mapRowToDepartment(results);
		}

		return department;
	}

	@Override
	public List<Department> getDepartments() {
		List<Department> department = new ArrayList<>();
		String sql = DEPARTMENT_SELECT;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			department.add(mapRowToDepartment(results));
		}

		return department;
	}

	@Override
	public Department createDepartment(Department department) {
		int departmentId;
		try {
			String sql = "INSERT INTO department (department_id, name) VALUES (?, ?) RETURNING department_id;";
			jdbcTemplate.queryForObject(sql, int.class, department.getId());
		} catch (Exception e) {
			throw new DaoException("Failed to insert department", e);
		}
		return department;
	}

	@Override
	public Department updateDepartment(Department department) {
		try{
			String sql = "UPDATE department SET name = ? WHERE department_id = ?";
			jdbcTemplate.update(sql, department.getName(), department.getId());
		} catch (Exception e){
			throw new DaoException("Failed to update department", e);
		}
		return getDepartmentById(department.getId());
	}


	@Override
	public int deleteDepartmentById(int id) {
        String sql;
        try {
            sql = "DELETE FROM department WHERE department_id = ?";
        } catch (Exception e) {
            throw new DaoException("Failed to delete department", e);
        }
        return jdbcTemplate.update(sql, id);
    }

	private Department mapRowToDepartment(SqlRowSet results) {
		Department department = new Department();
		department.setId(results.getInt("department_id"));
		department.setName(results.getString("name"));
		return department;
	}

}
