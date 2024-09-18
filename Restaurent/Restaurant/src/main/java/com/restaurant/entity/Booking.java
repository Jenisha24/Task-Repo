package com.restaurant.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	@Column(name = "booking_date")
	private LocalDate bookingDate;
	@Column(name = "meal_id")
	private int mealId;
	@Column(name = "table_id")
	private int tableId;
	@Column(name = "booked_seat")
	private int bookedSeat;
	@Column(name = "booking_status")
	private String bookingStatus;
	@Column(name = "user_id")
	private int userId;
	
	public Booking() {
		super();
	}
	public Booking(int bookingId, LocalDate bookingDate, int mealId, int tableId, int bookedSeat,
			String bookingStatus,int userId) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.mealId = mealId;
		this.tableId = tableId;
		this.bookedSeat = bookedSeat;
		this.bookingStatus = bookingStatus;
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getMealId() {
		return mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
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
	
}
