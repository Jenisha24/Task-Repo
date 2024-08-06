package com.todo.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class EventEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;
	private String eventName;
	private String eventDescription;
	private LocalDate eventDate;
	private LocalTime eventTime;
	@JsonIgnore
	private String eventStatus;

	public EventEntity() {
	}

	public EventEntity(int eventId, String eventName, String eventDescription, LocalDate eventDate, LocalTime eventTime,
			String eventStatus) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventStatus = eventStatus;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

 

}
