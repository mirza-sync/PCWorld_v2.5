package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pcworld.connection.ConnectionManager;
import pcworld.model.Storage;

public class StorageDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	int id, capacity, r_speed, w_speed;
	String storage_type, form;

	//new storage
	public void add(Storage storage){
		
		id = storage.getId();
		storage_type = storage.getStorage_type();
		capacity = storage.getCapacity();
		form = storage.getForm();
		r_speed = storage.getRead_speed();
		w_speed = storage.getWrite_speed();
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
           						
			ps=currentCon.prepareStatement("insert into storage (id, storage_type, capacity, formfactor, read_speed, write_speed) values (?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, storage_type);
			ps.setInt(3, capacity);
			ps.setString(4, form);
			ps.setInt(5, r_speed);
			ps.setInt(6, w_speed);
			ps.executeUpdate();
			
			ps.close();
			
			System.out.println("Storage is saved");
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
	
	//update storage
	public void update(Storage storage) {

		id = storage.getId();
		storage_type = storage.getStorage_type();
		capacity = storage.getCapacity();
		form = storage.getForm();
		r_speed = storage.getRead_speed();
		w_speed = storage.getWrite_speed();
		
		String q = "UPDATE storage SET storage_type = '"+storage_type+"',  capacity = '"+capacity+"', formfactor = '"+form+"',   read_speed = '"+r_speed+"', write_speed = '"+w_speed+"' WHERE id = '"+id+"'";
		
		System.out.println("Update query : "+q);
		
		try {

	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Storage> getAllStorage(){
		ArrayList<Storage> storages = new ArrayList<Storage>();
		String q = "select * from storage join components using (id)";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(q);
			
			//To check if resultSet gives no rows
			if (!rs.isBeforeFirst()) {
			    System.out.println("No data!"); 
			}
			
			while (rs.next()) {
				Storage storage = new Storage();

				storage.setId(rs.getInt("id"));
				storage.setBrand(rs.getString("brand"));
				storage.setModel(rs.getString("model"));
				storage.setPrice(rs.getDouble("price"));
				storage.setImage(rs.getString("image"));
				storage.setBrand(rs.getString("model"));
				storage.setType(rs.getString("type"));
				storage.setStorage_type(rs.getString("storage_type"));
            	storage.setCapacity(rs.getInt("capacity"));
            	storage.setForm(rs.getString("formfactor"));
            	storage.setRead_speed(rs.getInt("read_speed"));
            	storage.setWrite_speed(rs.getInt("write_speed"));
            	
				storages.add(storage);
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
		return storages;
	}
	
	public Storage getstorageById(int id) {
		Storage storage = new Storage();
		
		System.out.println("storage id : " + id);
		
		String q = "select * from storage JOIN components using (id) where id='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(q);
            
            if (rs.next()) {
            	storage.setId(rs.getInt("id"));
            	storage.setBrand(rs.getString("brand"));
            	storage.setModel(rs.getString("model"));
            	storage.setPrice(rs.getDouble("price"));
            	storage.setImage(rs.getString("image"));
            	storage.setType(rs.getString("type"));
            	storage.setStorage_type(rs.getString("storage_type"));
            	storage.setCapacity(rs.getInt("capacity"));
            	storage.setForm(rs.getString("formfactor"));
            	storage.setRead_speed(rs.getInt("read_speed"));
            	storage.setWrite_speed(rs.getInt("write_speed"));
           	}
           
            else {
            	System.out.println("No result");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
	    return storage;
	}
	
	//delete storage
	public void deletestorage(int id) {
		String q = "delete from storage where id=" + id;
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(q);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
}