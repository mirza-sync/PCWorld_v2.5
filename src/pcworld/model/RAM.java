package pcworld.model;

public class RAM extends Components {
	public int id;
	public int capacity;
	public String ram_type;
	public int speed;
	
	public RAM() {
		super();
	}
	public RAM(int id, int capacity, String ram_type, int speed) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.ram_type = ram_type;
		this.speed = speed;
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
}
