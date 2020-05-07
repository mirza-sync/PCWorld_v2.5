package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.GPU;

public class GpuDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String event_description;
	int id, num_vram, clock;
	String chipset, color, imageName;

	//new gpu
	public void add(GPU gpu){
		
		id = gpu.getId();
		chipset = gpu.getChipset();
		num_vram = gpu.getNum_vram();
		color = gpu.getColor();
		clock = gpu.getCore_clock();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into gpu (id, chipset, num_vram, color, core_clock) values (?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, chipset);
			ps.setInt(3, num_vram);
			ps.setString(4, color);
			ps.setInt(5, clock);
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
	
	//update RPH Event
	public void update(GPU gpu) {

		id = gpu.getId();
		chipset = gpu.getChipset();
		num_vram = gpu.getNum_vram();
		color = gpu.getColor();
		clock = gpu.getCore_clock();
		
		String q = "UPDATE gpu SET chipset='"+chipset+"', num_vram='"+num_vram+"', color='"+color+"', core_clock='"+clock+"' WHERE id='"+id+"'";

		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<GPU> getAllGpu() {
		ArrayList<GPU> gpus = new ArrayList<GPU>();
		
		String q = "select * from gpu join components using(id)";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            while(rs.next()) {
            	GPU gpu = new GPU();
            	
            	if(rs.getString("image")==null) {
					imageName = "noimage2.png";
				} else {
					imageName = rs.getString("image");
				}
            	
            	gpu.setId(rs.getInt("id"));
				gpu.setBrand(rs.getString("brand"));
				gpu.setModel(rs.getString("model"));
				gpu.setPrice(rs.getDouble("price"));
				gpu.setImage(imageName);
				gpu.setType(rs.getString("type"));
            	gpu.setChipset(rs.getString("chipset"));
            	gpu.setNum_vram(rs.getInt("num_vram"));
            	gpu.setColor(rs.getString("color"));
            	gpu.setCore_clock(rs.getInt("core_clock"));
            	
            	gpus.add(gpu);
           	}
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return gpus;
	}
	
	public GPU getGpuById(int id) {
		GPU gpu = new GPU();
		
		String q = "select * from gpu join components using(id) where id='" + id + "'";

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
            	gpu.setId(rs.getInt("id"));
				gpu.setBrand(rs.getString("brand"));
				gpu.setModel(rs.getString("model"));
				gpu.setPrice(rs.getDouble("price"));
				gpu.setImage(imageName);
				gpu.setType(rs.getString("type"));
            	gpu.setChipset(rs.getString("chipset"));
            	gpu.setNum_vram(rs.getInt("num_vram"));
            	gpu.setColor(rs.getString("color"));
            	gpu.setCore_clock(rs.getInt("core_clock"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return gpu;
	}
	
	//delete event
	public void deleteGpu(int id) {
		String q = "delete from gpu where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}	
}