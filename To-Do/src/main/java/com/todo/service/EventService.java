package com.todo.service;

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
		ArrayList<EventEntity> eventEntities = new ArrayList<>();
				eventRepo.findAll();
		 return null;

	}
}
