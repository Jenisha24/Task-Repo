package com.restaurant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import com.restaurant.entity.Tables;
import com.restaurant.vo.SeatingAvailabilityVo;

@Repository
public interface TableRepo extends JpaRepository<Tables, Integer> {
	
	@Query("SELECT t.tableId FROM Tables t WHERE t.tableName = :tableName")
	Integer findTableIdByTableName(String tableName);

	
	@Query("SELECT t.seatingCapacity FROM Tables t WHERE t.tableId = :tableId")
	Integer findSeatingCapacityByTableId(int tableId);

	
	@Query("SELECT t.tableName FROM Tables t WHERE t.tableId = :tableId")
	String findTableNameByTableId(@Param("tableId") int tableId);
	
	
	
	@Query("SELECT (t.seatingCapacity - COALESCE(b.bookedSeat, 0)) AS availableSeats, t.tableId " + "FROM Tables t LEFT JOIN Booking b ON t.tableId = b.tableId " + "AND b.mealId = :mealId AND b.bookingDate = :bookingDate AND b.bookingStatus = 'Booked'")
	List<Object[]> findSeatingAvailability(@Param("mealId") int mealId, @Param("bookingDate") LocalDate bookingDate);
}
