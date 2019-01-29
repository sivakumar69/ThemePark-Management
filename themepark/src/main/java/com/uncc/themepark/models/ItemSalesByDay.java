package com.uncc.themepark.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "item_sales_by_day")
@IdClass(ItemSalesDayLevel.class)
public class ItemSalesByDay {
	@Id
	@Column(name = "sale_day")
	String saleDay;
	@Id
	@Column(name = "item_type")
	String itemType;
	@Column(name = "total_amount")
	String totalAmount;

	public ItemSalesByDay() {

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

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
