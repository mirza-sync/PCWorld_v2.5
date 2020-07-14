package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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
		mobos = mobodao.getAllMotherboard();
		rams = ramdao.getAllRam();
		storages = storagedao.getAllStorage();
		psus = psudao.getAllPsu();
		casings = casingdao.getAllCasing();
		
		Components componentsList = new Components(cpus, gpus, mobos, rams, storages, psus, casings);
		
		int population = 100;
		Chromosome[] pc = new Chromosome[population];	//Create empty array of 100 chromosome (population pool)
		int generation = 1000;	//Will loop for 1000 times
		
		Instant startTime = Instant.now();	//record time starts
		System.out.println("+++++++++++++++++++++++++++Starting GA+++++++++++++++++++++++++++++++++");
		for(int g = 0; g < generation; g++) {
			if(g==0) {
				for(int i = 0; i < population; i++) {					
					Chromosome dna = new Chromosome(componentsList,0,0);
					pc[i] = dna;
                	double fitness = FitnessFunction(dna,input);
                    dna.fitness = fitness;
                    System.out.println("P:"+(i+1)+" | Fitness: " + pc[i].fitness + " | Price : RM" + pc[i].totalPrice + " ["+pc[0].gene[0].getBrand()+" "+pc[i].gene[0].getModel()+"|"+pc[i].gene[1].getModel()+"|"+pc[i].gene[2].getModel()+"|"+pc[i].gene[3].getModel()+"|"+pc[i].gene[4].getModel()+"|"+pc[i].gene[5].getModel()+"|"+pc[i].gene[6].getModel()+"]");
				}
			} else {
				for (int i = population/2; i < population; i++) {
					Chromosome dna = new Chromosome(componentsList,0,0);
					pc[i] = dna;
                	double fitness = FitnessFunction(dna, input);
                	dna.fitness = fitness;
				}
			}
			Arrays.sort(pc); //Sort chromosome
			
			int start = pc.length/2;
            for (int i =0 ; i<pc.length/2; i++)
            {
            	Chromosome offspring = CrossOver(pc[i], pc[i + 1], comps, input);
            	Chromosome evolve = mutation(offspring, componentsList);
            	evolve.fitness = FitnessFunction(evolve, input);
                pc[start] = evolve;
                start++;
            }
			Arrays.sort(pc); //Sort chromosome
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
	     return child;
	}
	
	//mutation
	public Chromosome mutation(Chromosome crossed, Components componentsList) {
		if(0.01 > Math.random()) {
			Random rnd = new Random();
			int randomIndex = rnd.nextInt(crossed.getChromosomeLength());
			Chromosome randomDNA;
			Components randomGene, currentGene;
			currentGene = crossed.getGene(randomIndex);
			do {
				randomDNA = new Chromosome(componentsList,0,0);
				randomGene = randomDNA.getGene(randomIndex);
			} while(currentGene == randomGene);
			crossed.setGene(randomIndex, randomDNA.getGene(randomIndex));
		}
		return crossed;
	}
	
	public double FitnessFunction(Chromosome x, Input input) {
		
		double fitness = 0;
		fitness = 1/(1+(Math.abs(input.getBudget() - x.getTotalPrice())));
		
		//Usage
		String[] usages = input.getUsage();
		for(int i=0; i<usages.length; i++) {
			switch(usages[i]){
				case "video3d":
					if(((CPU)x.gene[0]).num_core >= 6){
						fitness++;
					}
					if(((CPU)x.gene[0]).multithread == 1){
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
				default:
					if(((CPU)x.gene[0]).base_clock <= 3){
						fitness++;
					}
					if(((GPU)x.gene[1]).core_clock <= 1300){
						fitness++;
					}
					break;
			}
		}
		//Style
		if(input.getStyle().equals("gamer")){
			if(((Casing)x.gene[6]).model.contains("RGB")){
				fitness++;
			}
			else {
				fitness--;
			}
			
			if(((RAM)x.gene[3]).model.contains("RGB")) {
				fitness++;
			}
			else {
				fitness--;
			}
		}else if(input.getStyle().equals("minimalist")){
			if(!((Casing)x.gene[6]).model.contains("RGB")){
				fitness++;
			}
			else {
				fitness--;
			}
			
			if(!((RAM)x.gene[3]).model.contains("RGB")) {
				fitness++;
			}
			else {
				fitness--;
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
		//Compatibility check
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
		if(((RAM)x.gene[3]).capacity <= ((Motherboard)x.gene[2]).max_memory){
			fitness++;
		}
		else{
			fitness--;
		}
		if(((PSU)x.gene[5]).wattage >= (((CPU)x.gene[0]).wattage + 300)){
			fitness++;
		}
		else{
			fitness--;
		}
		
		return fitness;
	}
}