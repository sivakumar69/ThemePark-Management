package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="item_sales_by_stall_and_day")
@IdClass(ItemSalesDayStallLevel.class)
public class ItemSalesByStallAndDay {
	
	@Id
	@Column(name="sale_day")
	String saleDay;
	@Id
	@Column(name="item_type")
	String itemType;
	@Id
	@Column(name="stall_name")
	String stallName;
	@Column(name="total_amount")
	String totalAmount;
	
	public ItemSalesByStallAndDay() {
		
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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
