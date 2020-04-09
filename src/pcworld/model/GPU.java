package pcworld.model;

public class GPU extends Components {
	public int id;
	public String chipset;
	public int num_vram;
	public String vram_type;
	public int length;
	public int width;
	public int height;
	public String color;
	public int core_clock;
	public int wattage;
	
	public GPU() {
		super();
	}
	
	public GPU(int id, String chipset, int num_vram, String vram_type, int length, int width, int height, String color,
			int core_clock, int wattage) {
		super();
		this.id = id;
		this.chipset = chipset;
		this.num_vram = num_vram;
		this.vram_type = vram_type;
		this.length = length;
		this.width = width;
		this.height = height;
		this.color = color;
		this.core_clock = core_clock;
		this.wattage = wattage;
	}
	
	public GPU(int id, String brand, String model, double price, String image, String type, String chipset, int num_vram, String vram_type, int length, int width, int height, String color,
			int core_clock, int wattage) {
		super(id, brand, model, price, image, type);
		this.chipset = chipset;
		this.num_vram = num_vram;
		this.vram_type = vram_type;
		this.length = length;
		this.width = width;
		this.height = height;
		this.color = color;
		this.core_clock = core_clock;
		this.wattage = wattage;
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

	public String getVram_type() {
		return vram_type;
	}

	public void setVram_type(String vram_type) {
		this.vram_type = vram_type;
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

	public int getCore_clock() {
		return core_clock;
	}

	public void setCore_clock(int core_clock) {
		this.core_clock = core_clock;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}
}
