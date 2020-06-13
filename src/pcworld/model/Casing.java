package pcworld.model;

public class Casing extends Components {
	public int id;
	public String formfactor;
	public String color;
	
	public Casing() {
		super();
	}
	public Casing(int id, String formfactor, String color) {
		super();
		this.id = id;
		this.formfactor = formfactor;
		this.color = color;
	}
	public Casing(int id, String brand, String model, double price, String image, String type, String formfactor, String color) {
		super(id, brand, model, price, image, type);
		this.formfactor = formfactor;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getForm() {
		return formfactor;
	}
	public void setForm(String formfactor) {
		this.formfactor = formfactor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
