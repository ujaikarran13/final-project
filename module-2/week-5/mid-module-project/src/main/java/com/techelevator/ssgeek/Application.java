package com.techelevator.ssgeek;

import com.techelevator.ssgeek.dao.*;
import com.techelevator.ssgeek.model.Customer;
import com.techelevator.ssgeek.model.LineItem;
import com.techelevator.ssgeek.model.Product;
import com.techelevator.ssgeek.model.Sale;
import com.techelevator.util.SystemInOutConsole;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

/**
 * Application is the class that launches the Solar System Geek Administrator by creating
 * the objects needed to interact with the user and file system and passing them to
 * the application's controller object.
 */

public class Application {

    public static void main(String[] args) {
        // Create the datasource used by all the DAOs
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/SSGeek");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        // Controller expects the DAOs it needs to be "injected" in the constructor.
        // Create the DAOs needed by the controller.
        //*****************************************************************************************
        // TODO: When you implement a new DAO, create an instance of it here, replacing the NULL
        CustomerDao customerDao = (CustomerDao) new JdbcCustomerDao(dataSource);

        ProductDao productDao = (ProductDao) new JdbcProductDao(dataSource);

        SaleDao saleDao = (SaleDao) new JdbcSaleDao(dataSource);

        LineItemDao lineItemDao = (LineItemDao) new JdbcLineItemDao(dataSource);

        //*****************************************************************************************

        // Create the basic i/o mechanism (the console)
        SystemInOutConsole systemInOutConsole = new SystemInOutConsole();

        // The controller manages the program flow. Create a control and call its run() method to start the menu loop.
        SSGeekAdminController controller =
                new SSGeekAdminController(systemInOutConsole, customerDao, productDao, saleDao, lineItemDao);
        controller.run();
    }
}
