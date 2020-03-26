package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	//Add new component
	public int add(Components comp) {
		int increment_id = 0;
		
		compId = comp.getId();
		compBrand = comp.getBrand();
		compModel = comp.getModel();
		compPrice = comp.getPrice();
		compImage = comp.getImage();
		compType = comp.getType();
		
		String maxQuery = "select MAX(id) from Components";

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
	public void updateComponent(Components comp) {
		
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

				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(rs.getString("image"));
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
	
	//------------------------------------viewAllRPH.jsp MIRZA 28/5/19---------------------------------------
	//For headmaster to check sent RPH
//	public List<RPHS> getAllSentRphByTeacher(int teacher_id) {
//		List<RPHS> rphs = new ArrayList<RPHS>();
//		subjectdao = new SubjectDAO();
//		String q = "select * from rphs where teacher_id ='"+teacher_id+"' AND rph_submitstatus='Sent'";
//		try {
//			currentCon = ConnectionManager.getConnection();
//			stmt = currentCon.createStatement();
//			rs = stmt.executeQuery(q);
//			
//			//To check if resultSet gives no rows
//			if (!rs.isBeforeFirst()) {
//			    System.out.println("No data!"); 
//			}
//			
//			while (rs.next()) {
//				RPHS rph = new RPHS();
//
//				rph.setRph_id(rs.getInt("rph_id"));
//				rph.setTeacher_id(rs.getInt("teacher_id"));
//				rph.setRph_date(rs.getString("rph_date"));
//				rph.setRph_type(rs.getString("rph_type"));
//				rph.setSubject_id(rs.getInt("subject_id"));
//				
//				System.out.println("Teacher ID : "+ rs.getInt("teacher_id") +", The rph id : "+rs.getInt("rph_id"));
//				
//				//To get details of the RPH's subject
//				Subjects subject = new Subjects();
//				subject = subjectdao.getSubjectById(rs.getInt("subject_id"));
//				rph.setSubject(subject);
//
//				rphs.add(rph);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (Exception e) {
//				}
//				rs = null;
//			}
//
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (Exception e) {
//				}
//				stmt = null;
//			}
//
//			if (currentCon != null) {
//				try {
//					currentCon.close();
//				} catch (Exception e) {
//				}
//
//				currentCon = null;
//			}
//		}
//
//		return rphs;
//	}
	
	
	public List<Components> getAllComponentByType(String type) {
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

				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(rs.getString("image"));
				

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
				comp.setId(rs.getInt("id"));
				comp.setBrand(rs.getString("brand"));
				comp.setModel(rs.getString("model"));
				comp.setPrice(rs.getDouble("price"));
				comp.setImage(rs.getString("image"));
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
	public List<Components> recommendPC(Input input){
		System.out.println("Entered the recommend method");
		List<Components> comps = new ArrayList<Components>();
		List<Components> compRecommend = new ArrayList<Components>();
		
		CpuDAO cpudao = new CpuDAO();
		ArrayList<CPU> cpus = new ArrayList<CPU>();
		GpuDAO gpudao = new GpuDAO();
		List<GPU> gpus = new ArrayList<GPU>();
		MotherboardDAO mobodao = new MotherboardDAO();
		List<Motherboard> mobos = new ArrayList<Motherboard>();
		RamDAO ramdao = new RamDAO();
		List<RAM> rams = new ArrayList<RAM>();
		StorageDAO storagedao = new StorageDAO();
		List<Storage> storages = new ArrayList<Storage>();
		PsuDAO psudao = new PsuDAO();
		List<PSU> psus = new ArrayList<PSU>();
		CasingDAO casingdao = new CasingDAO();
		List<Casing> casings = new ArrayList<Casing>();
		
		cpus = cpudao.getAllCpu();
		gpus = gpudao.getAllGpu();
		mobos = mobodao.getAllMobo();
		rams = ramdao.getAllRam();
		storages = storagedao.getAllStorage();
		psus = psudao.getAllPsu();
		casings = casingdao.getAllCasing();
		
//		try {
//			currentCon = ConnectionManager.getConnection();
//			ps=currentCon.prepareStatement("select * from components");
//			ResultSet rs = ps.executeQuery();
//			
//			//Get ALL Components
//			while (rs.next()) {
//				Components comp = new Components();
//
//				comp.setId(rs.getInt("id"));
//				comp.setBrand(rs.getString("brand"));
//				comp.setModel(rs.getString("model"));
//				comp.setPrice(rs.getDouble("price"));
//				comp.setImage(rs.getString("image"));
//
//				comps.add(comp);
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
		
		int population = 100;
		Chromosome[] pc = new Chromosome[population];
		Random rnd = new Random();
		int generation = 1000;
		
		System.out.println("Starting GA...");
		for(int g = 0; g < generation; g++) {
			System.out.println("Generation : "+g);
			
			if(g==0) {
				for(int i = 0; i < population; i++) {
					
					RAM temp4 = (RAM)rams.get(rnd.nextInt(rams.size()));
					System.out.println("===================================================");
					System.out.println("RAM id : "+temp4.id);
					System.out.println("RAM id : "+temp4.getPrice());
					System.out.println("RAM speed : "+temp4.speed);
					System.out.println("===================================================");
					
//					CPU temp1 = (CPU)cpus.get(rnd.nextInt(cpus.size()));
//					GPU temp2 = (GPU)gpus.get(rnd.nextInt(gpus.size()));
//					Motherboard temp3 = (Motherboard)mobos.get(rnd.nextInt(mobos.size()));
					CPU temp1 = (CPU)cpus.get(rnd.nextInt(10));
					GPU temp2 = (GPU)gpus.get(rnd.nextInt(10));
					Motherboard temp3 = (Motherboard)mobos.get(rnd.nextInt(10));
//					RAM temp4 = (RAM)rams.get(rnd.nextInt(rams.size()));
					Storage temp5 = (Storage)storages.get(rnd.nextInt(storages.size()));
//					PSU temp6 = (PSU)psus.get(rnd.nextInt(psus.size()));
//					Casing temp7 = (Casing)casings.get(rnd.nextInt(casings.size()));
					PSU temp6 = (PSU)psus.get(rnd.nextInt(10));
					Casing temp7 = (Casing)casings.get(rnd.nextInt(22));
					
					
					
					Chromosome genes = new Chromosome(temp1, temp2, temp3, temp4, temp5, temp6, temp7, 0, 0);
					pc[i] = genes;
                	int fitness = FitnessFunction(genes,input);
                    genes.fitness = fitness;
                    System.out.print("p"+i+":"+pc[i].fitness+"; ");
				}
			} else {
				for(int i = 0; i < population; i++) {
					
				}
			}
		}
		
		return compRecommend;
	}
	
	public int FitnessFunction(Chromosome x, Input input) {
		int fitness = 0;
		
		//Budget
		if(x.getTotalPrice() <= input.getBudget()){
			fitness+=5;
		}
		else{
			fitness-=5;
		}

		//Usage
		switch(input.getUsage()){
			case "video3d":
				if(x.cpu.num_core >= 6){
					fitness++;
				}
				if(x.gpu.num_vram >= 4){
					fitness++;
				}
			case "gaming":
				if(x.cpu.clock >= 3){
					fitness++;
				}
				if(x.ram.capacity >= 8){
					fitness++;
				}
				if(x.gpu.core_clock >= 5000){
					fitness++;
				}
				break;
			default:		//office
				if(x.cpu.clock <= 3){
					fitness++;
				}
				break;
		}

		//Size
		if(input.getSize().equals("big")){
			if(x.casing.form.contains("ATX") || x.casing.form.contains("microATX")){
				fitness++;
			}
		}else if(input.getSize().equals("small")){
			if(x.casing.form.contains("miniATX")){
				fitness++;
			}
		}else{
			fitness = fitness;		//Dont care
		}

		//Style
		if(input.getSize().equals("gamer")){
			if(x.casing.model.contains("RGB")){
				fitness++;
			}
		}else if(input.getSize().equals("minimalist")){
			if(x.casing.model.contains("RGB")){
				fitness++;
			}
		}else{
			fitness = fitness;		//Dont care
		}
		
		//Color
//		if(black){
//			
//		}else if(white){
//			
//		}else{
//			fitness = fitness;
//		}

		//Compatiibility check:
		//ATX case can support ATX, mATX and miniITX
		if(x.mobo.formfactor.equals(x.casing.form)){
			fitness++;
		}else{
			fitness--;
		}
		if(x.mobo.socket.equals(x.cpu.socket)){
			fitness++;
		}else{
			fitness--;
		}
		if(x.mobo.memory_type.equals(x.ram.ram_type)){
			fitness++;
		}else{
			fitness--;
		}
		if(x.mobo.max_memory <= x.ram.capacity){
			fitness++;
		}else{
			fitness--;
		}
		if(x.psu.wattage >= (x.cpu.wattage + x.gpu.wattage)){
			fitness++;
		}else{
			fitness--;
		}
		
		return fitness;
	}
}











