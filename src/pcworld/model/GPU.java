package pcworld.model;

public class GPU extends Components {
	public int id;
	public String chipset;
	public int num_vram;
	public String color;
	public int core_clock;
	
	public GPU() {
		super();
	}
	
	public GPU(int id, String chipset, int num_vram, String color, int core_clock) {
		super();
		this.id = id;
		this.chipset = chipset;
		this.num_vram = num_vram;
		this.color = color;
		this.core_clock = core_clock;
	}
	
	public GPU(int id, String brand, String model, double price, String image, String type, String chipset, int num_vram, String color,
			int core_clock) {
		super(id, brand, model, price, image, type);
		this.chipset = chipset;
		this.num_vram = num_vram;
		this.color = color;
		this.core_clock = core_clock;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChipset() {
		return chipset;
	}

	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public int getNum_vram() {
		return num_vram;
	}

	public void setNum_vram(int num_vram) {
		this.num_vram = num_vram;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCore_clock() {
		return core_clock;
	}

	public void setCore_clock(int core_clock) {
		this.core_clock = core_clock;
	}
}
