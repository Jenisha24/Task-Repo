package com.restaurant.vo;

import java.time.LocalDate;
import java.util.List;

public class SeatingAvailabilityVo {
	private LocalDate bookingDate;
	private String mealType;
	private String tableName;
	private int availableSeats;
	
	public SeatingAvailabilityVo() {
		super();
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String list) {
		this.tableName = list;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
}
