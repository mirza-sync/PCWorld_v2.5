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
	int id, clock, core, watt;
	String socket;

	//new CPU
	public void add(CPU cpu){
		
		id = cpu.getId();
		socket = cpu.getSocket();
		clock = cpu.getClock();
		core = cpu.getNum_core();
		watt = cpu.getWattage();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
           						
			ps=currentCon.prepareStatement("insert into cpu (id, socket, clock_speed, num_core, wattage) values (?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, socket);
			ps.setInt(3, clock);
			ps.setInt(4, core);
			ps.setInt(5, watt);
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
		clock = cpu.getClock();
		core = cpu.getNum_core();
		watt = cpu.getWattage();
		
		String q = "UPDATE cpu SET socket = '"+socket+"', clock_speed = '"+clock+"', num_core = '"+core+"', wattage = '"+watt+"' WHERE id = '"+id+"'";
		
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

				cpu.setId(rs.getInt("id"));
				cpu.setBrand(rs.getString("brand"));
				cpu.setModel(rs.getString("model"));
				cpu.setPrice(rs.getDouble("price"));
				cpu.setImage(rs.getString("image"));
				cpu.setType(rs.getString("type"));
            	cpu.setSocket(rs.getString("socket"));
            	cpu.setClock(rs.getInt("clock_speed"));
            	cpu.setNum_core(rs.getInt("num_core"));
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
            	cpu.setId(rs.getInt("id"));
            	cpu.setBrand(rs.getString("brand"));
            	cpu.setModel(rs.getString("model"));
            	cpu.setPrice(rs.getDouble("price"));
            	cpu.setImage(rs.getString("image"));
            	cpu.setType(rs.getString("type"));
            	cpu.setSocket(rs.getString("socket"));
            	cpu.setClock(rs.getInt("clock_speed"));
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