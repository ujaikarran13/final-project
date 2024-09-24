package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.exception.DaoException;
import com.techelevator.ssgeek.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcProductDao(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT product_id, name, description, price, image_name" + "FROM Product WHERE product_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
            if (results.next()) {
                product = mapRowToProduct(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * " + "FROM Product";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
    }
        return products;
}

    @Override
    public List<Product> getProductsWithNoSales() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * " + "FROM product" + "JOIN line_item ON product.product_id = line_item.product_id" + "WHERE product.product_id NOT IN (SELECT product_id FROM line_item";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return products;
    }

    @Override
    public Product createProduct(Product newProduct) {
        Integer newId;
        String sql = "INSERT INTO product (name, description, price, image_name) " + "VALUES(? ,? ,? ,?) RETURNING product_id";
        try {
           newId = jdbcTemplate.queryForObject(sql, int.class, newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getImageName());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getProductById(newId);
    }


    @Override
    public Product updateProduct(Product updatedProduct) {
        String sql = "UPDATE product " + "SET name = ? , description = ?, price = ?, image_name= ? " + "WHERE product_id = ?";
        try {
           int rowsAffected = jdbcTemplate.update(sql, updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(), updatedProduct.getImageName(), updatedProduct.getProductId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedProduct = getProductById((updatedProduct.getProductId()));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedProduct;
    }


    @Override
    public int deleteProductById(int productId) {
        int numberOfDelectedRows = 0;
        String sql = "SELECT * " + "FROM product WHERE product_id = ?";

        try {
            numberOfDelectedRows = jdbcTemplate.update(sql, productId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfDelectedRows;
    }
private Product mapRowToProduct(SqlRowSet results) {
    Product product = new Product();
    product.setProductId(results.getInt("product_id"));
    product.setName(results.getString("name"));
    product.setDescription(results.getString("description"));
    product.setPrice(BigDecimal.valueOf(results.getDouble("price")));
    product.setImageName(results.getString("image_name"));

    return product;
}
}
