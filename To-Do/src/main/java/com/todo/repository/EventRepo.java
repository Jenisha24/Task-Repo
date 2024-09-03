package com.todo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.entity.Events;

@Repository
public interface EventRepo extends JpaRepository<Events, Integer> {
 
	ArrayList<Events> findByEventStatus( String eventStatus);

	

}
