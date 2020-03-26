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
	int id, num_vram, length, width, height, clock, watt;
	String chipset, vram_type, color;

	//new gpu
	public void add(GPU gpu){
		
		id = gpu.getId();
		chipset = gpu.getChipset();
		num_vram = gpu.getNum_vram();
		vram_type = gpu.getVram_type();
		length = gpu.getLength();
		width = gpu.getWidth();
		height = gpu.getHeight();
		color = gpu.getColor();
		clock = gpu.getCore_clock();
		watt = gpu.getWattage();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			
			ps=currentCon.prepareStatement("insert into gpu (id, chipset, num_vram, vram_type, length, width, height, color, core_clock, wattage) values (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, chipset);
			ps.setInt(3, num_vram);
			ps.setString(4, vram_type);
			ps.setInt(5, length);
			ps.setInt(6, width);
			ps.setInt(7, height);
			ps.setString(8, color);
			ps.setInt(9, clock);
			ps.setInt(10, watt);
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
		vram_type = gpu.getVram_type();
		length = gpu.getLength();
		width = gpu.getWidth();
		height = gpu.getHeight();
		color = gpu.getColor();
		clock = gpu.getCore_clock();
		watt = gpu.getWattage();
		
		String q = "UPDATE gpu SET chipset='"+chipset+"', num_vram='"+num_vram+"', vram_type='"+vram_type+"', length='"+length+"', width='"+width+"', height='"+height+"', color='"+color+"', core_clock='"+clock+"', wattage='"+watt+"' WHERE id='"+id+"'";

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
            	
            	gpu.setId(rs.getInt("id"));
				gpu.setBrand(rs.getString("brand"));
				gpu.setModel(rs.getString("model"));
				gpu.setPrice(rs.getDouble("price"));
				gpu.setImage(rs.getString("image"));
				gpu.setType(rs.getString("type"));
            	gpu.setChipset(rs.getString("chipset"));
            	gpu.setNum_vram(rs.getInt("num_vram"));
            	gpu.setVram_type(rs.getString("vram_type"));
            	gpu.setLength(rs.getInt("length"));
            	gpu.setWidth(rs.getInt("width"));
            	gpu.setHeight(rs.getInt("height"));
            	gpu.setColor(rs.getString("color"));
            	gpu.setCore_clock(rs.getInt("core_clock"));
            	gpu.setWattage(rs.getInt("wattage"));
            	
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
            	gpu.setId(rs.getInt("id"));
				gpu.setBrand(rs.getString("brand"));
				gpu.setModel(rs.getString("model"));
				gpu.setPrice(rs.getDouble("price"));
				gpu.setImage(rs.getString("image"));
				gpu.setType(rs.getString("type"));
            	gpu.setChipset(rs.getString("chipset"));
            	gpu.setNum_vram(rs.getInt("num_vram"));
            	gpu.setVram_type(rs.getString("vram_type"));
            	gpu.setLength(rs.getInt("length"));
            	gpu.setWidth(rs.getInt("width"));
            	gpu.setHeight(rs.getInt("height"));
            	gpu.setColor(rs.getString("color"));
            	gpu.setCore_clock(rs.getInt("core_clock"));
            	gpu.setWattage(rs.getInt("wattage"));
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
	public void deleteGPU(int id) {
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