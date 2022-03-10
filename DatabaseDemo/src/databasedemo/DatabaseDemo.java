package databasedemo;
import util.Configuration;

public class DatabaseDemo {
	public static void main(String[] args) {
		
		Configuration config = Configuration.getInstance();
		System.out.println(config);
		
		
//		ServiceFactory sf = ServiceFactory.getInstance();
//		
//		EmployeeService es = sf.getEmployeeService();
//		ProductService ps = sf.getProductService();
//		CategoryService cs = sf.getCategoryService();
//		ShoppingCartService scs = sf.getShoppingCartService();
//		
//		System.out.println("----------- EMPLOYEES -----------");
//		ArrayList<Employee> employees = es.getEmployees();
//		
//		for(Employee e : employees) {
//			System.out.println(e);
//		}
//		
////		cs.addCategory(new Category("Elektronik", "Beschreibung"));
//		System.out.println("----------- CATEGORIES -----------");
//		ArrayList<Category> categories = cs.getCategories();
//		for(Category category : categories) {
//			System.out.println(category);
//			ArrayList<Product> products = ps.getProducts(category);
//			for(Product product : products) {
//				System.out.println("\t" + product);
//			}
//		}
//		
////		Product p = ps.addProduct(new Product("0000021", "TV", 399.95, "pictureblob", "Super TV für das Heimkino"));
////		Product tv = ps.getProductByName("TV");
////		Category electronics = cs.getCategoryByName("Elektronik");
////		ps.addCategory(tv, electronics);
//		System.out.println("----------- PRODUCTS DETAILS -----------");
//		ArrayList<Product> products = ps.getProducts();
//		for(Product product : products) {
//			System.out.println(product);
//			for(Category category : product.getCategories()) {
//				System.out.println("\t" + category);
//			}
//		}
//		
//		
////		scs.addShoppingCart(new ShoppingCart());
////		ShoppingCart wishlist = scs.getShoppingCartById(2);
////		System.out.println(wishlist);
////		scs.addProduct(wishlist, tv, 7);
//		System.out.println("----------- SHOPPINGCARTS -----------");
//		ArrayList<ShoppingCart> shoppingcarts = scs.getShoppingCarts();
//		for(ShoppingCart shoppingcart : shoppingcarts) {
//			System.out.println(shoppingcart);
//			for(Entry<Product, Integer> entry : shoppingcart.getProducts().entrySet()) {
//				System.out.println("\t" + entry.getKey() + ", Anzahl: " + entry.getValue());
//			}
//		}
		
	}	
}
