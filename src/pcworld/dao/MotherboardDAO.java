package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.Motherboard;

public class MotherboardDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String event_description;
	int id, mem_slot, max_mem;
	String form, socket, color, imageName;

	//new mobo
	public void add(Motherboard mobo){
		
		id = mobo.getId();
		form = mobo.getFormfactor();
		socket = mobo.getSocket();
		mem_slot = mobo.getMemory_slot();
		max_mem = mobo.getMax_memory();
		color = mobo.getColor();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into motherboard (id, formfactor, socket, memory_slot, max_memory, color) values (?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, form);
			ps.setString(3, socket);
			ps.setInt(4, mem_slot);
			ps.setInt(5, max_mem);
			ps.setString(6, color);
			ps.executeUpdate();
			
			ps.close();
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
	
	//update
	public void update(Motherboard mobo) {

		id = mobo.getId();
		form = mobo.getFormfactor();
		socket = mobo.getSocket();
		mem_slot = mobo.getMemory_slot();
		max_mem = mobo.getMax_memory();
		color = mobo.getColor();
		
		String q = "UPDATE motherboard SET formfactor='"+form+"', socket='"+socket+"', memory_slot='"+mem_slot+"', max_memory='"+max_mem+"', color='"+color+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Motherboard> getAllMotherboard() {
		ArrayList<Motherboard> mobos = new ArrayList<Motherboard>();
		
		String q = "select * from motherboard join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	Motherboard mobo = new Motherboard();
            	
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	mobo.setId(rs.getInt("id"));
				mobo.setBrand(rs.getString("brand"));
				mobo.setModel(rs.getString("model"));
				mobo.setPrice(rs.getDouble("price"));
				mobo.setImage(imageName);
				mobo.setType(rs.getString("type"));
				mobo.setFormfactor(rs.getString("formfactor"));
            	mobo.setSocket(rs.getString("socket"));
            	mobo.setMemory_slot(rs.getInt("memory_slot"));
            	mobo.setMax_memory(rs.getInt("max_memory"));
            	mobo.setColor(rs.getString("color"));
            	
            	mobos.add(mobo);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return mobos;
	}
	
	public Motherboard getMotherboardById(int id) {
		Motherboard mobo = new Motherboard();
		
		String q = "select * from motherboard join components using(id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            if (rs.next()) {
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	mobo.setId(rs.getInt("id"));
				mobo.setBrand(rs.getString("brand"));
				mobo.setModel(rs.getString("model"));
				mobo.setPrice(rs.getDouble("price"));
				mobo.setImage(imageName);
				mobo.setType(rs.getString("type"));
				mobo.setFormfactor(rs.getString("formfactor"));
            	mobo.setSocket(rs.getString("socket"));
            	mobo.setMemory_slot(rs.getInt("memory_slot"));
            	mobo.setMax_memory(rs.getInt("max_memory"));
            	mobo.setColor(rs.getString("color"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return mobo;
	}
	
	//delete
	public void deleteMotherboard(int id) {
		String q = "delete from motherboard where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}	
}