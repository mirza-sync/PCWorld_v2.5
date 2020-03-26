package pcworld.model;

public class Input {
	private int budget;
	private String usage;
	private String size;
	private String style;
	//private int speciality;
	
	public Input() {
		super();
	}
	public Input(int budget, String usage, String size, String style) {
		super();
		this.budget = budget;
		this.usage = usage;
		this.size = size;
		this.style = style;
	}
	
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
