package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Customer;

public class CustomerService extends Service {
		
	public Customer addCustomer(Customer customer) {			
		try {
			int id = super.dbm.insert(
				"Customer", 
				new String[] {"firstname", "lastname", "phone"}, 
				new String[] {customer.getFirstname(), customer.getLastname(),customer.getPhone()}
			);
			
			if(id != 0) {
				customer.setId(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return customer;
	}
			
	public boolean updateCustomer(Customer customer) {
		try {
			return super.dbm.update(
				"Customer", 
				new String[] {"firstname", "lastname", "phone"}, 
				new String[] {customer.getFirstname(), customer.getLastname(),customer.getPhone()},
				new String[] {"id", "=", String.valueOf(customer.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCustomer(Customer customer) {
		try {
			return super.dbm.delete(
				"Customer", 
				new String[] {"id", "=", String.valueOf(customer.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try {
			ResultSet rs = super.dbm.select(
				"Customer",
				new String[] {"id", "firstname", "lastname", "phone"}, 
				null
			);
			
			if(!rs.next()) {
			} else {
				do {
					customers.add(
						new Customer(
							rs.getInt("id"), 
							rs.getString("firstname"),
							rs.getString("lastname"),
							rs.getString("phone")
						)
					);
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return customers;
	}
	
	public Customer getCustomerById(int id) {
		Customer customer = new Customer();
		
		try {
			ResultSet rs = super.dbm.select(
				"Customer",
				new String[] {"id", "firstname", "lastname", "phone"}, 
				new String[] {"id", "=", String.valueOf(id)} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					customer = new Customer(
						rs.getInt("id"), 
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("phone")
					);
				} while(rs.next());
			}

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}
	
	public Customer getCustomerByName(String name) {
		Customer customer = new Customer();
		
		try {
			ResultSet rs = super.dbm.select(
				"Customer",
				new String[] {"id", "firstname", "lastname", "phone"},
				new String[] {"name", "=", name} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					customer = new Customer(
						rs.getInt("id"), 
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("phone")
					);
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		return customer;
	}
}
