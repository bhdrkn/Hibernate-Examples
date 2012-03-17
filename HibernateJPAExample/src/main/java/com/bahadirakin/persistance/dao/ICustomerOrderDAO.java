package com.bahadirakin.persistance.dao;

import java.util.List;

import com.bahadirakin.persistance.model.Customer;
import com.bahadirakin.persistance.model.CustomerOrder;

public interface ICustomerOrderDAO extends IBaseDAO<CustomerOrder>{

	/**
	 * Gets All the CustomerOrder for one Customer
	 * 
	 * @param customer
	 * @return List of CustomerOrder
	 */
	List<CustomerOrder> getAllByCustomer(Customer customer);
}
