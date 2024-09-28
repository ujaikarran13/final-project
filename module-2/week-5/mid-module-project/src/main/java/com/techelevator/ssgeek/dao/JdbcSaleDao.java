package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.exception.DaoException;
import com.techelevator.ssgeek.model.Product;
import com.techelevator.ssgeek.model.Sale;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcSaleDao implements SaleDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcSaleDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSaleById(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, customer_id, sale_date, ship_date" + "FROM Sale WHERE sale_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
            if (results.next()) {
                sale = mapRowToSale(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return sale;
    }

    @Override
    public List<Sale> getUnshippedSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * " + "FROM sale" + "WHERE ship_date IS NULL";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Sale sale = mapRowToSale(results);
                sales.add(sale);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * " + "FROM sale" + "WHERE customer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
            while (results.next()) {
                Sale sale = mapRowToSale(results);
                sales.add(sale);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * " + "FROM sale" + "WHERE product_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
            while (results.next()) {
                Sale sale = mapRowToSale(results);
                sales.add(sale);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return sales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        Integer newId;
        String sql = "INSERT INTO sale (sale_id, customer_id, sale_date, ship_date) " + "VALUES(? ,? ,? ,?) RETURNING sale_id";
        try {
            newId = jdbcTemplate.queryForObject(sql, int.class, newSale.getSaleId(), newSale.getCustomerId(), newSale.getSaleDate(), newSale.getShipDate());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getSaleById(newId);
    }

    @Override
    public Sale updateSale(Sale updatedSale) {
        String sql = "UPDATE sale " + "SET customer_id = ? , sale_date = ?, ship_date = ? " + "WHERE sale_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, updatedSale.getCustomerId(), updatedSale.getCustomerId(), updatedSale.getSaleDate(), updatedSale.getShipDate());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedSale = getSaleById((updatedSale.getSaleId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedSale;
    }

    @Override
    public int deleteSaleById(int saleId) {
        int numberOfDelectedRows = 0;
        String sql = "SELECT * " + "FROM sale WHERE sale_id = ?";

        try {
            numberOfDelectedRows = jdbcTemplate.update(sql, saleId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfDelectedRows;
    }
    private Sale mapRowToSale (SqlRowSet results) {
        Sale sale = new Sale();
        sale.setSaleId(results.getInt("sale_id"));
        sale.setCustomerId(results.getInt("customer_id"));
        sale.setSaleDate(Objects.requireNonNull(results.getDate("sale_date")).toLocalDate());
        sale.setShipDate(Objects.requireNonNull(results.getDate("ship_date")).toLocalDate());


        return sale;
    }
}
