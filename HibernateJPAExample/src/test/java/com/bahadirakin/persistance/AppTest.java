package com.bahadirakin.persistance;

import java.util.Date;

import com.bahadirakin.persistance.dao.ICustomerDAO;
import com.bahadirakin.persistance.dao.ICustomerOrderDAO;
import com.bahadirakin.persistance.dao.impl.CustomerDAOImpl;
import com.bahadirakin.persistance.dao.impl.CustomerOrderDAOImpl;
import com.bahadirakin.persistance.model.Customer;
import com.bahadirakin.persistance.model.CustomerOrder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Customer customer = new Customer();
        customer.setName("Bahadır AKIN");
        customer.setAddress("Ortaköy");
        customer.setCity("İstanbul");
        customer.setPhone("555-5555555");
        
        ICustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.save(customer);
        
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setDatePlaced(new Date(System.currentTimeMillis()));
        customerOrder.setDatePromised(new Date(System.currentTimeMillis()));
        
        ICustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();
        customerOrderDAO.save(customerOrder);
        
        int size1 = 0;
        int size2 = -1;
        System.out.println("================");
        System.out.println("==== INSERT ====");
        System.err.println(size1 = customerOrderDAO.getAll(true).size());
        System.out.println(size2 = customerOrderDAO.getAllByCustomer(customer).size());
        System.out.println("================");
        assertTrue(size1 == size2);
        
        customerDAO.refresh(customer);
        customerOrderDAO.refresh(customerOrder);
        
        System.out.println("================");
        System.out.println("DELETE - CASCADE");
        customerOrderDAO.delete(customerOrder);
        customerDAO.delete(customer);
        System.err.println(size1 = customerOrderDAO.getAll(true).size());
        System.out.println(size2 = customerOrderDAO.getAllByCustomer(customer).size());
        System.out.println("================");
        assertTrue(size1 == size2);
    }
}
