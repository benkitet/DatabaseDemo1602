package models;

import java.util.ArrayList;

/**
 * Erstellt eine Klasse Product, basierend auf folgendem Datenbankdesign.
 * 
 * CREATE TABLE "Product" (
 *	"id" INTEGER NOT NULL,
 *	"number" TEXT,
 *	"name" TEXT,
 *	"price" NUMERIC,
 *	"picture" TEXT,
 *	"description" TEXT,
 *	PRIMARY KEY("id" AUTOINCREMENT)
 * );
 * 
 * @author Michel Hegewald
 * @version 1.0.0
 */

public class Product {

	private int id;
	private String number;
	private String name;
	private double price;
	private String picture;
	private String description;
	private ArrayList<Category> categories;
	
	
	/**
	 * Minimalkonstruktor.
	 */
	public Product() {
		this(0, "", "", 0.0, "", "");
	}
	
	
	/**
	 * Konstruktor ohne ID. (wird auf 0 gesetzt)
	 * 
	 * @param number
	 * @param name
	 * @param price
	 * @param picture
	 * @param description
	 */
	public Product(String number, String name, double price, String picture, String description) {
		this.setId(0);
		this.setNumber(number);
		this.setName(name);
		this.setPrice(price);
		this.setPicture(picture);
		this.setDescription(description);
		this.setCategories(new ArrayList<Category>());
	}
	
	
	/**
	 * Maximalkonstruktor.
	 * 
	 * @param id
	 * @param number
	 * @param name
	 * @param price
	 * @param picture
	 * @param description
	 */
	public Product(int id, String number, String name, double price, String picture, String description) {
		this(number, name, price, picture, description);
		this.setId(id);
	}


	/**
	 * Getter für 'id'.
	 * 
	 * @return
	 */
	public int getId() { return this.id; }


	/**
	 * Setter für 'id'.
	 * 
	 * @param id
	 */
	public void setId(int id) { this.id = id; }


	/**
	 * Getter für 'number'.
	 * 
	 * @return
	 */
	public String getNumber() { return this.number; }


	/**
	 * Setter für 'number'.
	 * 
	 * @param number
	 */
	private void setNumber(String number) { this.number = number; }


	/**
	 * Getter für 'name'.
	 * 
	 * @return
	 */
	public String getName() { return this.name; }


	/**
	 * Setter für 'name'.
	 * 
	 * @param name
	 */
	private void setName(String name) { this.name = name; }


	/**
	 * Getter für 'price'.
	 * 
	 * @return
	 */
	public double getPrice() { return this.price; }


	/**
	 * Setter für 'price'.
	 * 
	 * @param price
	 */
	private void setPrice(double price) { this.price = price; }


	/**
	 * Getter für 'picture'.
	 * 
	 * @return
	 */
	public String getPicture() { return this.picture; }

	
	/**
	 * Setter für 'picture'.
	 * 
	 * @param picture
	 */
	private void setPicture(String picture) { this.picture = picture; }


	/**
	 * Getter für 'description'.
	 * 
	 * @return
	 */
	public String getDescription() { return this.description; }


	/**
	 * Setter für 'description'.
	 * 
	 * @param description
	 */
	private void setDescription(String description) { this.description = description; }
	
	/**
	 * Getter für 'categories'.
	 * 
	 * @return
	 */
	public ArrayList<Category> getCategories() { return this.categories; }


	/**
	 * Setter für 'categories'.
	 * 
	 * @param categories
	 */
	public void setCategories(ArrayList<Category> categories) { this.categories = categories; }

	
	/**
	 * Die 'equals()' Methode.
	 * 
	 * @param product
	 * @return
	 */
	public boolean equals(Product product) {
		return this.getId() == product.getId() &&
				this.getPrice() == product.getPrice();
	}

	
	/**
	 * Überschreibung der 'toString()' Methode.
	 */
	@Override
	public String toString() {
		return "Product [getId()=" + getId() +
				", getNumber()=" + getNumber() +
				", getName()=" + getName() +
				", getPrice()=" + getPrice() +
				", getPicture()=" + getPicture() +
				", getDescription()=" + getDescription() + "]";
	}
	
	
}
