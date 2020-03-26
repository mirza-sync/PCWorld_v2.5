package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.Cooler;

public class CoolerDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String event_description;
	int id, length, width, height;
	String c_type, color;

	//new cool
	public void add(Cooler cool){
		
		id = cool.getId();
		c_type = cool.getCooler_type();
		length = cool.getLength();
		width = cool.getWidth();
		height = cool.getHeight();
		color = cool.getColor();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into cooler (id, cooler_type, length, width, height, color) values (?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, c_type);
			ps.setInt(3, length);
			ps.setInt(4, width);
			ps.setInt(5, height);
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
	public void update(Cooler cool) {

		id = cool.getId();
		c_type = cool.getCooler_type();
		length = cool.getLength();
		width = cool.getWidth();
		height = cool.getHeight();
		color = cool.getColor();
		
		String q = "UPDATE cooler SET formfactor='"+c_type+"', length='"+length+"', width='"+width+"', height='"+height+"', color='"+color+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Cooler> getAllCooler() {
		ArrayList<Cooler> cools = new ArrayList<Cooler>();
		
		String q = "select * from cooler join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	Cooler cool = new Cooler();
            	
            	cool.setId(rs.getInt("id"));
				cool.setBrand(rs.getString("brand"));
				cool.setBrand(rs.getString("model"));
				cool.setPrice(rs.getDouble("price"));
				cool.setImage(rs.getString("image"));
				cool.setBrand(rs.getString("model"));
				cool.setType(rs.getString("type"));
				cool.setCooler_type(rs.getString("cooler_type"));
            	cool.setLength(rs.getInt("length"));
            	cool.setWidth(rs.getInt("width"));
            	cool.setHeight(rs.getInt("height"));
            	cool.setColor(rs.getString("color"));
            	
            	cools.add(cool);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return cools;
	}
	
	public Cooler getCoolerById(int id) {
		Cooler cool = new Cooler();
		
		String q = "select * from cooler join components using(id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            if (rs.next()) {
            	cool.setId(rs.getInt("id"));
				cool.setBrand(rs.getString("brand"));
				cool.setBrand(rs.getString("model"));
				cool.setPrice(rs.getDouble("price"));
				cool.setImage(rs.getString("image"));
				cool.setBrand(rs.getString("model"));
				cool.setType(rs.getString("type"));
				cool.setCooler_type(rs.getString("cooler_type"));
            	cool.setLength(rs.getInt("length"));
            	cool.setWidth(rs.getInt("width"));
            	cool.setHeight(rs.getInt("height"));
            	cool.setColor(rs.getString("color"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return cool;
	}
	
	//delete
	public void deleteCooler(int id) {
		String q = "delete from cooler where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}	
}