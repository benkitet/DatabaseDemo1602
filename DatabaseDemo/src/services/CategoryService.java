package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Category;
import models.Product;

public class CategoryService extends Service {
		
	public Category addCategory(Category category) {			
		try {
			int id = super.dbm.insert(
				"Category", 
				new String[] {"name", "description"}, 
				new String[] {category.getName(), category.getDescription()}
			);
			
			if(id != 0) {
				category.setId(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return category;
	}
	
	public void addProduct(Product product, Category category) {
		try {
			super.dbm.insert(
				"Product_Category", 
				new String[] {"ProductID", "CategoryID"}, 
				new String[] {String.valueOf(product.getId()), String.valueOf(category.getId())}
			);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteProduct(Product product, Category category) {
		try {
			super.dbm.delete(
				"Product_Category", 
				new String[] {String.valueOf(product.getId()), String.valueOf(category.getId())}
			);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean updateCategory(Category Category) {
		try {
			return super.dbm.update(
				"Category", 
				new String[] {"name", "description"}, 
				new String[] {Category.getName(), Category.getDescription()},
				new String[] {"id", "=", String.valueOf(Category.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCategory(Category category) {
		try {
			return super.dbm.delete(
				"Category", 
				new String[] {"id", "=", String.valueOf(category.getId())}
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Category> getCategories() {
		ArrayList<Category> category = new ArrayList<Category>();
		
		try {
			ResultSet rs = super.dbm.select(
				"Category",
				new String[] {"id", "name", "description"}, 
				null
			);
			
			if(!rs.next()) {
			} else {
				do {
					category.add(
						new Category(
							rs.getInt("id"), 
							rs.getString("name"),
							rs.getString("description")
						)
					);
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return category;
	}
	
	public Category getCategoryById(int id) {
		Category category = new Category();
		
		try {
			ResultSet rs = super.dbm.select(
				"Category",
				new String[] {"id", "name", "description"},
				new String[] {"id", "=", String.valueOf(id)} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					category = new Category(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("description")	
					);
				} while(rs.next());
			}

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return category;
	}
	
	public Category getCategoryByName(String name) {
		Category category = new Category();
		
		try {
			ResultSet rs = super.dbm.select(
				"Category",
				new String[] {"id", "name", "description"},
				new String[] {"name", "=", name} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					category = new Category(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("description")	
					);
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		return category;
	}
	
	public ArrayList<Category> getCategoriesByProduct(Product product) {
		ArrayList<Category> categories = new ArrayList<Category>();
		
		try {
			ResultSet rs = super.dbm.select(
				"Product_Category",
				new String[] {"ProductId", "CategoryId"},
				new String[] {"ProductId", "=", String.valueOf(product.getId())} 
			);
			
			if(!rs.next()) {
			} else {
				do {
					categories.add(this.getCategoryById(rs.getInt("CategoryId")));
				} while(rs.next());
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		return categories;
	}
}
