package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Items {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String type;
	@Column(name="stall_id")
	int stallId;
	@Column(name="stall_name")
	String stallName;
	
	public Items() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStallId() {
		return stallId;
	}

	public void setStallId(int stallId) {
		this.stallId = stallId;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", type=" + type + ", stallId=" + stallId + ", stallName=" + stallName + "]";
	}
	
	
	
	

}
