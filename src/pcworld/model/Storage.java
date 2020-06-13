package pcworld.model;

public class Storage extends Components {
	public int id;
	public String storage_type;
	public String capacity;
	public String formfactor;
	
	public Storage() {
		super();
	}
	public Storage(int id, String storage_type, String capacity, String formfactor) {
		super();
		this.id = id;
		this.storage_type = storage_type;
		this.capacity = capacity;
		this.formfactor = formfactor;
	}
	public Storage(int id, String brand, String model, double price, String image, String type, String storage_type, String capacity, String formfactor) {
		super(id, brand, model, price, image, type);
		this.storage_type = storage_type;
		this.capacity = capacity;
		this.formfactor = formfactor;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStorage_type() {
		return storage_type;
	}

	public void setStorage_type(String storage_type) {
		this.storage_type = storage_type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getForm() {
		return formfactor;
	}

	public void setForm(String formfactor) {
		this.formfactor = formfactor;
	}
}
