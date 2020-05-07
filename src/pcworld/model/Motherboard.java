package pcworld.model;

public class Motherboard extends Components {
	public int id;
	public String formfactor;
	public String socket;
	public int memory_slot;
	public int max_memory;
	public String color;
	
	public Motherboard() {
		super();
	}
	public Motherboard(int id, String formfactor, String socket, int memory_slot,
			int max_memory, String color) {
		super();
		this.id = id;
		this.formfactor = formfactor;
		this.socket = socket;
		this.memory_slot = memory_slot;
		this.max_memory = max_memory;
		this.color = color;
	}
	public Motherboard(int id, String brand, String model, double price, String image, String type, String formfactor, String socket, int memory_slot, int max_memory, String color) {
		super(id, brand, model, price, image, type);
		this.formfactor = formfactor;
		this.socket = socket;
		this.memory_slot = memory_slot;
		this.max_memory = max_memory;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormfactor() {
		return formfactor;
	}
	public void setFormfactor(String formfactor) {
		this.formfactor = formfactor;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public int getMemory_slot() {
		return memory_slot;
	}
	public void setMemory_slot(int memory_slot) {
		this.memory_slot = memory_slot;
	}
	public int getMax_memory() {
		return max_memory;
	}
	public void setMax_memory(int max_memory) {
		this.max_memory = max_memory;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
