package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    private static final RowMapper<Movie> MOVIE_ROW_MAPPER = (rs, rowNum) -> {
        Movie movie = new Movie();
        movie.setId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
        movie.setDirectorId(rs.getInt("director_id"));
        movie.setCollectionId(rs.getInt("collection_id"));
        movie.setTagline(rs.getString("tagline"));
        movie.setPosterPath(rs.getString("poster_path"));
        movie.setHomePage(rs.getString("home_page"));
        movie.setLengthMinutes(rs.getInt("length_minutes"));
        return movie;
    };

    @Override
    public List<Movie> getMovies() {
        String sql = "SELECT * FROM movie";
        return jdbcTemplate.query(sql, MOVIE_ROW_MAPPER);
    }

    @Override
    public Movie getMovieById(int id) {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, MOVIE_ROW_MAPPER);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
            String sql;
            Object[] params;

            if (useWildCard) {
                sql = "SELECT * FROM movie WHERE title LIKE ?";
                params = new Object[]{"%" + title + "%"};
            } else {
                sql = "SELECT * FROM movie WHERE title = ?";
                params = new Object[]{title};
            }

            return jdbcTemplate.query(sql, params, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {
            String sql;
            Object[] params;

            if (useWildCard) {
                sql = "SELECT * FROM movie WHERE director_id ILIKE ? AND release_date BETWEEN ? AND ?";
                params = new Object[]{"%" + directorName + "%", startYear, endYear};
            } else {
                sql = "SELECT * FROM movie WHERE director_id = ? AND release_date BETWEEN ? AND ?";
                params = new Object[]{directorName, startYear, endYear};
            }

            return jdbcTemplate.query(sql, params, MOVIE_ROW_MAPPER);
        }
    }

