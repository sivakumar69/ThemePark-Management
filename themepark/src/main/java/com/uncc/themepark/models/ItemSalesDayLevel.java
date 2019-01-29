package com.uncc.themepark.models;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class ItemSalesDayLevel implements Serializable {

	@Column(name = "sale_day")
	String saleDay;
	@Column(name = "item_type")
	String itemType;

	public ItemSalesDayLevel() {

	}

	public ItemSalesDayLevel(String saleDay, String itemType) {
		this.saleDay = saleDay;
		this.itemType = itemType;
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

}
