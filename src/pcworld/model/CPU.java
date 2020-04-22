package pcworld.model;

public class CPU extends Components {
	public Components comp;
	public int id;
	public String socket;
	public float base_clock;
	public float max_clock;
	public int num_core;
	public int thread;
	public int wattage;
	
	public CPU() {
		super();
	}

	public CPU(int id, String socket, float base_clock, float max_clock, int num_core, int thread, int wattage) {
		super();
		this.id = id;
		this.socket = socket;
		this.base_clock = base_clock;
		this.max_clock = max_clock;
		this.num_core = num_core;
		this.thread = thread;
		this.wattage = wattage;
	}
	
	public CPU(int id, String brand, String model, double price, String image, String type, String socket, float base_clock, float max_clock, int num_core, int thread, int wattage) {
		super(id, brand, model, price, image, type);
		this.socket = socket;
		this.base_clock = base_clock;
		this.max_clock = max_clock;
		this.num_core = num_core;
		this.thread = thread;
		this.wattage = wattage;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}
	
	public float getBase_clock() {
		return base_clock;
	}

	public void setBase_clock(float base_clock) {
		this.base_clock = base_clock;
	}

	public float getMax_clock() {
		return max_clock;
	}

	public void setMax_clock(float max_clock) {
		this.max_clock = max_clock;
	}

	public int getNum_core() {
		return num_core;
	}

	public void setNum_core(int num_core) {
		this.num_core = num_core;
	}
	
	public int getThread() {
		return thread;
	}

	public void setThread(int thread) {
		this.thread = thread;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}
}
