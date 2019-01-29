package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.TicketSalesByTicetAndDay;

public interface TicketSalesByTicketAndDayInfo extends CrudRepository<TicketSalesByTicetAndDay, Long>{
	
	@Query(value="select * from ticket_sales_by_ticket_and_day", nativeQuery=true)
	public List<TicketSalesByTicetAndDay> findAllInfo();
	
	@Query(value="call filter_ticket_sales(:type, :startDate, :endDate)", nativeQuery=true)
	public List<TicketSalesByTicetAndDay> filterTicketSales(@Param("type") String type, @Param("startDate") String startDate, @Param("endDate") String endDate);
	

}
