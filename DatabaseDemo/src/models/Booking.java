package models;

public class Booking {
	
	private int id;
	private Customer customer;
	private ShoppingCart shoppingcart;

	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public Customer getCustomer() {	return this.customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }

	public ShoppingCart getShoppingcart() {	return shoppingcart; }
	public void setShoppingcart(ShoppingCart shoppingcart) { this.shoppingcart = shoppingcart; }

	public Booking() {
		this(0, null, null);
	}
	
	public Booking(Customer customer, ShoppingCart shoppingcart) {
		this(0, customer, shoppingcart);
	}
	
	public Booking(int id, Customer customer, ShoppingCart shoppingcart) {
		this.setId(id);
		this.setCustomer(customer);
		this.setShoppingcart(shoppingcart);
	}
	
	@Override
	public String toString() {
		return "Booking [getId()=" + this.getId() + "]";
	}
}
