package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_sales_by_day")
public class TicketSalesByDay {

	@Id
	@Column(name = "sale_day")
	String saleDay;
	@Column(name = "total_amount")
	String totalAmount;

	public TicketSalesByDay() {

	}

	public String getSaleDay() {
		return saleDay;
	}

	public void setSaleDay(String saleDay) {
		this.saleDay = saleDay;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
