package com.uncc.themepark.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Ride;

public interface RideInfo extends CrudRepository<Ride, Long> {
	
	@Query(value="select * from ride", nativeQuery=true)
	public List<Ride> getAllRides();
	
	@Query(value="select group_concat(name) from ride where ride_id in (select ride_id from ride_ticket where ticket_id = :ticketId)", nativeQuery=true)
	public String getRidesStringByID(@Param("ticketId") int ticketId);

}
