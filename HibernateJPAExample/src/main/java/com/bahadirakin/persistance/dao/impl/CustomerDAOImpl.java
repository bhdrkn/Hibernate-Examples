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
package com.bahadirakin.persistance.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.bahadirakin.persistance.dao.ICustomerDAO;
import com.bahadirakin.persistance.model.Customer;

public class CustomerDAOImpl extends BaseHibernateDAO<Customer> implements
		ICustomerDAO {

	private static final long serialVersionUID = 1L;

	public List<Customer> getAllByName(String name) {
		return findAllByCriteria(Restrictions.eq("name", name));
	}

	public List<Customer> getAllByCity(String city) {
		return findAllByCriteria(Restrictions.eq("city", city));
	}

}
