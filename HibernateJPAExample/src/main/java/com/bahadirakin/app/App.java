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
package com.bahadirakin.app;

import java.util.Date;

import com.bahadirakin.persistance.dao.ICustomerDAO;
import com.bahadirakin.persistance.dao.ICustomerOrderDAO;
import com.bahadirakin.persistance.dao.impl.CustomerDAOImpl;
import com.bahadirakin.persistance.dao.impl.CustomerOrderDAOImpl;
import com.bahadirakin.persistance.model.Customer;
import com.bahadirakin.persistance.model.CustomerOrder;

public class App 
{
    public static void main( String[] args )
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
        
        System.out.println("================");
        System.out.println("==== INSERT ====");
        System.err.println(customerOrderDAO.getAll(true).size());
        System.out.println(customerOrderDAO.getAllByCustomer(customer).size());
        System.out.println("================");
        
        System.out.println("================");
        System.out.println("DELETE - CASCADE");
        customerDAO.delete(customer);
        System.err.println(customerOrderDAO.getAll(true).size());
        System.out.println(customerOrderDAO.getAllByCustomer(customer).size());
        System.out.println("================");
    }
}
