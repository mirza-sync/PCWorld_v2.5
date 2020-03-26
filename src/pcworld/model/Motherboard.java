package pcworld.model;

public class Motherboard extends Components {
	public int id;
	public String formfactor;
	public int length;
	public int width;
	public int height;
	public String socket;
	public String memory_type;
	public int memory_slot;
	public int max_memory;
	
	public Motherboard() {
		super();
	}
	public Motherboard(int id, String formfactor, int length, int width, int height, String socket, String memory_type, int memory_slot,
			int max_memory) {
		super();
		this.id = id;
		this.formfactor = formfactor;
		this.length = length;
		this.width = width;
		this.height = height;
		this.socket = socket;
		this.memory_type = memory_type;
		this.memory_slot = memory_slot;
		this.max_memory = max_memory;
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
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getMemory_type() {
		return memory_type;
	}
	public void setMemory_type(String memory_type) {
		this.memory_type = memory_type;
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
}
