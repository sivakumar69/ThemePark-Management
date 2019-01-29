package com.uncc.themepark.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uncc.themepark.models.Items;

public interface ItemsInfo extends CrudRepository<Items, Long>{
	
	@Query(value="select * from items where type= :itemType", nativeQuery=true)
	public List<Items> getItemsInfoByType(@Param("itemType") String itemType);
	
	@Query(value="select id from items where type= :itemCategory and stall_name= :stallName", nativeQuery=true)
	public int getItemId(@Param("itemCategory") String itemCategory, @Param("stallName") String stallName);
}
