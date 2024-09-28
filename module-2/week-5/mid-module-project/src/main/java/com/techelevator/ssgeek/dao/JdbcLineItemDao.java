package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.exception.DaoException;
import com.techelevator.ssgeek.model.LineItem;
import com.techelevator.ssgeek.model.Sale;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcLineItemDao implements LineItemDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcLineItemDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<LineItem> getLineItemsBySaleId(int saleId) {
        List<LineItem> lineItems = new ArrayList<>();
        String sql = "SELECT * " + "FROM lineItem" + "WHERE sale_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
            while (results.next()) {
                LineItem lineItem = mapRowToLineItem(results);
                lineItems.add(lineItem);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return lineItems;
    }
    private LineItem mapRowToLineItem (SqlRowSet results) {
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(results.getInt("line_item_id"));
        lineItem.setSaleId(results.getInt("sale_id"));
        lineItem.setProductId(results.getInt("product_id"));
        lineItem.setQuantity(results.getInt("quantity"));


        return lineItem;
    }
}

