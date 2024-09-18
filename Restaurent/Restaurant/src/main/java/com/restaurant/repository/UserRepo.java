package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	@Query("SELECT u.role FROM Users u WHERE u.userId = :userId")
    String findRoleByUserId(int userId);

}
