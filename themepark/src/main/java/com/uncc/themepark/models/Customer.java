package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="cust_id")
	Integer custId;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String last_name;
	@Column(name = "phone_number")
	String phoneNumber;
	
	

	public Customer(Integer custId, String firstName, String last_name, String phoneNumber) {
		this.custId = custId;
		this.firstName = firstName;
		this.last_name = last_name;
		this.phoneNumber = phoneNumber;
	}

	public Customer() {
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", firstName=" + firstName + ", last_name=" + last_name + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
