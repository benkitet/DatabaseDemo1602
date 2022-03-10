package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import models.Employee;

public final class EmployeeService extends Service {
	
	public Employee addEmployee(Employee emp) {		
		String hashedPassword = BCrypt.hashpw(emp.getPassword(), BCrypt.gensalt(15));
		emp.setPassword(hashedPassword);
		
		try {
			int id = super.dbm.insert(
					"Employee", 
					new String[] {"username", "password"}, 
					new String[] {emp.getUsername(), emp.getPassword()}
			);
			
			if(id != 0) {
			 	emp.setId(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return emp;
	}
	
	public boolean login(Employee emp) {
		boolean result = false;
		Employee dbemp = this.getEmployeeByName(emp.getUsername());
		if(dbemp.getId() != 0) {
			if(BCrypt.checkpw(emp.getPassword(), dbemp.getPassword())){
				result = true;
			}
		}
		return result;
	}
		
	public void updateEmployee(Employee emp) throws SQLException {
		// emp.setPassword(BCrypt.hashpw(emp.getPassword(), BCrypt.gensalt(15)));
		
		super.dbm.update(
				"Employee", 
				new String[] {"username", "password"}, 
				new String[] {emp.getUsername(), emp.getPassword()},
				new String[] {"id", "=", String.valueOf(emp.getId())}
		);
	}
	
	
	public boolean deleteEmployee(Employee emp) throws SQLException {
		return super.dbm.delete(
				"Employee", 
				new String[] {"id", "=", String.valueOf(emp.getId())}
		);
	}
	
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try {
			ResultSet rs = super.dbm.select(
					"Employee",
					new String[] {"id", "username", "password"}, 
					null
			);
			
			if(!rs.next()) {
			} else {
				do {
					employees.add(new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return employees;
	}
	
	public ResultSet getEmployeeById(int id) throws SQLException {
		return super.dbm.select(
				"Employee",
				new String[] {"id", "username", "password"},
				new String[] {"id", "=", ""+id} 
		);
	}
	
	public Employee getEmployeeByName(String name) {
		Employee emp = new Employee();
		
		try {
			ResultSet rs = super.dbm.select(
					"Employee",
					new String[] {"id", "username", "password"},
					new String[] {"username", "=", name} 
			);
			
			if(rs.next()) {
				do {
					emp = new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return emp;
	}
}
