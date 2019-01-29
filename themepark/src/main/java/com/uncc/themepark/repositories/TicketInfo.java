package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Ticket;

public interface TicketInfo extends CrudRepository<Ticket, Long> {
	
	@Query(value="select * from ticket where ticket_type not like '%_INDIVIDUAL%'", nativeQuery=true)
	public List<Ticket> getEntryTicketsInfo();
	
	@Query(value="select * from ticket where ticket_type like '%_INDIVIDUAL%'", nativeQuery=true)
	public List<Ticket> getIndividualTicketsInfo();
	
	@Query(value="select ticket_id from ticket where ticket_type= :ticketCategory", nativeQuery=true)
	public int getTicketIdByTicketCategory(@Param("ticketCategory") String ticketCategory);
	
	@Query(value="select price from ticket where ticket_type= :ticketCategory", nativeQuery=true)
	public int getTicketPriceByTicketCategory(@Param("ticketCategory") String ticketCategory);
	
	@Query(value="select t.ticket_type as TicketType, group_concat(r.name) as RidesList, t.price as Price from ride_ticket rt \r\n" + 
			"inner join ticket t on t.ticket_id = rt.ticket_id\r\n" + 
			"inner join ride r on r.ride_id = rt.ride_id\r\n" + 
			"group by t.ticket_type\r\n" + 
			"having t.ticket_type not like'%_INDIVIDUAL%'", nativeQuery=true)
	public List<RideTicketInfo> getRideTicketInfo();
	
	@Query(value="select t.ticket_type as TicketType, group_concat(r.name) as RidesList, t.price as Price from ride_ticket rt \r\n" + 
			"inner join ticket t on t.ticket_id = rt.ticket_id\r\n" + 
			"inner join ride r on r.ride_id = rt.ride_id\r\n" + 
			"group by t.ticket_type\r\n" + 
			"having t.ticket_type like'%_INDIVIDUAL%'", nativeQuery=true)
	public List<RideTicketInfo> getRideTicketInfoIndividual();
	
	

}
