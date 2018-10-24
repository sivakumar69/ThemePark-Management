package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Customer;

public interface customerInfo extends CrudRepository<Customer, Long>{
	
	@Query(value="select cust_id from customer where first_name= :firstName and last_name= :lastName and phone_number= :phoneNumber", nativeQuery=true)
	public Integer getCustomerId(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNumber") String phoneNumber);
	
	

}
