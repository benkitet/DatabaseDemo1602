package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Product;
import models.ShoppingCart;

public class ShoppingCartService extends Service {
	public void addShoppingCart(ShoppingCart shoppingcart) {
		try {
			super.dbm.insert(
				"ShoppingCart", 
				new String[] {"id"}, 
				new String[] {null}
			);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ShoppingCart addProduct(ShoppingCart shoppingcart, Product product, int count) {
		try {
			super.dbm.insert(
				"ShoppingCart_Product", 
				new String[] {"ShoppingCartId", "ProductId", "menge"}, 
				new String[] {String.valueOf(shoppingcart.getId()), String.valueOf(product.getId()), String.valueOf(count)}
			);
			shoppingcart.getProducts().put(product, count);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return shoppingcart;
	}
		
	public void deleteShoppingCart(ShoppingCart shoppingcart) {
		try {
			super.dbm.delete(
				"ShoppingCart", 
				new String[] {String.valueOf(shoppingcart.getId())}
			);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteProduct(ShoppingCart shoppingcart, Product product) {
		try {
			super.dbm.delete(
				"ShoppingCart_Product", 
				new String[][] { 
					new String[] {"ShoppingCartId", "=", String.valueOf(shoppingcart.getId())},
					new String[] {"ProductId", "=", String.valueOf(product.getId())} 
				}
			);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<ShoppingCart> getShoppingCarts() {
		ArrayList<ShoppingCart> shoppingcarts = new ArrayList<ShoppingCart>();
		
		try {
			ResultSet rs = super.dbm.select(
				"ShoppingCart",
				new String[] {"id"}, 
				null
			);
			
			if(!rs.next()) {
			} else {
				ProductService ps = new ProductService();
				do {
					ShoppingCart shoppingcart = new ShoppingCart(
						rs.getInt("id")
					);
					
					shoppingcart.setProducts(ps.getProducts(shoppingcart));

					shoppingcarts.add(shoppingcart);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shoppingcarts;
	}
	
	public ShoppingCart getShoppingCartById(int id) {
		ShoppingCart shoppingcart = new ShoppingCart();
		
		try {
			ResultSet rs = super.dbm.select(
				"ShoppingCart",
				new String[] {"id"},
				new String[] {"id", "=", String.valueOf(id)} 
			);
			
			if(!rs.next()) {
			} else {
				ProductService ps = new ProductService();
				do {
					shoppingcart = new ShoppingCart(
						rs.getInt("id")
					);
					shoppingcart.setProducts(ps.getProducts(shoppingcart));
				} while(rs.next());
			}

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return shoppingcart;
	}
}
