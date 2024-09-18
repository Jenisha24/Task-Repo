package com.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.service.RestaurantService;
import com.restaurant.vo.AvailabilityVo;
import com.restaurant.vo.BookingVo;
import com.restaurant.vo.MealsVo;
import com.restaurant.vo.SeatingAvailabilityVo;
import com.restaurant.vo.SeatsVo;
import com.restaurant.vo.TablesVo;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	RestaurantService restaurantService;
	
	
	@PostMapping("/tables")
	public String addTable(@RequestHeader("userId") int userId, @RequestBody TablesVo tableDetails) {
		return restaurantService.table(tableDetails,userId);
	}
	
	@GetMapping("/tables")
	public ArrayList<TablesVo> getTable() {
		return restaurantService.getTable();
	}

	@PutMapping("/tables")
	public String updateTable(@RequestHeader("userId") int userId, @RequestBody TablesVo tableDetails) {
		return restaurantService.table(tableDetails,userId);
	}
	
	@DeleteMapping("/tables/{tableId}")
	public String deleteTable(@RequestHeader("userId") int userId, @PathVariable int tableId) {
		return restaurantService.deleteTable(tableId,userId);

	}
	@PostMapping("/meals")
	public String addMeals(@RequestHeader("userId") int userId, @RequestBody MealsVo mealsDetails) {
		return restaurantService.addOrUpdate(mealsDetails,userId);
	}
	
	@GetMapping("/meals")
	public ArrayList<MealsVo> getMeals() {
		return restaurantService.getMeals();
	}
	
	@PutMapping("/meals")
	public String updateMeals(@RequestHeader("userId") int userId, @RequestBody MealsVo mealsDetails) {
		return restaurantService.addOrUpdate(mealsDetails,userId);
	}
	
	
	@DeleteMapping("/meals/{mealId}")
	public String deleteMeals(@RequestHeader("userId") int userId, @PathVariable int mealId) {
		return restaurantService.deleteMeals(mealId,userId);
	}
	
	@PostMapping("/booking")
	public String addBooking(@RequestHeader("userId") int userId, @RequestBody BookingVo bookingDetails) {
		return restaurantService.addBooking(bookingDetails,userId);
	}
	
	@GetMapping("/booking")
	public List<SeatingAvailabilityVo> availableSeats( @RequestBody AvailabilityVo bookingDetails) {
		return restaurantService.availableSeats(bookingDetails);
	}
	
	@PutMapping("/booking/{bookingId}")
	public String cancelBooking(@RequestHeader("userId") int userId, @PathVariable int bookingId) {
		return restaurantService.cancelBooking(bookingId,userId);
	}
	
	@PutMapping("/booking")
	public String addSeats(@RequestHeader("userId") int userId, @RequestBody SeatsVo seats) {
		return restaurantService.addSeats(seats,userId);
	}
	
	@GetMapping("/booking/list")
	public ArrayList<BookingVo> listBooking(@RequestHeader("userId") int userId) {
		return restaurantService.listBooking(userId);
	}
	
}	
