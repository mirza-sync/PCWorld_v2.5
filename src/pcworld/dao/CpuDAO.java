
package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.CPU;

public class CpuDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	int id, core, thread, watt;
	float base_clock, max_clock;
	String socket, imageName;

	//new CPU
	public void add(CPU cpu){
		
		id = cpu.getId();
		socket = cpu.getSocket();
		base_clock = cpu.getBase_clock();
		max_clock = cpu.getMax_clock();
		core = cpu.getNum_core();
		thread = cpu.getThread();
		watt = cpu.getWattage();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
           						
			ps=currentCon.prepareStatement("insert into cpu (id, socket, base_clock, max_clock, num_core, thread, wattage) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, socket);
			ps.setFloat(3, base_clock);
			ps.setFloat(4, max_clock);
			ps.setInt(5, core);
			ps.setInt(6, thread);
			ps.setInt(7, watt);
			ps.executeUpdate();
			
			ps.close();
			
			System.out.println("CPU is saved");
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
	}
	
	//update CPU
	public void update(CPU cpu) {

		id = cpu.getId();
		socket = cpu.getSocket();
		base_clock = cpu.getBase_clock();
		max_clock = cpu.getMax_clock();
		core = cpu.getNum_core();
		thread = cpu.getThread();
		watt = cpu.getWattage();
		
		String q = "UPDATE cpu SET socket = '"+socket+"', base_clock = '"+base_clock+"', max_clock = '"+max_clock+"', num_core = '"+core+"', thread = '"+thread+"', wattage = '"+watt+"' WHERE id = '"+id+"'";
		
		System.out.println("Update query : "+q);
		
		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<CPU> getAllCpu(){
		ArrayList<CPU> cpus = new ArrayList<CPU>();
		String q = "select * from cpu join components using (id)";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(q);
			
			//To check if resultSet gives no rows
			if (!rs.isBeforeFirst()) {
			    System.out.println("No data!"); 
			}
			
			while (rs.next()) {
				CPU cpu = new CPU();
				
				if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}

				cpu.setId(rs.getInt("id"));
				cpu.setBrand(rs.getString("brand"));
				cpu.setModel(rs.getString("model"));
				cpu.setPrice(rs.getDouble("price"));
				cpu.setImage(imageName);
				cpu.setType(rs.getString("type"));
            	cpu.setSocket(rs.getString("socket"));
            	cpu.setBase_clock(rs.getFloat("base_clock"));
            	cpu.setMax_clock(rs.getFloat("max_clock"));
            	cpu.setNum_core(rs.getInt("num_core"));
            	cpu.setThread(rs.getInt("thread"));
            	cpu.setWattage(rs.getInt("wattage"));
            	
				cpus.add(cpu);
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
		return cpus;
	}
	
	public CPU getCpuById(int id) {
		CPU cpu = new CPU();
		
		System.out.println("CPU id : " + id);
		
		String q = "select * from cpu JOIN components using (id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            System.out.println("CPU!");
            
            if (rs.next()) {
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	cpu.setId(rs.getInt("id"));
            	cpu.setBrand(rs.getString("brand"));
            	cpu.setModel(rs.getString("model"));
            	cpu.setPrice(rs.getDouble("price"));
            	cpu.setImage(imageName);
            	cpu.setType(rs.getString("type"));
            	cpu.setSocket(rs.getString("socket"));
            	cpu.setBase_clock(rs.getFloat("base_clock"));
            	cpu.setMax_clock(rs.getFloat("max_clock"));
            	cpu.setNum_core(rs.getInt("num_core"));
            	cpu.setThread(rs.getInt("thread"));
            	cpu.setNum_core(rs.getInt("num_core"));
            	cpu.setWattage(rs.getInt("wattage"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return cpu;
	}
	
	//delete CPU
	public void deleteCpu(int id) {
		String q = "delete from cpu where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
}