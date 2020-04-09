package pcworld.model;

import java.util.ArrayList;
import java.util.Random;

public class Components {
	CPU cpu;
	GPU gpu;
	Motherboard mobo;
	RAM ram;
	Storage storage;
	PSU psu;
	Casing casing;
	double totalPrice;
	
	ArrayList<CPU> cpus;
	ArrayList<GPU> gpus;
	ArrayList<Motherboard> mobos;
	ArrayList<RAM> rams;
	ArrayList<Storage> storages;
	ArrayList<PSU> psus;
	ArrayList<Casing> casings;
	
	Random rnd;
	
	public int id;
	public String brand;
	public String model;
	public double price;
	public String image;
	public String type;
	
	public Components() {
		super();
	}

	public Components(int id, String brand, String model, double price, String image, String type) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.image = image;
		this.type = type;
	}
	public Components(String brand, String model, double price, String image, String type) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.image = image;
		this.type = type;
	}
	
	public Components(ArrayList<CPU> cpus, ArrayList<GPU> gpus, ArrayList<Motherboard> mobos, ArrayList<RAM> rams,
			ArrayList<Storage> storages, ArrayList<PSU> psus, ArrayList<Casing> casings) {
		super();
		this.cpus = cpus;
		this.gpus = gpus;
		this.mobos = mobos;
		this.rams = rams;
		this.storages = storages;
		this.psus = psus;
		this.casings = casings;
	}
	
	public void getRandomComponents() {
		rnd = new Random();
		this.setCpu(cpus.get(rnd.nextInt(cpus.size())));
		this.setGpu(gpus.get(rnd.nextInt(gpus.size())));
		this.setMobo(mobos.get(rnd.nextInt(mobos.size())));
		this.setRam(rams.get(rnd.nextInt(rams.size())));
		this.setStorage(storages.get(rnd.nextInt(storages.size())));
		this.setPsu(psus.get(rnd.nextInt(psus.size())));
		this.setCasing(casings.get(rnd.nextInt(casings.size())));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	//======================================================================================
	public CPU getRandomCpu() {
		rnd = new Random();
		CPU rndCpu = cpus.get(rnd.nextInt(cpus.size()));
		return rndCpu;
	}
	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public GPU getRandomGpu() {
		rnd = new Random();
		GPU rndGpu = gpus.get(rnd.nextInt(gpus.size()));
		return rndGpu;
	}
	public GPU getGpu() {
		return gpu;
	}
	

	public void setGpu(GPU gpu) {
		this.gpu = gpu;
	}
	
	public Motherboard getRandomMobo() {
		rnd = new Random();
		Motherboard rndMobo = mobos.get(rnd.nextInt(mobos.size()));
		return rndMobo;
	}
	public Motherboard getMobo() {
		return mobo;
	}

	public void setMobo(Motherboard mobo) {
		this.mobo = mobo;
	}

	public RAM getRandomRam() {
		rnd = new Random();
		RAM rndRam = rams.get(rnd.nextInt(rams.size()));
		return rndRam;
	}
	public RAM getRam() {
		return ram;
	}

	public void setRam(RAM ram) {
		this.ram = ram;
	}

	public Storage getRandomStorage() {
		rnd = new Random();
		Storage rndStor = storages.get(rnd.nextInt(storages.size()));
		return rndStor;
	}
	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public PSU getRandomPsu() {
		rnd = new Random();
		PSU rndPsu = psus.get(rnd.nextInt(psus.size()));
		return rndPsu;
	}
	public PSU getPsu() {
		return psu;
	}

	public void setPsu(PSU psu) {
		this.psu = psu;
	}

	public Casing getRandomCasing() {
		rnd = new Random();
		Casing rndCase = casings.get(rnd.nextInt(casings.size()));
		return rndCase;
	}
	public Casing getCasing() {
		return casing;
	}

	public void setCasing(Casing casing) {
		this.casing = casing;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}