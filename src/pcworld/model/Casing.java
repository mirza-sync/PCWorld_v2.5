package pcworld.model;

public class Casing extends Components {
	public int id;
	public String form;
	public int length;
	public int width;
	public int height;
	public String color;
	
	public Casing() {
		super();
	}
	public Casing(int id, String form, int length, int width, int height, String color) {
		super();
		this.id = id;
		this.form = form;
		this.length = length;
		this.width = width;
		this.height = height;
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
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
