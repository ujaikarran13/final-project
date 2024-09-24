package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcLineItemDao implements LineItemDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcLineItemDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<LineItem> getLineItemsBySaleId(int saleId) {
        return null;
    }
}

