package com.restaurant.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.entity.Booking;
import com.restaurant.vo.BookingVo;

public interface BookingRepo extends JpaRepository<Booking, Integer>{


	@Query("SELECT b.bookedSeat FROM Booking b WHERE b.mealId = :mealId AND b.bookingDate = :bookingDate")
	Integer findBookedSeatByMealIdAndBookingDate(@Param("mealId") int mealId, @Param("bookingDate") LocalDate bookingDate);
	 
	 @Query("SELECT b.tableId FROM Booking b WHERE b.mealId = :mealId AND b.bookingDate = :bookingDate")
	 Integer findTableIdByMealIdAndBookingDate(@Param("mealId") int mealId, @Param("bookingDate") LocalDate bookingDate);

	 boolean existsByMealIdAndTableIdAndBookingDate(int mealId, int tableId, LocalDate bookingDate);

	 @Query("SELECT b FROM Booking b WHERE b.userId IS NOT NULL AND b.userId = :userId")
	  List<Booking> findBookingsByUserId(@Param("userId") int userId);

	 @Query("SELECT (t.seatingCapacity - COALESCE(SUM(b.bookedSeat), 0)) AS availableSeats FROM Tables t LEFT JOIN Booking b ON t.tableId = b.tableId AND b.mealId = :mealId AND b.bookingDate = :bookingDate AND b.bookingStatus = 'Booked' WHERE t.tableId = :tableId GROUP BY t.tableId, t.seatingCapacity")
	Integer findAvailableSeats(@Param("mealId") int mealId,  @Param("bookingDate") LocalDate bookingDate,  @Param("tableId") int tableId);
}
