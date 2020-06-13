package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pcworld.connection.ConnectionManager;
import pcworld.model.Components;
import pcworld.model.OrderItem;

public class OrderItemDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static int order_id, component_id, quantity;
	
	ComponentDAO compdao = new ComponentDAO();
	
	//add new order items
	public void add(OrderItem orderItem) {
		order_id = orderItem.getOrder_id();
		component_id = orderItem.getComponent_id();
		quantity = orderItem.getQuantity();
		System.out.println("OIdao-> Order:"+order_id+" Comp:"+component_id+" Quant:"+quantity);
	
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into orderitem (order_id, component_id, quantity) values(?,?,?)");
			ps.setInt(1,order_id);
			ps.setInt(2,component_id);
			ps.setInt(3,quantity);
			ps.executeUpdate();
		}
		
		catch (Exception ex) {
			System.out.println("Hhaha FK error");
			System.out.println("failed: An Exception has occured!" + ex);
		}
		
		finally {
			if (ps != null){
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e_) {
					currentCon = null;
				}
			}
		}
	}
	
	//get array of orderitem
	public List<OrderItem> getItemsByOrderId (int order_id) {
		List<OrderItem> orderItemArray = new ArrayList<OrderItem>();
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from orders join orderitem ON orderitem.order_id = orders.id where orders.id = ?");
			ps.setInt(1, order_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				
				Components comp = new Components();
				comp = compdao.getComponentById(rs.getInt("component_id"));
				
				orderItem.setComponent(comp);
				orderItem.setQuantity(rs.getInt("quantity"));
				
				orderItemArray.add(orderItem);
			}
		}
		catch (SQLException e) {
	    	e.printStackTrace();
	    }
		//return array of orderitem, with the components and the quantity
		return orderItemArray;
	}
	
	//Customer check if component already exist in order
	public OrderItem getItemFromOrder(int component_id, int order_id) {
		OrderItem orderitem = new OrderItem();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from orderitem where component_id = ? && order_id = ?");
			ps.setInt(1, component_id);
			ps.setInt(2, order_id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				orderitem.setComponent_id(rs.getInt("component_id"));
				orderitem.setOrder_id(rs.getInt("order_id"));
				orderitem.setQuantity(rs.getInt("quantity"));
				orderitem.setExist(true);
			}
		}
		catch (SQLException e) {
	    	e.printStackTrace();
	    }
		//return array of orderitem, with the components and the quantity
		return orderitem;
	}
	
	//Customer - update quantity of a component
	public void updateQuantity(OrderItem orderItem){
		order_id = orderItem.getOrder_id();
		component_id = orderItem.getComponent_id();
		quantity = orderItem.getQuantity();
		
		String searchQuery = "UPDATE orderitem SET quantity= '" + quantity + "'  WHERE order_id = '" + order_id + "' AND component_id = '"+ component_id +"'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			stat.executeUpdate(searchQuery);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Customer - delete unwanted components
	public void deleteItemFromOrder(int component_id, int order_id){		
		String searchQuery = "DELETE FROM orderitem WHERE order_id = '" + order_id + "' AND component_id = '"+ component_id +"'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			stat.executeUpdate(searchQuery);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
