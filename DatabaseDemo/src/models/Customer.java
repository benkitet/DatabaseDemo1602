package models;

public class Customer {
	private int id;
	private String firstname;
	private String lastname;
	private String phone;

	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public String getFirstname() { return this.firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname; }
	
	public String getLastname() { return this.lastname; }
	public void setLastname(String lastname) { this.lastname = lastname; }
	
	public String getPhone() { return this.phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public Customer() {
		this(0, "", "", "");
	}
	
	public Customer(String firstname, String lastname, String phone) {
		this(0, firstname, lastname, phone);
	}
	
	public Customer(int id, String firstname, String lastname, String phone) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setPhone(phone);
	}
	
	@Override
	public String toString() {
		return "Customer [getId()=" + this.getId() + 
				", getFirstname()=" + this.getFirstname() +
				", getLastname()=" + this.getLastname() +
				", getIPhone()=" + this.getPhone() +"]";
	}
}
