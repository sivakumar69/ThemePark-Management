package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Column(name = "ticket_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ticketId;
	@Column(name = "ticket_type")
	String ticketType;
	@Column(name = "price")
	Double price;

	public Ticket() {
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketType=" + ticketType + ", price=" + price + "]";
	}

}
