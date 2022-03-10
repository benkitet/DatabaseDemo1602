package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Booking;
import models.Customer;
import models.ShoppingCart;

public class BookingService extends Service {
		
	public Booking addBooking(Booking booking) {			
		try {
			int id = super.dbm.insert(
				"Booking", 
				new String[] {"customer", "shoppingcart"}, 
				new String[] {String.valueOf(booking.getCustomer().getId()), String.valueOf(booking.getShoppingcart().getId())}
			);
			
			if(id != 0) {
				booking.setId(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return booking;
	}
			
	public boolean updateBooking(Booking booking) {
		try {
			return super.dbm.update(
				"Booking", 
				new String[] {"customer", "shoppingcart"}, 
				new String[] {String.valueOf(booking.getCustomer().getId()), String.valueOf(booking.getShoppingcart().getId())},
				new String[] {"id", "=", String.valueOf(booking.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBooking(Booking booking) {
		try {
			return super.dbm.delete(
				"Booking", 
				new String[] {"id", "=", String.valueOf(booking.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Booking> getBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		try {
			ResultSet rs = super.dbm.select(
				"Booking",
				new String[] {"id", "customer", "shoppingcart"}, 
				null
			);
			
			if(!rs.next()) {
			} else {
				do {
					int bookingid = rs.getInt("id");
					int customerid = rs.getInt("customer");
					int shoppingcartid = rs.getInt("shoppingcart");
					
					CustomerService cs = new CustomerService();
					ShoppingCartService scs = new ShoppingCartService();
					
					Customer customer = cs.getCustomerById(customerid);
					ShoppingCart shoppingcart = scs.getShoppingCartById(shoppingcartid);
					
					Booking booking = new Booking(bookingid, customer, shoppingcart);
					
					bookings.add(booking);
					
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return bookings;
	}
	
	public Booking getBookingById(int id) {
		Booking booking = new Booking();
		
		try {
			ResultSet rs = super.dbm.select(
				"Booking",
				new String[] {"id", "customer", "shoppingcart"}, 
				new String[] {"id", "=", String.valueOf(id)} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					int bookingid = rs.getInt("id");
					int customerid = rs.getInt("customer");
					int shoppingcartid = rs.getInt("shoppingcart");
					
					CustomerService cs = new CustomerService();
					ShoppingCartService scs = new ShoppingCartService();
					
					Customer customer = cs.getCustomerById(customerid);
					ShoppingCart shoppingcart = scs.getShoppingCartById(shoppingcartid);
					
					booking = new Booking(bookingid, customer, shoppingcart);

				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return booking;
	}
}
