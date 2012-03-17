package com.bahadirakin.persistance.dao;

import java.util.List;

import com.bahadirakin.persistance.model.Customer;

public interface ICustomerDAO extends IBaseDAO<Customer>{
	
	/**
	 * Gets All Customers with same name.
	 * 
	 * <p>
	 * 	Customer name has no Unique Constraints
	 * </p>
	 * 
	 * @return List of Customers
	 */
	List<Customer> getAllByName(String name);
	
	/**
	 * Gets All Customers with in same city
	 * 
	 * @return List of Customers
	 */
	List<Customer> getAllByCity(String city);

}
