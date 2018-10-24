package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Customer;
import com.uncc.themepark.models.Employee;

public interface EmployeeInfo extends CrudRepository<Employee, Long>{
	
	@Query(value="select * from employee", nativeQuery=true)
	public List<Employee> getAllEmployees();
	
	@Query(value="select concat(first_name, ' ', last_name) from employee where email= :email", nativeQuery=true)
	public String getEmployeeNameByEmial(@Param("email") String email);

}
