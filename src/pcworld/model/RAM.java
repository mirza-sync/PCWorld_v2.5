package pcworld.model;

public class RAM extends Components {
	public int id;
	public int capacity;
	public String ram_type;
	public int speed;
	public String module;
	public String color;
	
	public RAM() {
		super();
	}
	public RAM(int id, int capacity, String ram_type, int speed, String module, String color) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.ram_type = ram_type;
		this.speed = speed;
		this.module = module;
		this.color = color;
	}
	public RAM(int id, String brand, String model, double price, String image, String type, int capacity, String ram_type, int speed, String module, String color) {
		super(id, brand, model, price, image, type);
		this.id = id;
		this.capacity = capacity;
		this.ram_type = ram_type;
		this.speed = speed;
		this.module = module;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getRam_type() {
		return ram_type;
	}
	public void setRam_type(String ram_type) {
		this.ram_type = ram_type;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
