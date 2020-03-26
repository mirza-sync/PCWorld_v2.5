package pcworld.model;

public class Components {
	public int id;
	public String brand;
	public String model;
	public double price;
	public String image;
	public String type;
	
	public Components() {
		super();
	}

	public Components(int id, String brand, String model, double price, String image, String type) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.image = image;
		this.type = type;
	}
	public Components(String brand, String model, double price, String image, String type) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.image = image;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}