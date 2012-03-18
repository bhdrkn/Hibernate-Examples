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
package com.bahadirakin.persistance.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Represents Customer Order information
 * 
 * @author Bahadır AKIN
 * 
 */
@Entity
@Table(name = "customerorder" )
public class CustomerOrder extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * Compares CustomerOrder by Customer Name
	 */
	public static final Comparator<CustomerOrder> CUSTOMERORDER_CUSTOMER_COMPARATOR = new CustomerOrderCustomerComparator();

	/**
	 * Compares CustomerOrder by Status
	 */
	public static final Comparator<CustomerOrder> CUSTOMERORDER_STATUS_COMPARATOR = new CustomerOrderStatusComparator();

	/**
	 * PK
	 */
	private Integer id;
	/**
	 * Customer
	 * 
	 * <p>
	 * There is a N-1 Relation
	 * </p>
	 */
	private Customer customer;
	/**
	 * When order set
	 */
	private Date datePlaced;
	/**
	 * When order to be done
	 */
	private Date datePromised;
	/**
	 * Order status
	 */
	private String status;

	/**
	 * Default constructor (required by Hibernate).
	 */
	public CustomerOrder() {
		super();
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerid", referencedColumnName = "id", updatable = true, insertable = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "dateplaced")
	public Date getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(Date datePlaced) {
		this.datePlaced = datePlaced;
	}

	@Column(name = "datepromised")
	public Date getDatePromised() {
		return datePromised;
	}

	public void setDatePromised(Date datePromised) {
		this.datePromised = datePromised;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private static final class CustomerOrderCustomerComparator implements
			Comparator<CustomerOrder>, Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(CustomerOrder o1, CustomerOrder o2) {
			return new CompareToBuilder().append(o1.getCustomer(),
					o2.getCustomer(), Customer.CUSTOMER_NAME_COMPARATOR)
					.toComparison();
			// Eger CUSTOMER_NAME_COMPARATOR kullanilmasaydi, dogrudan
			// AbstractEntity'de tanimli compareTo(...) metodunu kullanirdi
		}
	}

	private static final class CustomerOrderStatusComparator implements
			Comparator<CustomerOrder>, Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(CustomerOrder o1, CustomerOrder o2) {
			return new CompareToBuilder()
					.append(o1.getStatus(), o2.getStatus()).toComparison();
		}

	}

}
