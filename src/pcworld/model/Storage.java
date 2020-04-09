package pcworld.model;

public class Storage extends Components {
	public int id;
	public String storage_type;
	public int capacity;
	public String form;
	public int read_speed;
	public int write_speed;	
	
	public Storage() {
		super();
	}
	public Storage(int id, String storage_type, int capacity, String form, int read_speed,
			int write_speed) {
		super();
		this.id = id;
		this.storage_type = storage_type;
		this.capacity = capacity;
		this.form = form;
		this.read_speed = read_speed;
		this.write_speed = write_speed;
	}
	public Storage(int id, String brand, String model, double price, String image, String type, String storage_type, int capacity, String form, int read_speed,
			int write_speed) {
		super(id, brand, model, price, image, type);
		this.storage_type = storage_type;
		this.capacity = capacity;
		this.form = form;
		this.read_speed = read_speed;
		this.write_speed = write_speed;
	}

	public String getStorage_type() {
		return storage_type;
	}

	public void setStorage_type(String storage_type) {
		this.storage_type = storage_type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public int getRead_speed() {
		return read_speed;
	}

	public void setRead_speed(int read_speed) {
		this.read_speed = read_speed;
	}

	public int getWrite_speed() {
		return write_speed;
	}

	public void setWrite_speed(int write_speed) {
		this.write_speed = write_speed;
	}
}
