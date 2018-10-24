package com.uncc.themepark.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.TicketSales;

public interface TicketSalesInfo extends CrudRepository<TicketSales, Long> {
	@Modifying
	@Transactional
	@Query(value = "insert into ticket_sales (cust_id, employee_id, price, quantity, ticket_id) values (:custId, :employeeId, :price, :quantity, :ticketId)", nativeQuery = true)
	public void insertTicketSalesData(@Param("employeeId") String employeeId, @Param("custId") String custId, @Param("price") String price, 
			@Param("quantity") String quantity, @Param("ticketId") String ticketId);

}
