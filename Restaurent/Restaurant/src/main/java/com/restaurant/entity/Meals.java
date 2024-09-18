package com.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Meals {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int mealId;
	private String mealType;
	public Meals(int mealId, String mealType) {
		super();
		this.mealId = mealId;
		this.mealType = mealType;
	}
	
	
	public Meals() {
		super();
	}


	public int getMealId() {
		return mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
	
}
