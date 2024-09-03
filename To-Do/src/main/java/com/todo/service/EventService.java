package com.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.Events;
import com.todo.repository.EventRepo;
import com.todo.vo.EventDetailVo;

@Service
public class EventService {

	@Autowired
	EventRepo eventRepo;

	@Autowired
	ModelMapper modelMapper;

//	save & update an event
	public String event(EventDetailVo eventDetails) {
		if (eventDetails.getEventId() == 0 && eventDetails.getEventStatus() == null) {
			Events newEventDetails = new Events();
			newEventDetails = modelMapper.map(eventDetails, Events.class);
			newEventDetails.setEventStatus("pending");
			eventRepo.save(newEventDetails);
			return "event details has been saved successfully";
		} else {
			Events newEventDetails = eventRepo.findById(eventDetails.getEventId()).get();
			newEventDetails = modelMapper.map(eventDetails, Events.class);
			eventRepo.save(newEventDetails);
			return "event details updated successfully";
		}
	}

//	delete an event
	public String deleteEvent(int eventId) {
		eventRepo.deleteById(eventId);
		return "event has been deleted successfully";

	}

//	status update to completed
	public String updateStatus(int eventId) {
		Events eventData = eventRepo.findById(eventId).get();
		eventData.setEventStatus("completed");
		eventRepo.save(eventData);
		return "event has been completed";
	}

//	Get all the created events
	
	public ArrayList<EventDetailVo> getEvent(){
		 ArrayList<EventDetailVo> allEvents=new ArrayList<EventDetailVo>();
		 List<Events> events=eventRepo.findAll();
		 for(Events event : events) {
			 EventDetailVo eventDetail = modelMapper.map(event,EventDetailVo.class);
			 allEvents.add(eventDetail);
		 }
		 return allEvents;
		 
	}
	
	
	
//	public ArrayList<Map<String, ArrayList<EventDetailVo>>> getEvent() {
//		ArrayList<Map<String, ArrayList<EventDetailVo>>> allEvent = new ArrayList<>();
//		Map<String, ArrayList<EventDetailVo>> eventByStatus = new HashMap<>();
//		eventByStatus.put("pending", getStatus("pending"));
//		eventByStatus.put("overdue", getStatus("overdue"));
//		eventByStatus.put("completed", getStatus("completed"));
//		allEvent.add(eventByStatus);
//		return allEvent;
//
//	}
	
	

//	 group the events by status
	public ArrayList<EventDetailVo> getStatus(String eventStatus) {
		ArrayList<EventDetailVo> eventByStatus = new ArrayList<EventDetailVo>();
		if (eventStatus.equals("overdue") || eventStatus.equals("pending")) {
			LocalDate currentDate = LocalDate.now();
			ArrayList<Events> pendingList = new ArrayList<Events>();
			pendingList.addAll(eventRepo.findByEventStatus("pending"));
			for (Events event : pendingList) {
				if (event.getEventDate().isBefore(currentDate) && eventStatus.equals("overdue")) {
					EventDetailVo eventRes = modelMapper.map(event, EventDetailVo.class);
					eventRes.setEventStatus("overdue");
					eventByStatus.add(eventRes);
				} else if (event.getEventDate().isAfter(currentDate) && eventStatus.equals("pending")) {
					EventDetailVo eventRes = modelMapper.map(event, EventDetailVo.class);
					eventByStatus.add(eventRes);
				}
			}
		} else {
			ArrayList<Events> eventDetails = eventRepo.findByEventStatus(eventStatus);
			for (Events event : eventDetails) {
				EventDetailVo eventRes = modelMapper.map(event, EventDetailVo.class);
				eventByStatus.add(eventRes);
			}
		}
		return eventByStatus;
	}
}
