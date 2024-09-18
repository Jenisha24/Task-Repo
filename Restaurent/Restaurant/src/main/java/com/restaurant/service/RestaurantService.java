package com.restaurant.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.restaurant.entity.Booking;
import com.restaurant.entity.Meals;
import com.restaurant.entity.Tables;
import com.restaurant.entity.Users;
import com.restaurant.repository.BookingRepo;
import com.restaurant.repository.MealsRepo;
import com.restaurant.repository.TableRepo;
import com.restaurant.repository.UserRepo;
import com.restaurant.vo.AvailabilityVo;
import com.restaurant.vo.BookingVo;
import com.restaurant.vo.MealsVo;
import com.restaurant.vo.SeatingAvailabilityVo;
import com.restaurant.vo.SeatsVo;
import com.restaurant.vo.TablesVo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantService {
	@Autowired
	TableRepo tableRepo;
	@Autowired
	MealsRepo mealsRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BookingRepo bookingRepo;
	@Autowired
	ModelMapper modelMapper;

	// add and update table
	public String table(TablesVo tableDetails, int userId) {
		try {
			String role = userRepo.findRoleByUserId(userId);
			if (role.equals("admin")) {
				if (tableDetails.getTableId() == 0) {
					Tables addDetails = new Tables();
					addDetails = modelMapper.map(tableDetails, Tables.class);
					tableRepo.save(addDetails);
					return "Table details added sucessfully";
				} else {
					Tables updateDetails = tableRepo.findById(tableDetails.getTableId()).get();
					updateDetails = modelMapper.map(tableDetails, Tables.class);
					tableRepo.save(updateDetails);
					return "Table details updated successfully";
				}
			}
			return "Only admin can manage tables";
		} catch (Exception e) {
			return "Invalid Id";
		}
	}
	
//	get table
	public ArrayList<TablesVo> getTable(){
		 ArrayList<TablesVo> allTables=new ArrayList<TablesVo>();
		 List<Tables> tables=tableRepo.findAll();
		 for(Tables table : tables) {
			 TablesVo tableDetail = modelMapper.map(table,TablesVo.class);
			 allTables.add(tableDetail);
		 }
		 return allTables;
		 
	}

	// delete table
	public String deleteTable(int tableId, int userId) {

		String role = userRepo.findRoleByUserId(userId);
		if (role.equals("admin")) {
			if (!tableRepo.existsById(tableId)) {
				return "Invalid Id";
			} else {
				tableRepo.deleteById(tableId);
				return "table has been deleted successfully";

			}
		}
		return "Only admin can manage tables";
	}

	// add and update meals
	public String addOrUpdate(MealsVo mealsDetails, int userId) {
		try {
			String role = userRepo.findRoleByUserId(userId);
			if (role.equals("admin")) {
				if (mealsDetails.getMealId() == 0) {
					Meals addDetails = new Meals();
					addDetails = modelMapper.map(mealsDetails, Meals.class);
					mealsRepo.save(addDetails);
					return "meals details added sucessfully";
				} else {
					Meals updateDetails = mealsRepo.findById(mealsDetails.getMealId()).get();
					updateDetails = modelMapper.map(mealsDetails, Meals.class);
					mealsRepo.save(updateDetails);
					return "meals details updated successfully";
				}
			}
			return "Only admin can manage meals";
		} catch (Exception e) {
			return "Invalid Id";
		}
	}
	
	
//	get meals
	public ArrayList<MealsVo> getMeals(){
		 ArrayList<MealsVo> allMeals=new ArrayList<MealsVo>();
		 List<Meals> meals=mealsRepo.findAll();
		 for(Meals meal : meals) {
			 MealsVo mealsDetail = modelMapper.map(meal,MealsVo.class);
			 allMeals.add(mealsDetail);
		 }
		 return allMeals;
	}	 

	// delete meals
	public String deleteMeals(int mealId, int userId) {
		String role = userRepo.findRoleByUserId(userId);
		if (role.equals("admin")) {
			if (!mealsRepo.existsById(mealId)) {
				return "Invalid Id";
			} else {
				mealsRepo.deleteById(mealId);
				return "meals has been deleted successfully";
			}
		}
		return "Only admin can manage meals";

	}

	// booking
	public String addBooking(BookingVo bookingDetails, int userId) {
		try {
		String role = userRepo.findRoleByUserId(userId);
		if (!role.equals("user")) {
            return "Only users can manage bookings";
        }			
		LocalDate bookingDate = bookingDetails.getBookingDate();
		if (bookingDate == null || bookingDate.isBefore(LocalDate.now())) {
            return "Invalid booking date. Date must be today or in the future.";
	    }
        String mealType = bookingDetails.getMealType();
        int mealId = mealsRepo.findMealIdByMealType(mealType);
        String tableName = bookingDetails.getTableName();
        int tableId = tableRepo.findTableIdByTableName(tableName);
		int seatingCapacity=tableRepo.findSeatingCapacityByTableId(tableId);
		if(bookingDetails.getBookedSeat()>seatingCapacity) {
			return "Invalid number of booked seats. Seats exceed the table's capacity";

		}	
        if (bookingRepo.existsByMealIdAndTableIdAndBookingDate(mealId, tableId, bookingDate)) {
            return "Table is not available for booking on this date";
        } 
        
		Booking addBooking = modelMapper.map(bookingDetails, Booking.class);
		addBooking.setMealId(mealId);
		addBooking.setTableId(tableId);
		addBooking.setBookingStatus("Booked");
		addBooking.setUserId(userId);
		bookingRepo.save(addBooking);
		return "Booking Successfully";
		}
		catch (DateTimeException e) {
	        return "An error occurred with the date input: " + e.getMessage();
		}   
		catch (Exception e) {
	        return "An error occurred: Invalid input";
	    }
	}

	// check availability
	public List<SeatingAvailabilityVo> availableSeats(AvailabilityVo bookingDetails) {
		List<SeatingAvailabilityVo> seatingAvailabilityList = new ArrayList<>();
		int mealId = mealsRepo.findMealIdByMealType(bookingDetails.getMealType());
		LocalDate bookingDate = bookingDetails.getBookingDate();

		List<Tables> allTables = tableRepo.findAll();
		
		allTables.forEach(table -> {
			SeatingAvailabilityVo tableAvailability = new SeatingAvailabilityVo();
			tableAvailability.setMealType(bookingDetails.getMealType());
			tableAvailability.setBookingDate(bookingDate);
			tableAvailability.setTableName(table.getTableName());

			int availableSeats;

			Integer bookedSeat = bookingRepo.findBookedSeatByMealIdAndBookingDate(mealId, bookingDate);
			Integer tableId = bookingRepo.findTableIdByMealIdAndBookingDate(mealId, bookingDate);

			if (bookedSeat != null && tableId != null && table.getTableId() == tableId) {
				int seatingCapacity = tableRepo.findSeatingCapacityByTableId(tableId);
				availableSeats = seatingCapacity - bookedSeat;
			} else {
				availableSeats = table.getSeatingCapacity();
			}

			tableAvailability.setAvailableSeats(availableSeats);
			seatingAvailabilityList.add(tableAvailability);
		});
		
		return seatingAvailabilityList;
		
		
	}

//	 cancel booking
	public String cancelBooking(int bookingId, int userId) {
		String role = userRepo.findRoleByUserId(userId);
		if (!role.equals("user")) {
            return "Only users can manage bookings";
        }			
		if (!bookingRepo.existsById(bookingId)) {
			return "Invalid Id";
		} else {
			Booking bookingStatus = bookingRepo.findById(bookingId).get();
			if(bookingStatus.getUserId()!=userId) {
				return "invalid user";
			}	
			bookingStatus.setBookingStatus("cancelled");
			bookingRepo.save(bookingStatus);
			return "Booking cancelled";
			}
			
	}

//	 add extra persons
	public String addSeats(SeatsVo seats, int userId) {
		try {
		String role = userRepo.findRoleByUserId(userId);
		if (!role.equals("user")) {
            return "Only users can manage seating bookings";
        }			
		Booking bookingDetails = bookingRepo.findById(seats.getBookingId()).orElseThrow(() -> new EntityNotFoundException("Booking ID not found"));
		if(bookingDetails.getUserId()!=userId) {
			return "invalid user";
		}	
		int seatingCapacity=tableRepo.findSeatingCapacityByTableId(bookingDetails.getTableId());
		if(seats.getBookedSeat()<=seatingCapacity) {
			bookingDetails.setBookedSeat(seats.getBookedSeat());
			bookingRepo.save(bookingDetails);
			return "Booked seats are updated successfully";
		}
		else {
			return "Invalid number of booked seats. Seats exceed the table's capacity";
		}
		
		}
		catch (Exception e) {
	        return "An error occurred: Invalid input";
	    }
	}

//	 list all booking
	public ArrayList<BookingVo> listBooking(int userId) {
		ArrayList<BookingVo> bookingDetails = new ArrayList<>();
		List<Booking> getBooking = bookingRepo.findBookingsByUserId(userId);
		for (Booking booking : getBooking) {
				BookingVo allBookings = modelMapper.map(booking, BookingVo.class);
				String mealType = mealsRepo.findMealTypeByMealId(booking.getMealId());
				String tableName = tableRepo.findTableNameByTableId(booking.getTableId());
				allBookings.setMealType(mealType);
				allBookings.setTableName(tableName);
				bookingDetails.add(allBookings);
				
		}
		return bookingDetails;
		
	}
}
