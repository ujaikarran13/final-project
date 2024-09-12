package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private static final RowMapper<Person> PERSON_ROW_MAPPER = (rs, rowNum) -> {
        Person person = new Person();
        person.setId(rs.getInt("person_id"));
        person.setName(rs.getString("person_name"));
        person.setBirthday(rs.getDate("birthday").toLocalDate());
        person.setBiography(rs.getString("biography"));
        person.setProfilePath(rs.getString("profile_path"));
        person.setHomePage(rs.getString("home_page"));
        return person;
    };

    @Override
    public List<Person> getPersons() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, PERSON_ROW_MAPPER);
    }

    @Override
    public Person getPersonById(int id) {
        String sql = "SELECT * FROM person WHERE person_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, PERSON_ROW_MAPPER);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {
        String sql;
        Object[] params;

        if (useWildCard) {
            sql = "SELECT * FROM person WHERE person_name ILIKE ?";
            params = new Object[]{"%" + name + "%"};
        } else {
            sql = "SELECT * FROM person WHERE person_name = ?";
            params = new Object[]{name};
        }

        return jdbcTemplate.query(sql, params, PERSON_ROW_MAPPER);

    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {
        String sql;
        Object[] params;

        if (useWildCard) {
            sql = "SELECT DISTINCT p.* FROM person p " +
                    "JOIN movie_actor ma ON p.id = ma.actor_id " +
                    "JOIN movie m ON ma.movie_id = m.id " +
                    "JOIN collection c ON m.collection_id = c.id " +
                    "WHERE c.name ILIKE ?";
            params = new Object[]{"%" + collectionName + "%"};
        } else {
            sql = "SELECT DISTINCT p.* FROM person p " +
                    "JOIN movie_actor ma ON p.id = ma.actor_id " +
                    "JOIN movie m ON ma.movie_id = m.id " +
                    "JOIN collection c ON m.collection_id = c.id " +
                    "WHERE c.name = ?";
            params = new Object[]{collectionName};
        }

        return jdbcTemplate.query(sql, params, PERSON_ROW_MAPPER);
    }
}

