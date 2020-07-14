package pcworld.model;

public class Casing extends Components {
	public int id;
	public String form;
	public String color;
	
	public Casing() {
		super();
	}
	public Casing(int id, String form, String color) {
		super();
		this.id = id;
		this.form = form;
		this.color = color;
	}
	public Casing(int id, String brand, String model, double price, String image, String type, String form, String color) {
		super(id, brand, model, price, image, type);
		this.form = form;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
