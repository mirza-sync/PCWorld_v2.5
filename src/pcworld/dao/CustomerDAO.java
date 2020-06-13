package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pcworld.connection.ConnectionManager;
import pcworld.model.Customers;

public class CustomerDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int id;
	static String name, pass, email, phone;

	public Customers login(Customers customer) {
		Statement stmt = null;
		email = customer.getEmail();
		pass = customer.getPassword();
		String searchQuery = "select * from customers where email = '"+email+"' AND password = '"+pass+"'";

		System.out.println("Your email is " + email);
		System.out.println("Your password is " + pass);
		System.out.println("Query: " + searchQuery);
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			if (!rs.isBeforeFirst() ) {    //To check if resultSet gives no rows
			    System.out.println("No data!!!"); 
			}
			
			boolean more = rs.next();
			// if customer does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered customer! Please signup first");
				customer.setValid(false);
			}
			// if customer exists set the isValid variable to true
			else if (more) {
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setValid(true);
			}
		}
		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
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
		return customer;
	}
	
	//Register
	public int add(Customers customer) {
		int new_id = 0;
		
		name = customer.getName();
		email = customer.getEmail();
		pass = customer.getPassword();
		phone = customer.getPhone();
		
		String maxQuery = "select MAX(id) from customers";

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into customers (name, email, password, phone) values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,pass);
			ps.setString(4,phone);
			ps.executeUpdate();
			
			ps = currentCon.prepareStatement(maxQuery);
			rs = ps.executeQuery();
			if(rs.next()) {
				new_id = rs.getInt("MAX(id)");
			}

			ps.close();

			System.out.println("Register success. Your customer ID is : " + new_id);

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occurred! " + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return new_id;
	}

	public static Customers getCustomer(Customers customer)  {

		id = customer.getId();

		String searchQuery = "select * from customers where id='" + id + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if customer exists set the isValid variable to true
			if (more) {
				id = rs.getInt("id");

				customer.setId(id);
				customer.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry, customer does not exist");
				customer.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
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

		return customer;
	}


	public void updateAccount(Customers customer) {
		
		id = customer.getId();
		name = customer.getName();
		email = customer.getEmail();
		pass = customer.getPassword();
		phone = customer.getPhone();
		
	    String sql = "UPDATE customers SET name='" + name  + "', password='"+pass+"', email='"+email+"', phone='"+phone+"' WHERE id= '" + id + "'";
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(sql);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		finally {
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
	}

	public List<Customers> getAllCustomer() {
		List<Customers> customers = new ArrayList<Customers>();
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select * from customers";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Customers customer = new Customers();
				
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
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
		return customers;
	}

	public Customers getCustomerById(int id) {
		Customers customer = new Customers();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("select * from customers where id=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();


			if (rs.next()) {	       
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setPassword(rs.getString("password"));
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

			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return customer;
	}
	
	public void deleteCustomer(int id) {
		String sql = "delete from customers where id = '"+id+"'";
		
		System.out.println(sql);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(sql);
	        
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

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		
	}
}
