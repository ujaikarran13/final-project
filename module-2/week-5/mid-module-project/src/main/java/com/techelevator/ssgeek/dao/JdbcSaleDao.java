package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcSaleDao implements SaleDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcSaleDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSaleById(int saleId) {
        return null;
    }

    @Override
    public List<Sale> getUnshippedSales() {
        return null;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        return null;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        return null;
    }

    @Override
    public Sale createSale(Sale newSale) {
        return null;
    }

    @Override
    public Sale updateSale(Sale updatedSale) {
        return null;
    }

    @Override
    public int deleteSaleById(int saleId) {
        return 0;
    }
}
