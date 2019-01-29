package com.uncc.themepark.models;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class ItemSalesDayStallLevel implements Serializable {

	@Column(name = "sale_day")
	String saleDay;
	@Column(name = "item_type")
	String itemType;
	@Column(name = "stall_name")
	String stallName;

	public ItemSalesDayStallLevel() {

	}

	public ItemSalesDayStallLevel(String saleDay, String itemType, String stallName) {
		this.saleDay = saleDay;
		this.itemType = itemType;
		this.stallName = stallName;
	}

	public String getSaleDay() {
		return saleDay;
	}

	public void setSaleDay(String saleDay) {
		this.saleDay = saleDay;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

}
