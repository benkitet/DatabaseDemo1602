package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Category;
import models.Product;
import models.ShoppingCart;

public final class ProductService extends Service {
	
	public Product addProduct(Product product) {			
		try {
			int id = super.dbm.insert(
				"Product", 
				new String[] {"productNumber", "name", "price", "picture", "description"}, 
				new String[] {product.getNumber(), product.getName(), String.valueOf(product.getPrice()), product.getPicture(), product.getDescription()}
			);
			
			if(id != 0) {
				product.setId(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return product;
	}
	
	public Product addCategory(Product product, Category category) {
		try {
			super.dbm.insert(
				"Product_Category", 
				new String[] {"ProductID", "CategoryID"}, 
				new String[] {String.valueOf(product.getId()), String.valueOf(category.getId())}
			);
			
			product.getCategories().add(category);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	public Product deleteCategory(Product product, Category category) {
		try {
			super.dbm.delete(
				"Product_Category", 
				new String[] {String.valueOf(product.getId()), String.valueOf(category.getId())}
			);
			product.getCategories().remove(category);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	public boolean updateProduct(Product product) throws SQLException {
		return super.dbm.update(
			"Product", 
			new String[] {"productNumber", "name", "price", "picture", "description"}, 
			new String[] {product.getNumber(), product.getName(), String.valueOf(product.getPrice()), product.getPicture(), product.getDescription()},
			new String[] {"id", "=", String.valueOf(product.getId())}
		);
	}
	
	
	public boolean deleteProduct(Product product) throws SQLException {
		return super.dbm.delete(
			"Product", 
			new String[] {"id", "=", String.valueOf(product.getId())}
		);
	}
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			ResultSet rs = super.dbm.select(
					"Product",
					new String[] {"id", "productNumber", "name", "price", "picture", "description"}, 
					null
			);
			
			if(!rs.next()) {
			} else {
				do {
					Product p = new Product(
						rs.getInt("id"), 
						rs.getString("productNumber"), 
						rs.getString("name"), 
						rs.getDouble("price"),
						rs.getString("picture"),
						rs.getString("description")
					);
					
					CategoryService cs = new CategoryService();
					p.setCategories(cs.getCategoriesByProduct(p));
					
					products.add(p);
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}
	
	public ArrayList<Product> getProducts(Category category) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			ResultSet rs = super.dbm.select(
				"Product_Category", 
				new String[] {"CategoryId", "ProductId"}, 
				new String[] {"CategoryId", "=", String.valueOf(category.getId())}
			);
			ProductService ps = new ProductService();
			
			while(rs.next()) {
				products.add(ps.getProductById(rs.getInt("ProductId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public HashMap<Product, Integer> getProducts(ShoppingCart shoppingcart) {
		HashMap<Product, Integer> products = new HashMap<Product, Integer>();
		try {
			ResultSet rs = super.dbm.select(
				"ShoppingCart_Product", 
				new String[] {"ProductId", "ShoppingCartId", "menge"}, 
				new String[] {"ShoppingCartId", "=", String.valueOf(shoppingcart.getId())}
			);
			ProductService ps = new ProductService();
			
			while(rs.next()) {
				products.put(ps.getProductById(rs.getInt("ProductId")), rs.getInt("menge"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public Product getProductById(int id) {
		Product product = new Product();
		
		try {
			ResultSet rs = super.dbm.select(
					"Product",
					new String[] {"id", "productNumber", "name", "price", "picture", "description"}, 
					new String[] {"id", "=", String.valueOf(id)} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					product = new Product(
							rs.getInt("id"), 
							rs.getString("productNumber"), 
							rs.getString("name"), 
							rs.getDouble("price"),
							rs.getString("picture"),
							rs.getString("description")	
					);
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return product;
	}
	
	public Product getProductByName(String name) {
		Product product = new Product();
		
		try {
			ResultSet rs = super.dbm.select(
					"Product",
					new String[] {"id", "productNumber", "name", "price", "picture", "description"}, 
					new String[] {"name", "=", name} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					product = new Product(
							rs.getInt("id"), 
							rs.getString("productNumber"), 
							rs.getString("name"), 
							rs.getDouble("price"),
							rs.getString("picture"),
							rs.getString("description")	
					);
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return product;
	}
}



