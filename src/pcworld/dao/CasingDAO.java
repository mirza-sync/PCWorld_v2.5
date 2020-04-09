package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.Casing;

public class CasingDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String event_description;
	int id, length, width, height;
	String form, color, imageName;

	//new casing
	public void add(Casing casing){
		
		id = casing.getId();
		form = casing.getForm();
		length = casing.getLength();
		width = casing.getWidth();
		height = casing.getHeight();
		color = casing.getColor();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into Casing (id, formfactor, length, width, height, color) values (?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, form);
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
	public void update(Casing casing) {

		id = casing.getId();
		form = casing.getForm();
		length = casing.getLength();
		width = casing.getWidth();
		height = casing.getHeight();
		color = casing.getColor();
		
		String q = "UPDATE Casing SET formfactor='"+form+"', length='"+length+"', width='"+width+"', height='"+height+"', color='"+color+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Casing> getAllCasing() {
		ArrayList<Casing> casings = new ArrayList<Casing>();
		
		String q = "select * from casing join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	Casing casing = new Casing();
            	
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	casing.setId(rs.getInt("id"));
				casing.setBrand(rs.getString("brand"));
				casing.setModel(rs.getString("model"));
				casing.setPrice(rs.getDouble("price"));
				casing.setImage(imageName);
				casing.setType(rs.getString("type"));
				casing.setForm(rs.getString("formfactor"));
            	casing.setLength(rs.getInt("length"));
            	casing.setWidth(rs.getInt("width"));
            	casing.setHeight(rs.getInt("height"));
            	casing.setColor(rs.getString("color"));
            	
            	casings.add(casing);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return casings;
	}
	
	public Casing getCasingById(int id) {
		Casing casing = new Casing();
		
		String q = "select * from casing join components using(id) where id='" + id + "'";

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
            	casing.setId(rs.getInt("id"));
				casing.setBrand(rs.getString("brand"));
				casing.setModel(rs.getString("model"));
				casing.setPrice(rs.getDouble("price"));
				casing.setImage(imageName);
				casing.setType(rs.getString("type"));
				casing.setForm(rs.getString("formfactor"));
            	casing.setLength(rs.getInt("length"));
            	casing.setWidth(rs.getInt("width"));
            	casing.setHeight(rs.getInt("height"));
            	casing.setColor(rs.getString("color"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return casing;
	}
	
	//delete
	public void deleteCasing(int id) {
		String q = "delete from casing where id=" + id;
		
		try {
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}	
}