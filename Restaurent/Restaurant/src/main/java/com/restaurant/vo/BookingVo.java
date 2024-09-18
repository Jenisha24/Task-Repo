package com.restaurant.vo;

import java.time.LocalDate;

public class BookingVo {
	private int bookingId;
	private LocalDate bookingDate;
	private String mealType;
	private String tableName;
	private int bookedSeat;
	private String bookingStatus;
	private int userId;
	
	
	
	
	public BookingVo() {
		super();
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getBookedSeat() {
		return bookedSeat;
	}
	public void setBookedSeat(int bookedSeat) {
		this.bookedSeat = bookedSeat;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
