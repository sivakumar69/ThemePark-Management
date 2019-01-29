package com.uncc.themepark.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.ItemSales;

public interface ItemSalesInfo extends CrudRepository<ItemSales, Long> {

	@Modifying
	@Transactional
	@Query(value = "insert into item_sales (cust_id, item_id, employee_id, amount) values (:custId, :itemId, :employeeId, :amount)", nativeQuery = true)
	public void insertItemSalesData(@Param("custId") String custId, @Param("itemId") String itemId,
			@Param("employeeId") String employeeId, @Param("amount") String amount);

}
