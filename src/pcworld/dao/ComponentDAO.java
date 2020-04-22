package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import pcworld.connection.ConnectionManager;
import pcworld.model.CPU;
import pcworld.model.Casing;
import pcworld.model.Chromosome;
import pcworld.model.Components;
import pcworld.model.GPU;
import pcworld.model.Input;
import pcworld.model.Motherboard;
import pcworld.model.PSU;
import pcworld.model.RAM;
import pcworld.model.Storage;

public class ComponentDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	static String compBrand, compModel, compImage, compType;
	static int    compId;
	static double compPrice;
	String imageName;

	//Add new component
	public int add(Components comp) {
		int increment_id = 0;
		
		compId = comp.getId();
		compBrand = comp.getBrand();
		compModel = comp.getModel();
		compPrice = comp.getPrice();
		compImage = comp.getImage();
		compType = comp.getType();
		
		String maxQuery = "select MAX(id) from components";

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into Components (brand, model, price, image, type) values (?,?,?,?,?)");
			ps.setString(1, compBrand);
			ps.setString(2, compModel);
			ps.setDouble(3, compPrice);
			ps.setString(4, compImage);
			ps.setString(5, compType);
			ps.executeUpdate();	//Insert to database
			
			ps = currentCon.prepareStatement(maxQuery);	//Get latest ID
			rs = ps.executeQuery();
			if(rs.next()) {
				increment_id = rs.getInt("MAX(id)");
			}

			ps.close();

			System.out.println("Your Component is saved ");
		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} 
				catch (Exception e) {}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				}
				catch (Exception e) {}
				currentCon = null;
			}
		}

		return increment_id;
	}

	//update
	public void update(Components comp) {
		
		compId = comp.getId();
		compBrand = comp.getBrand();
		compModel = comp.getModel();
		compPrice = comp.getPrice();
		compImage = comp.getImage();
		compType = comp.getType();
		
		//You cannot update Type
		String updateQuery = "UPDATE components SET brand= '" + compBrand + "', model='" + compModel +"', price='"+ compPrice +"', image='"+ compImage +"' WHERE id= '" + compId + "'";
		
		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(updateQuery);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} 
				catch (Exception e) {}
				stmt = null;
			}
			
			if (currentCon != null) {
				try {
					currentCon.close();
				}
				catch (Exception e) {}
				currentCon = null;
			}
		}
	}

	public List<Components> getAllComponent() {
		List<Components> comps = new ArrayList<Components>();
		System.out.println("Retreiving all components...");
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery("SELECT * FROM components");
			
			while (rs.next()) {
				Components comp = new Components();
				
				if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}

				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(imageName);
				comp.setType(rs.getString("type"));

				comps.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		
		System.out.println(comps.get(1));

		return comps;
	}
	
	
	public List<Components> getComponentsByType(String type) {
		List<Components> comps = new ArrayList<Components>();
		String q = "select * from components where type ='"+type+"'";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(q);
			
			//To check if resultSet gives no rows
			if (!rs.isBeforeFirst()) {
			    System.out.println("No data!"); 
			}
			
			while (rs.next()) {
				Components comp = new Components();
				
				if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}

				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(imageName);
				comp.setType(rs.getString("type"));
				

				comps.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return comps;
	}

	public Components getComponentById(int id) {
		Components comp = new Components();
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("select * from components where id=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Enter component DAO");

			if (rs.next()) {
				if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
				
				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(imageName);
				comp.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return comp;
	}
	
	public void deleteComponent(int id) {
		String searchQuery = "delete from components where id=" + id;
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
	}
	
	//Genetic Algorithm - Recommend PC
	public Components recommendPC(Input input){
		System.out.println("Entered the recommend method");
		List<Components> comps = new ArrayList<Components>();
		
		CpuDAO cpudao = new CpuDAO();
		ArrayList<CPU> cpus = new ArrayList<CPU>();
		GpuDAO gpudao = new GpuDAO();
		ArrayList<GPU> gpus = new ArrayList<GPU>();
		MotherboardDAO mobodao = new MotherboardDAO();
		ArrayList<Motherboard> mobos = new ArrayList<Motherboard>();
		RamDAO ramdao = new RamDAO();
		ArrayList<RAM> rams = new ArrayList<RAM>();
		StorageDAO storagedao = new StorageDAO();
		ArrayList<Storage> storages = new ArrayList<Storage>();
		PsuDAO psudao = new PsuDAO();
		ArrayList<PSU> psus = new ArrayList<PSU>();
		CasingDAO casingdao = new CasingDAO();
		ArrayList<Casing> casings = new ArrayList<Casing>();
		
		cpus = cpudao.getAllCpu();
		gpus = gpudao.getAllGpu();
		mobos = mobodao.getAllMobo();
		rams = ramdao.getAllRam();
		storages = storagedao.getAllStorage();
		psus = psudao.getAllPsu();
		casings = casingdao.getAllCasing();
		
		Components componentsList = new Components(cpus, gpus, mobos, rams, storages, psus, casings);
		
		int population = 100;
		Chromosome[] pc = new Chromosome[population];	//Create empty array of 100 chromosome (population pool)
		int generation = 1000;	//Will loop for 1000 times
		
		Instant startTime = Instant.now();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Starting GA...");
		for(int g = 0; g < generation; g++) {
			//System.out.println("Generation : "+ g);
			
			if(g==0) {
				for(int i = 0; i < population; i++) {					
					Chromosome dna = new Chromosome(componentsList,0,0);
					pc[i] = dna;
                	double fitness = FitnessFunction(dna,input);
                    dna.fitness = fitness;
                    
                    System.out.println("P:"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price : RM" + pc[i].totalPrice + " ["+pc[0].gene[0].getBrand()+" "+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");
				}
			} else {
				//for(int i = 0; i < population; i++)
				for (int i = population/2; i < population; i++) {
					Chromosome dna = new Chromosome(componentsList,0,0);
					pc[i] = dna;
                	double fitness = FitnessFunction(dna, input);
                	dna.fitness = fitness;
                	
                	//System.out.println("P:"+(i+1)+" ["+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]"+" Fitness: " + pc[i].fitness + " #Price : RM" + pc[i].totalPrice);
				}
			}
			
			//System.out.println("End population");
			//System.out.println("=====================================================================");
			
			Arrays.sort(pc);
			
//			Chromosome temp1[] =new Chromosome[1];
//			for(int i=1; i<pc.length; i++) {
//				if(pc[i-1].fitness<pc[i].fitness)
//				{
//					temp1[0]=pc[i-1];
//					pc[i-1]=pc[i];
//					pc[i]=temp1[0];
//				}
//				System.out.println("P:"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price : RM" + pc[i].totalPrice + " ["+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");
//			}
			
			int start = pc.length/2;
            for (int i =0 ; i<pc.length/2; i++)
            {
            	Chromosome offspring = CrossOver(pc[i], pc[i + 1], comps, input);
            	Chromosome evolve = mutation(offspring, componentsList);
            	evolve.fitness = FitnessFunction(evolve, input);
                pc[start] = evolve;
                start++;
            }
			
			Arrays.sort(pc);
//            Chromosome test2[] =new Chromosome[1];
//			for(int i=0;i<pc.length;i++)
//			{
//				for(int t =1;t<pc.length-i;t++) {
//					if(pc[t-1].fitness<pc[t].fitness)
//					{
//						test2[0]=pc[t-1];
//						pc[t-1]=pc[t];
//						pc[t]=test2[0];
//					}
//				}
//				
//				if(g==myG1 || g==myG2) {
//					System.out.println("P"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price: RM" + pc[i].totalPrice + " ["+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");
//					System.exit(0);
//				}
//				//System.out.println("P:"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price : RM" + pc[i].totalPrice + " ["+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");				
//			}
			int myG1 = 10;
			int myG2 = 500;
			if(g==myG1 || g==myG2) {
				System.out.println("Generation:" + myG1 + myG2);
				for(int i=0; i<pc.length; i++) {
					System.out.println("P:"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price : RM" + pc[i].totalPrice + " ["+pc[0].gene[0].getBrand()+" "+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");
				}
			}
		}
		Instant finish = Instant.now();
		double timeElapsed = Duration.between(startTime, finish).toMillis();
		
		System.out.println("==========End "+generation+" generation : [Time taken: " + String.format("%.2f",timeElapsed) + " milliseconds]==========");
		System.out.println("Budget : "+input.getBudget());
		for(String use : input.getUsage()) {
			System.out.println("Usage : "+ use);
		}
		System.out.println("Style : "+input.getStyle());
		System.out.println("Color : "+input.getColor());
		System.out.println("Fittest : " +"["+pc[0].gene[0].getBrand()+" "+pc[0].gene[0].getModel()+"|"+pc[0].gene[1].getModel()+"|"+pc[0].gene[2].getModel()+"|"+pc[0].gene[3].getModel()+"|"+pc[0].gene[4].getModel()+"|"+pc[0].gene[5].getModel()+"|"+pc[0].gene[6].getModel()+"]"+" Fitness: " + pc[0].fitness + " #Price : RM" + pc[0].totalPrice);
		
		Components finalPC = new Components();
		
//		finalPC.setCpu(pc[0].getCpu());
//		finalPC.setGpu(pc[0].getGpu());
//		finalPC.setMobo(pc[0].getMobo());
//		finalPC.setRam(pc[0].getRam());
//		finalPC.setStorage(pc[0].getStorage());
//		finalPC.setPsu(pc[0].getPsu());
//		finalPC.setCasing(pc[0].getCasing());
//		finalPC.setTotalPrice(pc[0].totalPrice);
		
		finalPC.setCpu((CPU) pc[0].gene[0]);
		finalPC.setGpu((GPU) pc[0].gene[1]);
		finalPC.setMobo((Motherboard) pc[0].gene[2]);
		finalPC.setRam((RAM) pc[0].gene[3]);
		finalPC.setStorage((Storage) pc[0].gene[4]);
		finalPC.setPsu((PSU) pc[0].gene[5]);
		finalPC.setCasing((Casing) pc[0].gene[6]);
		finalPC.setTotalPrice(pc[0].totalPrice);
				
		return finalPC;
	}
	
	public Chromosome CrossOver(Chromosome x, Chromosome y, List<Components> comps,Input input) {
		//Create new child
		Chromosome child = new Chromosome(x.getChromosomeLength());
		 
		 for(int geneIndex=0; geneIndex < x.getChromosomeLength(); geneIndex++) {
			 //Probability of crossover is 50%
			 //The cross over point is random
//			 if(0.5 > Math.random()) {
//				 child.setGene(geneIndex, x.getGene(geneIndex));
//			 } else {
//				 child.setGene(geneIndex, y.getGene(geneIndex));
//			 }
			 if(0.8 > Math.random()) {
				 if((geneIndex%2)!=0) {		//Swap Y at index 1,3,5,7
					 child.setGene(geneIndex, y.getGene(geneIndex));
				 }
				 else {
					 child.setGene(geneIndex, x.getGene(geneIndex));
				 }
			 }
			 else {
				 child.setGene(geneIndex, x.getGene(geneIndex));
			 }
		 }
//		 System.out.println("Crossover done!");
//		 System.out.println("X | Fitness: " + x.fitness + " | Price : RM" + x.totalPrice + " ["+x.gene[0].getModel()+"|"+x.gene[1].getModel()+"|"+x.gene[2].getModel()+"|"+x.gene[3].getModel()+"|"+x.gene[4].getModel()+"|"+x.gene[5].getModel()+"|"+x.gene[6].getModel()+"]");
//		 System.out.println("Y | Fitness: " + y.fitness + " | Price : RM" + y.totalPrice + " ["+y.gene[0].getModel()+"|"+y.gene[1].getModel()+"|"+y.gene[2].getModel()+"|"+y.gene[3].getModel()+"|"+y.gene[4].getModel()+"|"+y.gene[5].getModel()+"|"+y.gene[6].getModel()+"]");
//		 System.out.println("Child | Fitness: " + child.fitness + " | Price : RM" + child.getTotalPrice() + " ["+child.gene[0].getModel()+"|"+child.gene[1].getModel()+"|"+child.gene[2].getModel()+"|"+child.gene[3].getModel()+"|"+child.gene[4].getModel()+"|"+child.gene[5].getModel()+"|"+child.gene[6].getModel()+"]");
		 
	     return child;
	}
	
	//mutation
	public Chromosome mutation(Chromosome crossed, Components componentsList) {
		//Create a new chromosome with random data
//		Chromosome randomDNA = new Chromosome(componentsList,0,0);
//		for (int geneIndex = 0; geneIndex < randomDNA.getChromosomeLength(); geneIndex++) {
//			//Probability of mutation is 1%
//			if (0.01 > Math.random()) {
//				// Swap the randomDNA gene to the crossed offspring
//				crossed.setGene(geneIndex, randomDNA.getGene(geneIndex));
//			}
//		}
		if(0.01 > Math.random()) {
			Random rnd = new Random();
			int randomIndex = rnd.nextInt(crossed.getChromosomeLength());
			Chromosome randomDNA;
			Components randomGene, currentGene;
			currentGene = crossed.getGene(randomIndex);
			do {
				randomDNA = new Chromosome(componentsList,0,0);
				randomGene = randomDNA.getGene(randomIndex);
				//System.out.println("Current Gene: " + currentGene.getId() + ", Random Gene: " + randomGene.getId());
			} while(currentGene == randomGene);
			crossed.setGene(randomIndex, randomDNA.getGene(randomIndex));
//			System.out.println("Random Index: "+randomIndex);
//			System.out.println("RandomDNA | Fitness: " + randomDNA.fitness + " | Price : RM" + randomDNA.getTotalPrice() + " ["+randomDNA.gene[0].getModel()+"|"+randomDNA.gene[1].getModel()+"|"+randomDNA.gene[2].getModel()+"|"+randomDNA.gene[3].getModel()+"|"+randomDNA.gene[4].getModel()+"|"+randomDNA.gene[5].getModel()+"|"+randomDNA.gene[6].getModel()+"]");
//			System.out.println("Mutated | Fitness: " + crossed.fitness + " | Price : RM" + crossed.getTotalPrice() + " ["+crossed.gene[0].getModel()+"|"+crossed.gene[1].getModel()+"|"+crossed.gene[2].getModel()+"|"+crossed.gene[3].getModel()+"|"+crossed.gene[4].getModel()+"|"+crossed.gene[5].getModel()+"|"+crossed.gene[6].getModel()+"]");
		}
		return crossed;
	}
	
	public double FitnessFunction(Chromosome x, Input input) {
		//int fitness = 0;
		double fitness = 0;
		
		//Budget
		
		//ALTERNATIVE
		fitness = 1/(1+(Math.abs(input.getBudget() - x.getTotalPrice())));
		
		//System.out.println(String.format("%.5f",1/(1+(Math.abs(input.getBudget() - x.getTotalPrice())))));
//		if(x.getTotalPrice() <= input.getBudget()){
//			fitness++;
//		}
//		else{
//			fitness--;
//		}
		
		String[] usages = input.getUsage();

		//Usage
		for(int i=0; i<usages.length; i++) {
			switch(usages[i]){
				case "video3d":
					if(((CPU)x.gene[0]).num_core >= 6){
						fitness++;
					}
					if(((CPU)x.gene[0]).thread >= 6){
						fitness++;
					}
					if(((GPU)x.gene[1]).num_vram >= 4){
						fitness++;
					}
				case "gaming":
					if(((CPU)x.gene[0]).base_clock >= 3.5){
						fitness++;
					}
					if(((RAM)x.gene[3]).capacity >= 8){
						fitness++;
					}
					if(((GPU)x.gene[1]).core_clock >= 1300){
						fitness++;
					} else {
						fitness--;
					}
					break;
				default:		//office
					System.out.println("office");
					if(((CPU)x.gene[0]).base_clock <= 3){
						fitness++;
					}
					if(((GPU)x.gene[1]).core_clock <= 1300){
						fitness++;
					}
					break;
			}
		}

		//Size
//		if(input.getSize().equals("big")){
//			if(((Casing)x.gene[6]).form.contains("ATX") || ((Casing)x.gene[6]).form.contains("microATX")){
//				fitness++;
//			}
//		}else if(input.getSize().equals("small")){
//			if(((Casing)x.gene[6]).form.contains("miniATX")){
//				fitness++;
//			}
//		}else{
//			fitness = fitness;		//Dont care
//		}

		//Style
		if(input.getStyle().equals("gamer")){
			if(((Casing)x.gene[6]).model.contains("RGB")){
				fitness++;
			}
		}else if(input.getStyle().equals("minimalist")){
			if(!((Casing)x.gene[6]).model.contains("RGB")){
				fitness++;
			}
		}
		
		//Color
		if(input.getColor().contains(((GPU)x.gene[1]).color)){
			fitness++;
		}
		if(input.getColor().contains(((Motherboard)x.gene[2]).color)){
			fitness++;
		}
		if(input.getColor().contains(((RAM)x.gene[3]).color)){
			fitness++;
		}
		if(input.getColor().contains(((Casing)x.gene[6]).color)){
			fitness++;
		}

		//Compatibility check:
		//ATX case can support ATX, mATX and miniITX
		if(((Motherboard)x.gene[2]).formfactor.equals(((Casing)x.gene[6]).form)){
			fitness++;
		}
		else{
			fitness--;
		}
		if(((Motherboard)x.gene[2]).socket.equals(((CPU)x.gene[0]).socket)){
			fitness++;
		}
		else{
			fitness--;
		}
		if(((Motherboard)x.gene[2]).memory_type.equals(((RAM)x.gene[3]).ram_type)){
			fitness++;
		}
		else{
			fitness--;
		}
		if(((Motherboard)x.gene[2]).max_memory <= ((RAM)x.gene[3]).capacity){
			fitness++;
		}
		else{
			fitness--;
		}
		if(((PSU)x.gene[5]).wattage >= (((CPU)x.gene[0]).wattage + ((GPU)x.gene[1]).wattage)){
			fitness++;
		}
		else{
			fitness--;
		}
		
		return fitness;
	}
	
	public Components jsGa(Input input){
		System.out.println("Entered the javascript method");
		List<Components> comps = new ArrayList<Components>();
		
		CpuDAO cpudao = new CpuDAO();
		ArrayList<CPU> cpus = new ArrayList<CPU>();
		GpuDAO gpudao = new GpuDAO();
		ArrayList<GPU> gpus = new ArrayList<GPU>();
		MotherboardDAO mobodao = new MotherboardDAO();
		ArrayList<Motherboard> mobos = new ArrayList<Motherboard>();
		RamDAO ramdao = new RamDAO();
		ArrayList<RAM> rams = new ArrayList<RAM>();
		StorageDAO storagedao = new StorageDAO();
		ArrayList<Storage> storages = new ArrayList<Storage>();
		PsuDAO psudao = new PsuDAO();
		ArrayList<PSU> psus = new ArrayList<PSU>();
		CasingDAO casingdao = new CasingDAO();
		ArrayList<Casing> casings = new ArrayList<Casing>();
		
		cpus = cpudao.getAllCpu();
		gpus = gpudao.getAllGpu();
		mobos = mobodao.getAllMobo();
		rams = ramdao.getAllRam();
		storages = storagedao.getAllStorage();
		psus = psudao.getAllPsu();
		casings = casingdao.getAllCasing();
		
		Components componentsList = new Components(cpus, gpus, mobos, rams, storages, psus, casings);
		
		return componentsList;
	}
}