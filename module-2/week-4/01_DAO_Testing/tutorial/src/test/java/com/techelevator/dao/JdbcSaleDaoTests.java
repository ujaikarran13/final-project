package com.techelevator.dao;

import com.techelevator.model.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class JdbcSaleDaoTests extends BaseDaoTests {

    // Step One: Add constants for Madge

    // Step Two: Add constants for customer without sale and non-existent customer

    private JdbcSaleDao jdbcSaleDao;

    @Before
    public void setup() {

        // Arrange - new instance of JdbcSaleDao before each and every test
        jdbcSaleDao = new JdbcSaleDao(dataSource);
    }

    @Test
    public void getSaleById_returns_correct_sale() {

        // Step One: Replace Assert.fail("Test not implemented.")
        // Step One: Add constants for Madge
        final int MADGE_CUSTOMER_ID = 3;
        final int MADGE_FIRST_SALE_ID = 5;
        Sale madgeFirstSale = mapValuesToSale(MADGE_FIRST_SALE_ID, new BigDecimal("23.98"), true, MADGE_CUSTOMER_ID);

        // Act - retrieve Madge's first sale
        Sale sale = jdbcSaleDao.getSaleById(MADGE_FIRST_SALE_ID);

        // Assert - retrieved sale is not null and matches expected sale
        Assert.assertNotNull("getSaleById(" + MADGE_FIRST_SALE_ID + ") returned null", sale);
        assertSalesMatch("getSaleById(" + MADGE_FIRST_SALE_ID + ") returned wrong or partial data", madgeFirstSale, sale);
    }


    @Test
    public void getSalesByCustomerId_with_valid_id_returns_correct_sales() {

        // Step Two: Replace Assert.fail("Test not implemented.")
        // Step Two: Add constants for customer without sale and non-existent customer
        // Step Two: Add constants for customer without sale and non-existent customer
        private static final int CUSTOMER_WITHOUT_SALES_ID = 5;
        private static final int NON_EXISTENT_CUSTOMER_ID = 7;

        // Act - retrieve sales for Madge
        List<Sale> sales = jdbcSaleDao.getSalesByCustomerId(MADGE_CUSTOMER_ID);
        // Assert - Madge has two existing sales
        Assert.assertEquals("getSalesByCustomerId(" + MADGE_CUSTOMER_ID + ") returned wrong number of sales",
                2, sales.size());

        // Act - retrieve customer with no sales
        sales = jdbcSaleDao.getSalesByCustomerId(CUSTOMER_WITHOUT_SALES_ID);
        // Assert - list of sales is empty for customer with no sales
        Assert.assertEquals("getSalesByCustomerId(" + CUSTOMER_WITHOUT_SALES_ID +
                ") without sales returned wrong number of sales", 0, sales.size());

        // Act - retrieve customer that doesn't exist
        sales = jdbcSaleDao.getSalesByCustomerId(NON_EXISTENT_CUSTOMER_ID);
        // Assert - list of sales is empty for customer that doesn't exist
        Assert.assertEquals("Customer doesn't exist, getSalesByCustomerId(" + NON_EXISTENT_CUSTOMER_ID +
                ") returned the wrong number of sales", 0, sales.size());
    }

    @Test
    public void createSale_creates_sale() {

        // Step Three: Replace Assert.fail("Test not implemented.")
        Assert.fail("Test not implemented.");
    }

    @Test
    public void updateSale_updates_sale() {

        // Step Four: Replace Assert.fail("Test not implemented.")
        Assert.fail("Test not implemented.");
    }

    @Test
    public void deleteSaleById_deletes_sale() {

        // Step Five: Replace Assert.fail("Test not implemented.")
        Assert.fail("Test not implemented.");
    }

    // Convenience method in lieu of a Sale constructor with all the fields as parameters.
    // Similar to mapRowToSale() in JdbcSaleDao.
    private static Sale mapValuesToSale(int saleId, BigDecimal total, boolean delivery, Integer customerId) {

        Sale sale = new Sale();
        sale.setSaleId(saleId);
        sale.setTotal(total);
        sale.setDelivery(delivery);
        sale.setCustomerId(customerId);
        return sale;
    }

    private void assertSalesMatch(String message, Sale expected, Sale actual) {

        Assert.assertEquals(message, expected.getSaleId(), actual.getSaleId());
        Assert.assertEquals(message, expected.getTotal(), actual.getTotal());
        Assert.assertEquals(message, expected.isDelivery(), actual.isDelivery());
        Assert.assertEquals(message, expected.getCustomerId(), actual.getCustomerId());
    }
}
