package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcCustomerDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Customer getCustomerById(int customerId) {
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        return null;
    }
}
