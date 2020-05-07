package pcworld.model;

public class PSU extends Components {
	public int id;
	public int wattage;
	public String modularity;
	public String efficiency;
	public String color;
	
	public PSU() {
		super();
	}
	public PSU(int id, int wattage, String modularity, String efficiency, String color) {
		super();
		this.id = id;
		this.wattage = wattage;
		this.modularity = modularity;
		this.efficiency = efficiency;
		this.color = color;
	}
	public PSU(int id, String brand, String model, double price, String image, String type, int wattage, String modularity, String efficiency, String color) {
		super(id, brand, model, price, image, type);
		this.wattage = wattage;
		this.modularity = modularity;
		this.efficiency = efficiency;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWattage() {
		return wattage;
	}
	public void setWattage(int wattage) {
		this.wattage = wattage;
	}
	public String getModularity() {
		return modularity;
	}
	public void setModularity(String modularity) {
		this.modularity = modularity;
	}
	public String getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
