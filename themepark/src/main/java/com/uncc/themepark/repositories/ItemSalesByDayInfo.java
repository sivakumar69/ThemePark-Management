package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.ItemSalesByDay;

public interface ItemSalesByDayInfo extends CrudRepository<ItemSalesByDay, Long>{
	
	@Query(value="select * from item_sales_by_day", nativeQuery=true)
	public List<ItemSalesByDay> findAllItemSalesByDay();
	
	@Query(value="call filter_item_sales(:type, :startDate, :endDate)", nativeQuery=true)
	public List<ItemSalesByDay> filterItemSales(@Param("type") String type, @Param("startDate") String startDate, @Param("endDate") String endDate);

}
