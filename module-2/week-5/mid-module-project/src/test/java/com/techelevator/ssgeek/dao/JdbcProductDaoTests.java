package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDaoTests extends BaseDaoTests {
    private JdbcProductDao jdbcProductDao;

    @Before
    public void setup() {

        jdbcProductDao = new JdbcProductDao((BasicDataSource) dataSource);
    }

    @Test
    public void getProductById() {
        Product product = mapValuesToProduct(1, "Coffee Mug", "Staying up late to take in the wonders of the solar system can make a geek a little sluggish in the morning. This awesome mug is just what you need to perk up in the morning with your caffeinatened beverage of choice!", BigDecimal.valueOf(9.99), "ssg_mug.png");
        Product product1 = jdbcProductDao.getProductById(product.getProductId());
        assertProductsMatch(product, product1);

    }

    public void getProducts(){
        List<Product> products = new ArrayList<>();
        Product product1 = mapValuesToProduct(1, "Coffee Mug", "Staying up late to take in the wonders of the solar system can make a geek a little sluggish in the morning. This awesome mug is just what you need to perk up in the morning with your caffeinatened beverage of choice!", BigDecimal.valueOf(9.99), "ssg_mug.png");
        Product product2 = mapValuesToProduct(2, "SOLAR SYSTEM: A Visual Exploration of the Planets, Moons, and Other Heavenly Bodies that Orbit Our Sun", "This beautiful book presents a new and fascinating way to experience our planetary neighborhood. With hundreds of stunning photographs and graphics, as well as fascinating text by the award-winning writer and broadcaster, Marcus Chown, Solar System takes us on a whirlwind tour of the planets, dwarf planets, moons and asteroids that orbit our sun.", BigDecimal.valueOf(29.95), "solarsystem_book.png");

        products.add(product1);
        products.add(product2);


        List<Product> actualProducts = jdbcProductDao.getProducts();
        Assert.assertEquals(products.size(), actualProducts.size());
        Assert.assertNotEquals(0, actualProducts.size());
        assertProductsMatch(products.get(1), actualProducts.get(1));
        assertProductsMatch(products.get(2), actualProducts.get(2));


    }
    private static Product mapValuesToProduct(int product_id, String name, String description, BigDecimal price, String image_name) {

        Product product = new Product();
        product.setProductId(product_id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(String.valueOf(price));
        product.setImageName(image_name);

        return product;
    }

    private void assertProductsMatch (Product expected, Product actual){

        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getImageName(), actual.getImageName());

    }
}
