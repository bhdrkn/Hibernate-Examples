package com.bahadirakin.persistance.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.bahadirakin.persistance.dao.ICustomerOrderDAO;
import com.bahadirakin.persistance.model.Customer;
import com.bahadirakin.persistance.model.CustomerOrder;

public class CustomerOrderDAOImpl extends BaseHibernateDAO<CustomerOrder>
		implements ICustomerOrderDAO {

	private static final long serialVersionUID = 1L;

	public List<CustomerOrder> getAllByCustomer(Customer customer) {
		return findAllByCriteria(true, Restrictions.eq("customer", customer));
	}

}
