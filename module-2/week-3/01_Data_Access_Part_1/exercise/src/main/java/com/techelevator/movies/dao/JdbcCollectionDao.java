package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private static final RowMapper<Collection> COLLECTION_ROW_MAPPER = (rs, rowNum) -> {
        Collection collection = new Collection();
        collection.setId(rs.getInt("collection_id"));
        collection.setName(rs.getString("collection_name"));
        return collection;
    };

    @Override
    public List<Collection> getCollections() {
        String sql = "SELECT * FROM collection";
        return jdbcTemplate.query(sql, COLLECTION_ROW_MAPPER);

    }

    @Override
    public Collection getCollectionById(int id) {
        String sql = "SELECT * FROM collection WHERE collection_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, COLLECTION_ROW_MAPPER);
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {
        String sql;
        Object[] params;

        if (useWildCard) {
            sql = "SELECT * FROM collection WHERE name ILIKE ?";
            params = new Object[]{"%" + name + "%"};
        } else {
            sql = "SELECT * FROM collection WHERE name = ?";
            params = new Object[]{name};
        }

        return jdbcTemplate.query(sql, params, COLLECTION_ROW_MAPPER);
        }
    }

