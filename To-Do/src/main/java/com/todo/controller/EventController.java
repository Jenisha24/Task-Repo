package com.todo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.EventEntity;
import com.todo.service.EventService;

@RestController
@RequestMapping("/todo")
public class EventController {
	@Autowired
	EventService eventService;
	
	@PostMapping("/save")
	public String saveEvent(@RequestBody EventEntity eventEntity ) {
		return eventService.saveEvent(eventEntity);
	}
	
	@PutMapping("/update")
	public EventEntity updateEvent(@RequestBody EventEntity eventEntity) {
		return eventService.updateEvent(eventEntity);
		
	}
	
	@DeleteMapping("/delete/{eventId}")
	public String deleteEvent(@PathVariable int eventId) {
		return eventService.deleteEvent(eventId);
		
	}
	
	@PutMapping("/completed/{eventId}")
	public String  updateStatus(@PathVariable int eventId) {
		return eventService.updateStatus(eventId);
	}
	
	@GetMapping("/get")
	public ArrayList<Map<String, ArrayList<EventEntity>>> getEvent() {
		return eventService.getEvent();
	}
	
	@GetMapping("/get/{eventStatus}")
	public ArrayList<EventEntity> getStatus(@PathVariable String eventStatus){
		return eventService.getStatus(eventStatus);
	}
	
	
}

