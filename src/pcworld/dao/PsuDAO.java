package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.PSU;

public class PsuDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String event_description;
	int id, watt;
	String psu_type, eff, color, imageName;

	//new psu
	public void add(PSU psu){
		
		id = psu.getId();
		watt = psu.getWattage();
		psu_type = psu.getPsu_type();
		eff = psu.getEfficiency();
		color = psu.getColor();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into psu (id, wattage, psu_type, efficiency, color) values (?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, watt);
			ps.setString(3, psu_type);
			ps.setString(4, eff);
			ps.setString(5, color);
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
	public void update(PSU psu) {

		id = psu.getId();
		watt = psu.getWattage();
		psu_type = psu.getPsu_type();
		eff = psu.getEfficiency();
		color = psu.getColor();
		
		String q = "UPDATE psu SET wattage='"+watt+"', psu_type='"+psu_type+"', efficiency='"+eff+"',, color='"+color+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<PSU> getAllPsu() {
		ArrayList<PSU> psus = new ArrayList<PSU>();
		
		String q = "select * from psu join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	PSU psu = new PSU();
            	
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	psu.setId(rs.getInt("id"));
				psu.setBrand(rs.getString("brand"));
				psu.setModel(rs.getString("model"));
				psu.setPrice(rs.getDouble("price"));
				psu.setImage(imageName);
				psu.setType(rs.getString("type"));
				psu.setWattage(rs.getInt("wattage"));
				psu.setPsu_type(rs.getString("psu_type"));
				psu.setEfficiency(rs.getString("efficiency"));
            	psu.setColor(rs.getString("color"));
            	
            	psus.add(psu);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return psus;
	}
	
	public PSU getPsuById(int id) {
		PSU psu = new PSU();
		
		String q = "select * from psu join components using(id) where id='" + id + "'";

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
            	psu.setId(rs.getInt("id"));
				psu.setBrand(rs.getString("brand"));
				psu.setModel(rs.getString("model"));
				psu.setPrice(rs.getDouble("price"));
				psu.setImage(imageName);
				psu.setType(rs.getString("type"));
				psu.setWattage(rs.getInt("wattage"));
				psu.setPsu_type(rs.getString("psu_type"));
				psu.setEfficiency(rs.getString("efficiency"));
            	psu.setColor(rs.getString("color"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return psu;
	}
	
	//delete
	public void deletePsu(int id) {
		String q = "delete from psu where id=" + id;
		
		try {
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}	
}