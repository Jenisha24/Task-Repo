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

import com.todo.service.EventService;
import com.todo.vo.EventDetailVo;

@RestController
@RequestMapping("/todo")
public class EventController {
	@Autowired
	EventService eventService;

	@PostMapping("/event")
	public String saveEvent(@RequestBody EventDetailVo eventDetails) {
		return eventService.event(eventDetails);
	}

	@PutMapping("/event")
	public String updateEvent(@RequestBody EventDetailVo eventDetail) {
		return eventService.event(eventDetail);
	}

	@DeleteMapping("/event/{eventId}")
	public String deleteEvent(@PathVariable int eventId) {
		return eventService.deleteEvent(eventId);

	}

	@PutMapping("/event/{eventId}")
	public String updateStatus(@PathVariable int eventId) {
		return eventService.updateStatus(eventId);
	}

	@GetMapping("/event")
	public ArrayList<EventDetailVo> getEvent() {
		return eventService.getEvent();
	}

	@GetMapping("/event/{eventStatus}")
	public ArrayList<EventDetailVo> getStatus(@PathVariable String eventStatus) {
		return eventService.getStatus(eventStatus);
	}

}
