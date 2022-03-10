package services;

public class ServiceProvider {
	private static ServiceProvider instance = null;
	
	private BookingService bookingService;
	private CategoryService categoryService;
	private CustomerService customerService;
	private EmployeeService employeeService;
	private ProductService productService;
	private ShoppingCartService shoppingCartService;
	
	public BookingService getBookingService() {
		return bookingService;
	}

	private void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	private void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	private void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	private void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	private void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	public static ServiceProvider getInstance() {
		if(ServiceProvider.instance == null) {
			ServiceProvider.instance = new ServiceProvider();
		}
		return ServiceProvider.instance;
	}

	private ServiceProvider() {
		this.setBookingService(new BookingService());
		this.setCategoryService(new CategoryService());
		this.setCustomerService(new CustomerService());
		this.setEmployeeService(new EmployeeService());
		this.setProductService(new ProductService());
		this.setShoppingCartService(new ShoppingCartService());
	}
}
