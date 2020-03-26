package pcworld.model;

public class Chromosome {
	public Components[] pc;
//	public CPU cpu;
//	public GPU gpu;
//	public Motherboard mobo;
//	public RAM ram;
//	public Storage storage;
//	public PSU psu;
//	public Casing casing;
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
    
    public Chromosome(CPU cpu, GPU gpu, Motherboard mobo, RAM ram, Storage storage, PSU psu, Casing casing,
			double totalPrice, int fitness) {
    	
    	this.pc = new Components[7];
    	this.pc[0] = cpu;
    	this.pc[1] = gpu;
    	this.pc[2] = mobo;
    	this.pc[3] = ram;
    	this.pc[4] = storage;
    	this.pc[5] = psu;
    	this.pc[6] = casing;
    }
    
    public double getTotalPrice() {
    	totalPrice = pc[0].price + pc[1].price + pc[2].price + pc[3].price + pc[4].price + pc[5].price + pc[6].price;
    	return totalPrice;
    }
    
    public int getFitness() {
    	return fitness;
    }
}
