package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcLineItemDaoTests extends BaseDaoTests {
    private JdbcLineItemDao jdbcLineItemDao;
    private BasicDataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Before
    public void setup() {

        jdbcLineItemDao = new JdbcLineItemDao((BasicDataSource) dataSource);

    }

    @Test
    public void testGetLineItemsBySaleId() {
        LineItem lineItem = mapValuesToLineItem(1, 1, 1, 10);
        LineItem lineItem1 = (LineItem) jdbcLineItemDao.getLineItemsBySaleId(lineItem.getSaleId());
        assertLineItemsMatch(lineItem, lineItem1);

    }

    private static LineItem mapValuesToLineItem (int line_item_id, int sale_id, int product_id, int quantity){
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(line_item_id);
        lineItem.setSaleId(sale_id);
        lineItem.setProductId(product_id);
        lineItem.setQuantity(quantity);

        return lineItem;

    }
    private void assertLineItemsMatch(LineItem expected, LineItem actual){
        Assert.assertEquals(expected.getLineItemId(), actual.getLineItemId());
        Assert.assertEquals(expected.getSaleId(), actual.getSaleId());
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());

    }
}