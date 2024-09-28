package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JdbcCustomerDaoTests extends BaseDaoTests {

    private static final Customer customer_1 = new Customer(1,"Sherlock Holmes", "221 B Baker street", "Apartment 2", "London", "OH", "43140");

    private JdbcCustomerDao jdbcCustomerDao;

    @Before
    public void setup() {

        jdbcCustomerDao = new JdbcCustomerDao((BasicDataSource) dataSource);

    }

    @Test
    public void getCustomerById_with_valid_id_returns_correct_customer(){
        Customer expectedCustomer1 = mapValuesToCustomer(1,"Sherlock Holmes", "221 B Baker street", "Apartment 2", "London", "OH", "43140");
        Customer actualCustomer1 = jdbcCustomerDao.getCustomerById(expectedCustomer1.getCustomerId());
        Customer expectedCustomer2 = mapValuesToCustomer(2,"Mona Lisa", "99 rue de Rivoli", "NULL", "Paris", "OH", "45347");
        Customer actualCustomer2 = jdbcCustomerDao.getCustomerById(expectedCustomer2.getCustomerId());
        Customer expectedCustomer3 = mapValuesToCustomer(3,"Lady Liberty", "Liberty Island", "NULL", "New York", "NY", "10004");
        Customer actualCustomer3 = jdbcCustomerDao.getCustomerById(expectedCustomer3.getCustomerId());
        Customer expectedCustomer4 = mapValuesToCustomer(4,"The President", "1600 Pennsylvania Avenue NW", "NULL", "Washington", "DC", "20500");
        Customer actualCustomer4 = jdbcCustomerDao.getCustomerById(expectedCustomer4.getCustomerId());
        Customer expectedCustomer5 = mapValuesToCustomer(5,"Anne Frank", "263 Prinsengracht", "NULL", "Amsterdam", "OH", "43903");
        Customer actualCustomer5 = jdbcCustomerDao.getCustomerById(expectedCustomer5.getCustomerId());
        Customer expectedCustomer6 = mapValuesToCustomer(6,"Elwood Blues", "1060 West Addison Street", "NULL", "Chicago", "IL", "60613");
        Customer actualCustomer6 = jdbcCustomerDao.getCustomerById(expectedCustomer6.getCustomerId());
        assertCustomersMatch(expectedCustomer1, actualCustomer1);
        assertCustomersMatch(expectedCustomer2, actualCustomer2);
        assertCustomersMatch(expectedCustomer3, actualCustomer3);
        assertCustomersMatch(expectedCustomer4, actualCustomer4);
        assertCustomersMatch(expectedCustomer5, actualCustomer5);
        assertCustomersMatch(expectedCustomer6, actualCustomer6);

    }


    @Test
    public void getCustomers(){

        List<Customer> customers = new ArrayList<>();
        Customer customer1 = mapValuesToCustomer(1,"Sherlock Holmes", "221 B Baker street", "Apartment 2", "London", "OH", "43140");
        Customer customer2 = mapValuesToCustomer(2,"Mona Lisa", "99 rue de Rivoli", "NULL", "Paris", "OH", "45347");
        Customer customer3 = mapValuesToCustomer(3,"Lady Liberty", "Liberty Island", "NULL", "New York", "NY", "10004");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        List<Customer> actualCustomers = jdbcCustomerDao.getCustomers();
        Assert.assertEquals(customers.size(), actualCustomers.size());
        Assert.assertNotEquals(0, actualCustomers.size());
        assertCustomersMatch(customers.get(0), actualCustomers.get(0));
        assertCustomersMatch(customers.get(1), actualCustomers.get(1));
        assertCustomersMatch(customers.get(2), actualCustomers.get(2));
    }

    @Test
    public void createCustomer(){
        Customer newCustomer = new Customer();
        newCustomer.setName("Name");
        newCustomer.setStreetAddress1("Street1");
        newCustomer.setStreetAddress2("Street2");
        newCustomer.setCity("City");
        newCustomer.setState("NY");
        newCustomer.setZipCode("11111");

        int newCustomerId = jdbcCustomerDao.createCustomer(newCustomer).getCustomerId();
        Customer actualCUstomer = jdbcCustomerDao.getCustomerById(newCustomerId);

        assertNotNull(actualCUstomer);
        assertCustomersMatch(newCustomer, actualCUstomer);
    }
    @Test
    public void updateCustomer(){
        Customer exhistingCustomer = jdbcCustomerDao.getCustomerById(customer_1.getCustomerId());
        exhistingCustomer.setName("Updated name");

        Customer updateCustomer = jdbcCustomerDao.updateCustomer(exhistingCustomer);

        assertNotNull("Updated customer should not be null", updateCustomer);
        Assert.assertEquals("Name should be updated", "Updated name", updateCustomer.getName());

    }
    private static Customer mapValuesToCustomer(int customer_id, String name, String street_address1, String street_address2,
                                        String city, String state, String zip_code) {

        Customer customer = new Customer();
        customer.setCustomerId(customer_id);
        customer.setName(name);
        customer.setStreetAddress1(street_address1);
        customer.setStreetAddress2(street_address2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipCode(zip_code);
        return customer;
    }
    private void assertCustomersMatch(Customer expected, Customer actual) {
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getStreetAddress1(), actual.getStreetAddress1());
        Assert.assertEquals(expected.getStreetAddress2(), actual.getStreetAddress2());
        Assert.assertEquals(expected.getCity(), actual.getCity(), 0.001);
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());

    }
}
