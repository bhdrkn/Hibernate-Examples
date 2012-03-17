package com.bahadirakin.persistance.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.bahadirakin.persistance.dao.ICustomerDAO;
import com.bahadirakin.persistance.model.Customer;

public class CustomerDAOImpl extends BaseHibernateDAO<Customer> implements
		ICustomerDAO {

	private static final long serialVersionUID = 1L;

	public List<Customer> getAllByName(String name) {
		return findAllByCriteria(true, Restrictions.eq("name", name));
	}

	public List<Customer> getAllByCity(String city) {
		return findAllByCriteria(true, Restrictions.eq("city", city));
	}

}
