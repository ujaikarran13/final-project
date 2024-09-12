package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final String PROJECT_SELECT = "SELECT p.project_id, p.name, p.from_date, p.to_date FROM project p";

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = null;
		String sql = PROJECT_SELECT +
				" WHERE p.project_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if (results.next()) {
			project = mapRowToProject(results);
		}

		return project;
	}

	@Override
	public List<Project> getProjects() {
		List<Project> allProjects = new ArrayList<>();
		String sql = PROJECT_SELECT;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Project projectResult = mapRowToProject(results);
			allProjects.add(projectResult);
		}

		return allProjects;
	}

	@Override
	public Project createProject(Project newProject) {
		try {
			String sql = "INSERT INTO project (name, from_date, to_date) VALUES (?, ?, ?) RETURNING project_id";
			int newId = jdbcTemplate.queryForObject(sql, new Object[]{
					newProject.getName(),
					newProject.getFromDate(),
					newProject.getToDate()
			}, Integer.class);

			newProject.setId(newId);
			return newProject;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed to create project due to data integrity violation", e);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Failed to create project due to connection issues", e);
		} catch (Exception e) {
			throw new DaoException("Failed to create project", e);
		}
	}

	
	@Override
	public void linkProjectEmployee(int projectId, int employeeId) {
		try {
			String sql = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)";
			jdbcTemplate.update(sql, projectId, employeeId);
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed to link project and employee due to data integrity violation", e);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Failed to link project and employee due to connection issues", e);
		} catch (Exception e) {
			throw new DaoException("Failed to link project and employee", e);
		}
	}

	@Override
	public void unlinkProjectEmployee(int projectId, int employeeId) {
		try {
			String sql = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
			jdbcTemplate.update(sql, projectId, employeeId);
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed to unlink project and employee due to data integrity violation", e);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Failed to unlink project and employee due to connection issues", e);
		} catch (Exception e) {
			throw new DaoException("Failed to unlink project and employee", e);
		}
	}

	@Override
	public Project updateProject(Project project) {
		try {
			String sql = "UPDATE project SET name = ?, from_date = ?, to_date = ? WHERE project_id = ?";
			jdbcTemplate.update(sql, project.getName(), project.getFromDate(), project.getToDate(), project.getId());
			return project; // Return the updated project
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed to update project due to data integrity violation", e);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Failed to update project due to connection issues", e);
		} catch (Exception e) {
			throw new DaoException("Failed to update project", e);
		}
	}
	@Override
	public int deleteProjectById(int projectId) {
		try {
			String sql = "DELETE FROM project WHERE project_id = ?";
			return jdbcTemplate.update(sql, projectId); // Returns the number of rows affected
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed to delete project due to data integrity violation", e);
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("Failed to delete project due to connection issues", e);
		} catch (Exception e) {
			throw new DaoException("Failed to delete project", e);
		}
	}
	
	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getInt("project_id"));
		project.setName(results.getString("name"));
		if (results.getDate("from_date") != null) {
			project.setFromDate(results.getDate("from_date").toLocalDate());
		}
		if (results.getDate("to_date") != null) {
			project.setToDate(results.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
