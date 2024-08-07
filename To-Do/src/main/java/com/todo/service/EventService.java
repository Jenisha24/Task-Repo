package com.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.EventEntity;
import com.todo.repository.EventRepo;

@Service
public class EventService {
	@Autowired
	EventRepo eventRepo;

	public String saveEvent(EventEntity eventEntity) {
		eventEntity.setEventStatus("pending");
		eventRepo.save(eventEntity);
		return "event details has been saved successfully";
	}

	public EventEntity updateEvent(EventEntity eventEntity) {
		Optional<EventEntity> existingDataOptional = eventRepo.findById(eventEntity.getEventId());
		EventEntity eventDetail = existingDataOptional.get();

		eventDetail.setEventName(eventEntity.getEventName());
		eventDetail.setEventDescription(eventEntity.getEventDescription());
		eventDetail.setEventDate(eventEntity.getEventDate());
		eventDetail.setEventTime(eventEntity.getEventTime());
		EventEntity updateDetails = eventRepo.save(eventDetail);
		return updateDetails;

	}

	public String deleteEvent(int eventId) {
		eventRepo.deleteById(eventId);
		return "event has been deleted successfully";

	}

	public String updateStatus(int eventId) {
		EventEntity eventData = eventRepo.findById(eventId).get();
		eventData.setEventStatus("completed");
		eventRepo.save(eventData);
		return "event has been completed";
	}

	public ArrayList<EventEntity> getEvent() {
		ArrayList<EventEntity> allEvent = new ArrayList<EventEntity>();
		allEvent.addAll(eventRepo.findAll());
		return allEvent;

	}

	public ArrayList<EventEntity> getStatus(String eventStatus) {
		ArrayList<EventEntity> eventByStatus = new ArrayList<EventEntity>();
		if(eventStatus.equals("overdue")){
	    	LocalDate currentDate=LocalDate.now();
			ArrayList<EventEntity> pendingList = new ArrayList<EventEntity>();
			pendingList.addAll(eventRepo.findByEventStatus("pending"));
			for(EventEntity event : pendingList) {
				if(event.getEventDate().isBefore(currentDate)) {
					eventByStatus.add(event);
				}
			}

		}
		eventByStatus.addAll(eventRepo.findByEventStatus(eventStatus));
		return eventByStatus;
	}
}
