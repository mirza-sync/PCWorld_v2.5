package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.RAM;

public class RamDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	int id, capacity, speed;
	String ram_type;

	//new ram
	public void add(RAM ram){
		
		id = ram.getId();
		capacity = ram.getCapacity();
		ram_type = ram.getRam_type();
		speed = ram.getSpeed();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
           						
			ps=currentCon.prepareStatement("insert into ram (id, capacity, ram_type, speed) values (?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, capacity);
			ps.setString(3, ram_type);
			ps.setInt(4, speed);
			ps.executeUpdate();
			
			ps.close();
			
			System.out.println("ram is saved");
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
	
	//update ram
	public void update(RAM ram) {

		id = ram.getId();
		capacity = ram.getCapacity();
		ram_type = ram.getRam_type();
		speed = ram.getSpeed();
		
		String q = "UPDATE ram SET capacity = '"+capacity+"', ram_type = '"+ram_type+"', speed = '"+speed+"' WHERE id = '"+id+"'";
		
		System.out.println("Update query : "+q);
		
		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<RAM> getAllRam(){
		ArrayList<RAM> rams = new ArrayList<RAM>();
		String q = "select * from ram join components using (id)";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(q);
			
			//To check if resultSet gives no rows
			if (!rs.isBeforeFirst()) {
			    System.out.println("No data!"); 
			}
			
			while (rs.next()) {
				RAM ram = new RAM();

				ram.setId(rs.getInt("id"));
				ram.setBrand(rs.getString("brand"));
				ram.setModel(rs.getString("model"));
				ram.setPrice(rs.getDouble("price"));
				ram.setImage(rs.getString("image"));
				ram.setType(rs.getString("type"));
            	ram.setCapacity(rs.getInt("capacity"));
            	ram.setRam_type(rs.getString("ram_type"));
            	ram.setSpeed(rs.getInt("speed"));
            	
				rams.add(ram);
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
		return rams;
	}
	
	public RAM getRamById(int id) {
		RAM ram = new RAM();
		
		System.out.println("ram id : " + id);
		
		String q = "select * from ram JOIN components using (id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            System.out.println("ram!");
            
            if (rs.next()) {
            	ram.setId(rs.getInt("id"));
            	ram.setBrand(rs.getString("brand"));
            	ram.setModel(rs.getString("model"));
            	ram.setPrice(rs.getDouble("price"));
            	ram.setImage(rs.getString("image"));
            	ram.setType(rs.getString("type"));
            	ram.setCapacity(rs.getInt("capacity"));
            	ram.setRam_type(rs.getString("ram_type"));
            	ram.setSpeed(rs.getInt("speed"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return ram;
	}
	
	//delete ram
	public void deleteRam(int id) {
		String q = "delete from ram where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
}