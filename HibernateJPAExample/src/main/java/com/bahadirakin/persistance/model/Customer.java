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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Represents Customer Information
 * 
 * @author Bahadır AKIN
 * 
 */
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * Compares Customer Name information
	 */
	public static final Comparator<Customer> CUSTOMER_NAME_COMPARATOR = new CustomerNameComparator();

	/**
	 * Compares Customer Address and City information
	 */
	public static final Comparator<Customer> CUSTOMER_ADDRESS_COMPARATOR = new CustomerAddressComparator();

	/**
	 * PK
	 */
	private Integer id;
	/**
	 * Customer Full Name
	 */
	private String name;
	/**
	 * Customer Address
	 */
	private String address;
	/**
	 * Customer City
	 */
	private String city;
	/**
	 * Customer cell phone
	 */
	private String phone;

	/**
	 * Default constructor (required by Hibernate).
	 */
	public Customer() {
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private static final class CustomerNameComparator implements
			Comparator<Customer>, Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(Customer arg0, Customer arg1) {
			return new CompareToBuilder()
					.append(arg0.getName(), arg1.getName()).toComparison();
		}

	}

	private static final class CustomerAddressComparator implements
			Comparator<Customer>, Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(Customer o1, Customer o2) {
			return new CompareToBuilder()
					.append(o1.getAddress(), o2.getAddress())
					.append(o1.getCity(), o2.getCity()).toComparison();
		}

	}

}
