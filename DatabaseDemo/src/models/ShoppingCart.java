package models;

import java.util.HashMap;

public class ShoppingCart {
	private int id;
	private HashMap<Product, Integer> products;
	
	public int getId() { return this.id; }
	public void setId(int id) {	this.id = id; }
	
	public HashMap<Product, Integer> getProducts() { return this.products; }
	public void setProducts(HashMap<Product, Integer> products) { this.products = products; }
	
	public ShoppingCart(int id) { this.setId(id); this.setProducts(new HashMap<Product, Integer>()); }
	public ShoppingCart() { this(0); }
	
	@Override
	public String toString() {
		return "ShoppingCart [getId()=" + this.getId() + "]";
	}
}
