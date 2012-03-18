package com.bahadirakin.persistance.dao;

import java.io.File;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.bahadirakin.persistance.BaseDAOTestHibernateImpl;
import com.bahadirakin.persistance.HibernateUtil;
import com.bahadirakin.persistance.dao.impl.CustomerDAOImpl;
import com.bahadirakin.persistance.dao.impl.CustomerOrderDAOImpl;
import com.bahadirakin.persistance.model.Customer;
import com.bahadirakin.persistance.model.CustomerOrder;

public class CustomerDAOImplTest extends BaseDAOTestHibernateImpl{
	
	public File getDataSetFile() {
		return new File("src/test/resources/Dataset.xml");
	}

	@Override
	public SessionFactory getSessionFactory() {
		return HibernateUtil.getInstance().getFactory();
	}

	@Test
	public void assertDB() {
		ICustomerDAO customerDAO = new CustomerDAOImpl();
		Customer customer = customerDAO.getById(123, false);
        ICustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();
        CustomerOrder customerOrder = customerOrderDAO.getById(321, false);
 
        
        customerOrderDAO.delete(customerOrder);
        customerDAO.delete(customer);
        Assert.assertTrue(customerDAO.getAll(true).size() == 0);
        Assert.assertTrue(customerOrderDAO.getAll(true).size() == 0);
	}

}
