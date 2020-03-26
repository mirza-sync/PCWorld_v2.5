package pcworld.model;

public class CPU extends Components {
	public int id;
	public String socket;
	public int clock;
	public int num_core;
	public int wattage;
	
	public CPU() {
		super();
	}

	public CPU(int id, String socket, int clock, int num_core, int wattage) {
		super();
		this.id = id;
		this.socket = socket;
		this.clock = clock;
		this.num_core = num_core;
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

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public int getNum_core() {
		return num_core;
	}

	public void setNum_core(int num_core) {
		this.num_core = num_core;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}
}
