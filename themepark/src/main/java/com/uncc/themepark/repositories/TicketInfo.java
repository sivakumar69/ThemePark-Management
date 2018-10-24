package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Ticket;

public interface TicketInfo extends CrudRepository<Ticket, Long> {
	
	@Query(value="select * from ticket", nativeQuery=true)
	public List<Ticket> getTicketInfo();
	
	@Query(value="select ticket_id from ticket where ticket_type= :ticketCategory", nativeQuery=true)
	public int getTicketIdByTicketCategory(@Param("ticketCategory") String ticketCategory);

}
