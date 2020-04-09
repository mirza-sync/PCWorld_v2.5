package pcworld.model;

public class Chromosome {
	public Components[] gene;
	public CPU cpu;
	public GPU gpu;
	public Motherboard mobo;
	public RAM ram;
	public Storage storage;
	public PSU psu;
	public Casing casing;
	public double totalPrice;
	public int fitness;
    
//	public Chromosome(CPU cpu, GPU gpu, Motherboard mobo, RAM ram, Storage storage, PSU psu, Casing casing,
//			float totalPrice, int fitness) {
//		super();
//		this.cpu = cpu;
//		this.gpu = gpu;
//		this.mobo = mobo;
//		this.ram = ram;
//		this.storage = storage;
//		this.psu = psu;
//		this.casing = casing;
//		this.totalPrice = totalPrice;
//		this.fitness = fitness;
//	}
    
//    public Chromosome(CPU cpu, GPU gpu, Motherboard mobo, RAM ram, Storage storage, PSU psu, Casing casing,
//			double totalPrice, int fitness) {
//    	
//    	gene = new Components[7];
//    	
//    	gene[0] = cpu;
//    	gene[1] = gpu;
//    	gene[2] = mobo;
//    	gene[3] = ram;
//    	gene[4] = storage;
//    	gene[5] = psu;
//    	gene[6] = casing;
//    }
    
    public Chromosome(Components components, float totalPrice, int fitness) {
    	gene = new Components[7];
    	
    	gene[0] = components.getRandomCpu();
    	gene[1] = components.getRandomGpu();
    	gene[2] = components.getRandomMobo();
    	gene[3] = components.getRandomRam();
    	gene[4] = components.getRandomStorage();
    	gene[5] = components.getRandomPsu();
    	gene[6] = components.getRandomCasing();
    	
    	this.totalPrice = totalPrice;
    	this.fitness = fitness;
    }
    
    public Chromosome(int chromosomeLength) {
    	gene = new Components[chromosomeLength];
    }
    
    public int getChromosomeLength() {
    	return gene.length;
    }
    
    
    public Components getGene(int index) {
		return this.gene[index];
	}


	public void setGene(int index, Components gene) {
		this.gene[index] = gene;
	}


	public CPU getCpu() {
		return cpu;
	}


	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}


	public GPU getGpu() {
		return gpu;
	}


	public void setGpu(GPU gpu) {
		this.gpu = gpu;
	}


	public Motherboard getMobo() {
		return mobo;
	}


	public void setMobo(Motherboard mobo) {
		this.mobo = mobo;
	}


	public RAM getRam() {
		return ram;
	}


	public void setRam(RAM ram) {
		this.ram = ram;
	}


	public Storage getStorage() {
		return storage;
	}


	public void setStorage(Storage storage) {
		this.storage = storage;
	}


	public PSU getPsu() {
		return psu;
	}


	public void setPsu(PSU psu) {
		this.psu = psu;
	}


	public Casing getCasing() {
		return casing;
	}


	public void setCasing(Casing casing) {
		this.casing = casing;
	}


	public double getTotalPrice() {
    	totalPrice = gene[0].price + gene[1].price + gene[2].price + gene[3].price + gene[4].price + gene[5].price + gene[6].price;
    	return totalPrice;
    }
    
    public int getFitness() {
    	
    	return fitness;
    }
}
