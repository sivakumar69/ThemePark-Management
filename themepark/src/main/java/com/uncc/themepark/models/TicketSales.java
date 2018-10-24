package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_sales")
public class TicketSales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_id")
	int salesId;
	@Column(name = "cust_id")
	int custId;
	@Column(name = "employee_id")
	String employeeId;
	@Column(name = "ticket_id")
	int ticketId;
	@Column(name = "price")
	int price;
	@Column(name = "quantity")
	int quantity;
	@Column(name = "sale_date")
	String sale_date;

	public TicketSales() {
	}

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSale_date() {
		return sale_date;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
