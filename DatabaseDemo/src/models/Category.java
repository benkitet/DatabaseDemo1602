package models;

public class Category {
	
	private int id;
	private String name;
	private String description;

	public Category() {
		this("", "");
	}

	public Category(String name, String description ) {
		this(0, name, description);
	}
	
	public Category(int id, String name, String description ) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}

	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return this.name; }
	private void setName(String name) { this.name = name; }

	public String getDescription() {return  this.description; }
	private void setDescription(String description) { this.description = description; }

	public boolean equals(Category category) {
		return this.getId() == category.getId() &&
				this.getName().equals(category.getName()) &&
				this.getDescription().equals(category.getDescription());
	}

	@Override
	public String toString() {
		return "Category [getId()=" + this.getId() +
				", getName()=" + this.getName() +
				", getDescription()=" + this.getDescription() + "]";
	}
}
