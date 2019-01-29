package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_sales_by_ticket_and_day")
@IdClass(TicketsDayLevel.class)
public class TicketSalesByTicetAndDay {
	@Id
	@Column(name="sale_day")
	String saleDay;
	@Id
	@Column(name="ticket_id")
	String ticketId;
	@Column(name="ticket_type")
	String ticketType;
	@Column(name="quantity")
	String quantity;
	@Column(name="total_amount")
	String totalAmount;

	public TicketSalesByTicetAndDay() {

	}

	public String getSaleDay() {
		return saleDay;
	}

	public void setSaleDay(String saleDay) {
		this.saleDay = saleDay;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
