package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//	public Optional<Restaurant> findByFilenameAndId(String file, Long id);
	

	 @Query("SELECT p FROM Restaurant p WHERE " +
	            "p.name LIKE CONCAT('%',:resto, '%')" +
	            "Or p.address LIKE CONCAT('%', :resto, '%')"+"Or p.phonenumber LIKE CONCAT('%', :resto, '%')")
	 public List<Restaurant> searchRestaurant(String resto);


//	public Optional<Restaurant> findByRestaurantname(String name);
}
