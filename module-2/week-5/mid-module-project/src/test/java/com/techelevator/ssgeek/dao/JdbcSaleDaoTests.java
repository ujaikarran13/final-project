package com.techelevator.ssgeek.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;

public class JdbcSaleDaoTests extends BaseDaoTests {
    private JdbcSaleDao jdbcSaleDao;

    @Before
    public void setup() {

        jdbcSaleDao = new JdbcSaleDao((BasicDataSource) dataSource);


    }
}