package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.Meals;
@Repository
public interface MealsRepo extends JpaRepository<Meals, Integer> {
	@Query("SELECT m.mealId FROM Meals m WHERE m.mealType = :mealType")
	Integer findMealIdByMealType(String mealType);
	
	 @Query("SELECT m.mealType FROM Meals m WHERE m.mealId = :mealId")
	 String findMealTypeByMealId(@Param("mealId") int mealId);


}
