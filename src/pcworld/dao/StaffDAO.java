package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pcworld.connection.ConnectionManager;
import pcworld.model.Staffs;

public class StaffDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int id;
	static String ic, name, pass, email, phone;

	public Staffs login(Staffs staff) {
		Statement stmt = null;
		ic = staff.getIc();
		pass = staff.getPassword();
		String searchQuery = "select * from staffs where ic = '"+ic+"' AND password = '"+pass+"'";

		System.out.println("Your staff IC is " + ic);
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
			// if staff does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered staff! Please signup first");
				staff.setValid(false);
			}
			// if staff exists set the isValid variable to true
			else if (more) {
				staff.setId(rs.getInt("id"));
				staff.setIc(rs.getString("ic"));
				staff.setName(rs.getString("name"));
				staff.setRole(rs.getString("role"));
				System.out.println("staff DAO role : "+ rs.getString("role"));
				staff.setValid(true);
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
		return staff;
	}
	
	//Register
	public void add(Staffs staff) {
		ic = staff.getIc();
		name = staff.getName();
		pass = staff.getPassword();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into staffs (ic, name, password) values(?,?,?)");
			ps.setString(1,ic);
			ps.setString(2,name);
			ps.setString(3,pass);
			ps.executeUpdate();

			ps.close();

			System.out.println("Register success");

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
	}

	//Check if IC already exist
	public static Staffs getStaff(Staffs staff)  {
		id = staff.getId();
		ic = staff.getIc();

		String searchQuery = "select * from staffs where ic = '"+ic+"'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if staff exists set the isValid variable to true
			if (more) {
				ic = rs.getString("ic");

				staff.setIc(ic);
				staff.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry, staff does not exist");
				staff.setValid(false);
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

		return staff;
	}


	public void updateStaff(Staffs staff) {
		
		id = staff.getId();
		ic = staff.getIc();
		name = staff.getName();
		pass = staff.getPassword();
		
	    String sql = "UPDATE staffs SET ic = '"+ic+"', name='" + name  + "', password = '"+pass+"' WHERE id = '"+id+"'";
		
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

	public List<Staffs> getAllStaff() {
		List<Staffs> staffs = new ArrayList<Staffs>();
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select * from staffs";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Staffs staff = new Staffs();
				
				staff.setId(rs.getInt("id"));
				staff.setIc(rs.getString("ic"));
				staff.setName(rs.getString("name"));
				staff.setRole(rs.getString("role"));
				
				staffs.add(staff);
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
		return staffs;
	}

	public Staffs getStaffById(int id) {
		Staffs staff = new Staffs();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("select * from staffs where id=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {	       
				staff.setId(rs.getInt("id"));
				staff.setIc(rs.getString("ic"));
				staff.setName(rs.getString("name"));
				staff.setPassword(rs.getString("password"));
				staff.setRole(rs.getString("role"));
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

		return staff;
	}
	
	public void deleteStaff(int id) {
		String sql = "delete from staffs where id = '"+id+"'";
		
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
