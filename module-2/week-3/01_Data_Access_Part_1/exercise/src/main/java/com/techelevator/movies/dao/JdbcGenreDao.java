package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenreDao implements GenreDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private static final RowMapper<Genre> GENRE_ROW_MAPPER = (rs, rowNum) -> {
        Genre genre = new Genre();
        genre.setId(rs.getInt("genre_id"));
        genre.setName(rs.getString("genre_name"));
        return genre;
    };

    @Override
    public List<Genre> getGenres() {
        String sql = "SELECT * FROM genre";
        return jdbcTemplate.query(sql, GENRE_ROW_MAPPER);
    }

    @Override
    public Genre getGenreById(int id) {
        String sql = "SELECT * FROM genre WHERE genre_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, GENRE_ROW_MAPPER);
        } catch (Exception e) {
            // Handle the case where the genre is not found by returning null

            return null;
        }
    }

    @Override
    public List<Genre> getGenresByName(String name, boolean useWildCard) {
        String sql;
        Object[] params;

        if (useWildCard) {
            sql = "SELECT * FROM genre WHERE genre_name ILIKE ?";
            params = new Object[]{"%" + name + "%"};
        } else {
            sql = "SELECT * FROM genre WHERE genre_name = ?";
            params = new Object[]{name};
        }
        return jdbcTemplate.query(sql, params, GENRE_ROW_MAPPER);

    }
}
