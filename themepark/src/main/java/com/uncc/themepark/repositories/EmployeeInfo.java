package com.uncc.themepark.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Employee;

public interface EmployeeInfo extends CrudRepository<Employee, Long>{
	
	@Query(value="select * from employee", nativeQuery=true)
	public List<Employee> getAllEmployees();
	
	@Query(value="select concat(first_name, ' ', last_name) from employee where email= :email", nativeQuery=true)
	public String getEmployeeNameByEmial(@Param("email") String email);
	
	@Query(value="select group_concat(email) from employee", nativeQuery=true)
	public String getEmployeeEmails();
	
	@Query(value="select * from employee where email= :email", nativeQuery=true)
	public Employee getEmployeeDetailsByEmail(@Param("email") String email);
	
	@Query(value="update employee set "
			+ "first_name= :firstName, last_name= :lastName, address_line_1=:addressLine1, address_line_2=:addressLine2, city= :city, state=:state, zipcode=:zipcode, phone_number= :phoneNumber "
			+ "where email= :email", nativeQuery=true)
	@Modifying
	@Transactional
	public void updateEmployeeDetailsByEmail(@Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("addressLine1") String addressLine1,
			@Param("addressLine2") String addressLine2, @Param("city") String city, @Param("state") String state, @Param("zipcode") String zipcode, @Param("phoneNumber") String phoneNumber);

}
