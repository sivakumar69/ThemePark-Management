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
	@Column(name = "operator_id")
	String operatorId;
	@Column(name = "name")
	String rideName;
	@Column(name = "length")
	int rideLength;
	@Column(name = "max")
	int rideMax;
	@Column(name = "min")
	int rideMin;

	public Ride() {
	}

	public int getRideId() {
		return rideId;
	}

	public void setRideId(int rideId) {
		this.rideId = rideId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRideName() {
		return rideName;
	}

	public void setRideName(String rideName) {
		this.rideName = rideName;
	}

	public int getRideLength() {
		return rideLength;
	}

	public void setRideLength(int rideLength) {
		this.rideLength = rideLength;
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

}
