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
	int id, length, width, height, mem_slot, max_mem;
	String form, socket, mem_type, color;

	//new mobo
	public void add(Motherboard mobo){
		
		id = mobo.getId();
		form = mobo.getFormfactor();
		length = mobo.getLength();
		width = mobo.getWidth();
		height = mobo.getHeight();
		socket = mobo.getSocket();
		mem_type = mobo.getMemory_type();
		mem_slot = mobo.getMemory_slot();
		max_mem = mobo.getMax_memory();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into motherboard (id, formfactor, length, width, height, socket, memory_type, memory_slot, max_memory, color) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, form);
			ps.setInt(3, length);
			ps.setInt(4, width);
			ps.setInt(5, height);
			ps.setString(6, socket);
			ps.setString(6, mem_type);
			ps.setInt(7, mem_slot);
			ps.setInt(8, max_mem);
			ps.setString(10, color);
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
		length = mobo.getLength();
		width = mobo.getWidth();
		height = mobo.getHeight();
		socket = mobo.getSocket();
		mem_type = mobo.getMemory_type();
		mem_slot = mobo.getMemory_slot();
		max_mem = mobo.getMax_memory();
		
		String q = "UPDATE motherboard SET formfactor='"+form+"', length='"+length+"', width='"+width+"', height='"+height+"', socket='"+socket+"', memory_type='"+mem_type+"', memory_slot='"+mem_slot+"', max_memory='"+max_mem+"', color='"+color+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Motherboard> getAllMobo() {
		ArrayList<Motherboard> mobos = new ArrayList<Motherboard>();
		
		String q = "select * from motherboard join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	Motherboard mobo = new Motherboard();
            	
            	mobo.setId(rs.getInt("id"));
				mobo.setBrand(rs.getString("brand"));
				mobo.setModel(rs.getString("model"));
				mobo.setPrice(rs.getDouble("price"));
				mobo.setImage(rs.getString("image"));
				mobo.setType(rs.getString("type"));
				mobo.setFormfactor(rs.getString("formfactor"));
            	mobo.setLength(rs.getInt("length"));
            	mobo.setWidth(rs.getInt("width"));
            	mobo.setHeight(rs.getInt("height"));
            	mobo.setSocket(rs.getString("socket"));
            	mobo.setMemory_type(rs.getString("memory_type"));
            	mobo.setMemory_slot(rs.getInt("memory_slot"));
            	mobo.setMax_memory(rs.getInt("max_memory"));
            	
            	mobos.add(mobo);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return mobos;
	}
	
	public Motherboard getMotherboardById(int id) {
		Motherboard mobo = new Motherboard();
		
		String q = "select * from mobo join components using(id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            if (rs.next()) {
            	mobo.setId(rs.getInt("id"));
				mobo.setBrand(rs.getString("brand"));
				mobo.setModel(rs.getString("model"));
				mobo.setPrice(rs.getDouble("price"));
				mobo.setImage(rs.getString("image"));
				mobo.setType(rs.getString("type"));
				mobo.setFormfactor(rs.getString("formfactor"));
            	mobo.setLength(rs.getInt("length"));
            	mobo.setWidth(rs.getInt("width"));
            	mobo.setHeight(rs.getInt("height"));
            	mobo.setSocket(rs.getString("socket"));
            	mobo.setMemory_type(rs.getString("memory_type"));
            	mobo.setMemory_slot(rs.getInt("memory_slot"));
            	mobo.setMax_memory(rs.getInt("max_memory"));
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
	public void deletemobo(int id) {
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