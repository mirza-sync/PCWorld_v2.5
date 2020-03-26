package pcworld.model;

public class Cooler extends Components {
	public int id;
	public String cooler_type;
	public int length;
	public int width;
	public int height;
	public String color;
	
	public Cooler() {
		super();
	}
	public Cooler(int id, String cooler_type, int length, int width, int height, String color) {
		super();
		this.id = id;
		this.cooler_type = cooler_type;
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
	public String getCooler_type() {
		return cooler_type;
	}
	public void setCooler_type(String cooler_type) {
		this.cooler_type = cooler_type;
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
