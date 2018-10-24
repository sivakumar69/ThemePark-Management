package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ride")
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ride_id")
	int rideId;
	@Column(name = "employee_id")
	int employeeId;
	@Column(name = "ridename")
	int rideName;
	@Column(name = "ridelength")
	int rideLength;
	@Column(name = "rideage")
	int rideAge;
	@Column(name = "ridemax")
	int rideMax;
	@Column(name = "ridemin")
	int rideMin;
	@Column(name = "price")
	int price;

	public Ride() {
	}

	public int getRideId() {
		return rideId;
	}

	public void setRideId(int rideId) {
		this.rideId = rideId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRideName() {
		return rideName;
	}

	public void setRideName(int rideName) {
		this.rideName = rideName;
	}

	public int getRideLength() {
		return rideLength;
	}

	public void setRideLength(int rideLength) {
		this.rideLength = rideLength;
	}

	public int getRideAge() {
		return rideAge;
	}

	public void setRideAge(int rideAge) {
		this.rideAge = rideAge;
	}

	public int getRideMax() {
		return rideMax;
	}

	public void setRideMax(int rideMax) {
		this.rideMax = rideMax;
	}

	public int getRideMin() {
		return rideMin;
	}

	public void setRideMin(int rideMin) {
		this.rideMin = rideMin;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
