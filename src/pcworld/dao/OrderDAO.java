package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import pcworld.connection.ConnectionManager;
import pcworld.model.Orders;

public class OrderDAO {		
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static int id, customer_id, staff_id, latest_id;
	static String date, status;
	static double total_price;
	
	
	
	//add new order
	public int add(int customer_id) {
		int latest_id = 0;
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into orders (customer_id) values(?)");
			ps.setInt(1,customer_id);
			ps.executeUpdate();
			
			ps=currentCon.prepareStatement("select max(id) from orders");	
			rs = ps.executeQuery();
			if (rs.next()) {
				latest_id = rs.getInt("max(id)");
	        }
			
			System.out.println("Your latest order ID is " + latest_id);
		}
		
		catch (Exception ex) {
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
		return latest_id;
	}
	
	// Customer - confirm order
	public void confirm() {
		try {
        	currentCon = ConnectionManager.getConnection();
        	stat = currentCon.createStatement();
        	String sql = "UPDATE orders SET date = SYSDATE status = '"+status+"' WHERE id = '"+id+"'";
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	//Staff - get all order to display in order-list.jsp
	public List<Orders> getAllOrder() {
        List<Orders> orders = new ArrayList<Orders>();
        try {
        	currentCon = ConnectionManager.getConnection();
        	stat = currentCon.createStatement();
        	String sql = "SELECT * FROM orders ORDER BY FIELD(status, 'Pending','Processing','Completed','Cancelled')";
            ResultSet rs = stat.executeQuery(sql);
            System.out.println("RS order: "+ rs.next());
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setDate(rs.getString("order_date"));
                order.setStaff_id(rs.getInt("staff_id"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setStatus(rs.getString("status"));
                
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
	
	//Staff - update order status
	public void updateOrderStatus(Orders order){
		id = order.getId();
		status = order.getStatus();
		staff_id = order.getStaff_id();
		
		String sql = "UPDATE orders SET status ='" + status + "', staff_id = '"+staff_id+"' WHERE id = '" + id + "'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//for delete order
	public  void deleteOrder(int id) {
        try {
        	currentCon = ConnectionManager.getConnection();
        	ps=currentCon.prepareStatement("delete from orders where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	

	//get detail about a order from its order
	public Orders getOrderByID(Integer orderID) {
		Orders order = new Orders();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from orders where id = ?");
	        
	        ps.setInt(1, orderID);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	order.setId(rs.getInt("id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setDate(rs.getString("order_date"));
                order.setStaff_id(rs.getInt("staff_id"));
                order.setStatus(rs.getString("status"));
        	}
	    }
	        catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return order;
	}
	
	//Customer - get draft order
	public Orders getDraftByCustId(int customer_id) {
    	Orders order = new Orders();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from orders where customer_id = ? AND status = 'Draft'");
	        ps.setInt(1, customer_id);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	order.setId(rs.getInt("id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setDate(rs.getString("order_date"));
                order.setStaff_id(rs.getInt("staff_id"));
                order.setStatus(rs.getString("status"));
                order.setExist(true);
        	}
	    }
	        catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return order;
	}
	
	//get latest draft id
	public int getDraftId(int customer_id) {
		int draft_id = 0;
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select id from orders where customer_id = '"+customer_id+"' && status = 'Draft'");
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	draft_id = rs.getInt("id");
	        	System.out.println("Draft id : " + draft_id);
        	}
	    }
	        catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return draft_id;
	}
	
	//Customer - get customer order list
	public List<Orders> getOrderByCustId(Integer customer_id) {
		List<Orders> orders = new ArrayList<Orders>();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from orders where customer_id = ? AND status != 'Draft'");
	        
	        ps.setInt(1, customer_id);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
				Orders order = new Orders();
	        	order.setId(rs.getInt("id"));
                order.setCustomer_id(rs.getInt("customer_id"));
                order.setDate(rs.getString("order_date"));
                order.setTotal_price(rs.getDouble("total_price"));
                order.setStaff_id(rs.getInt("staff_id"));
                order.setStatus(rs.getString("status"));
                
                orders.add(order);
        	}
	    }
	        catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return orders;
	}
	
	//Customer - confirm order
	public void lockOrder(Orders order){
		id = order.getId();
		total_price = order.getTotal_price();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy hh:mma");
		LocalDateTime now = LocalDateTime.now();
		String order_date = dtf.format(now);
		
		String sql = "UPDATE orders SET order_date = '"+order_date+"', total_price = '"+total_price+"', status ='Pending' WHERE id = '" +id+ "'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}