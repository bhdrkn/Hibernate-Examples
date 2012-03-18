/*
*   Copyright 2012 Bahadır AKIN
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
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
