package com.restaurant.vo;

import java.time.LocalDate;

public class AvailabilityVo {
	private LocalDate bookingDate;
	private String mealType;
	
	
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
	
}
