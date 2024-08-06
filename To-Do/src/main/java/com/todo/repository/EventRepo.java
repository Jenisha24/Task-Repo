package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.entity.EventEntity;
@Repository
public interface EventRepo extends JpaRepository<EventEntity, Integer> {

}
