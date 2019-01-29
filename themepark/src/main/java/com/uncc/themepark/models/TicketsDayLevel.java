package com.uncc.themepark.models;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class TicketsDayLevel implements Serializable {
	@Column(name="sale_day")
	String saleDay;
	@Column(name="ticket_id")
	String ticketId;

	public TicketsDayLevel() {

	}

	public TicketsDayLevel(String saleDay, String ticketId) {
		this.saleDay = saleDay;
		this.ticketId = ticketId;
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

}
