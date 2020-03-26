package pcworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pcworld.connection.ConnectionManager;
import pcworld.model.Users;

public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
	static int id;
	static String name, pass, email, phone, role;

	public static Users login(Users user) {
		Statement stmt = null;
		id = user.getId();
		pass = user.getPassword();
		String searchQuery = "select * from users where id = '"+id+"' AND password = '"+pass+"'";

		System.out.println("Your user name is " + id);
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
			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please signup first");
				user.setValid(false);
			}
			// if user exists set the isValid variable to true
			else if (more) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				user.setValid(true);
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
		return user;
	}
	
	//Register
	public int add(Users user) {
		int new_id = 0;
		
		name = user.getName();
		email = user.getEmail();
		pass = user.getPassword();
		
		String maxQuery = "select MAX(id) from users";

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into users (name, email, password)values(?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,pass);
			ps.executeUpdate();
			
			ps = currentCon.prepareStatement(maxQuery);
			rs = ps.executeQuery();
			if(rs.next()) {
				new_id = rs.getInt("MAX(user_id)");
			}

			ps.close();

			System.out.println("Register success. Your Teacher ID is : " + new_id);

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

	public static Users getUserById(Users user)  {

		id = user.getId();

		String searchQuery = "select * from users where id='" + id + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				id = rs.getInt("id");

				user.setId(id);
				user.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry");
				user.setValid(false);
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

		return user;
	}


	public void updateAccount(Users user) {
		
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		pass = user.getPassword();
		phone = user.getPhone();
		
	    String sql = "UPDATE users SET name='" + name  + "', password='"+pass+"', email='"+email+"', phone='"+phone+"' WHERE id= '" + id + "'";
		
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

	public List<Users> getAllCustomer() {
		List<Users> users = new ArrayList<Users>();
		System.out.println("Enter allTeacher");
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select * from users where user_role !='Headmaster'";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Users user = new Users();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
				
				users.add(user);
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
		System.out.println("Done allTeacher");
		return users;
	}

	public Users getTeacherById(int id) {
		Users user = new Users();

		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("select * from users where user_id=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();


			if (rs.next()) {	       
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
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

		return user;
	}
	
	public void deleteTeacher(int user_id) {
		String sql = "delete from users where user_id = '"+user_id+"'";
		
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
	
	//Headmaster can view all user
	public List<Users> getAllTeacher() {
		List<Users> users = new ArrayList<Users>();
		String sql = "select * from users where user_role !='Headmaster'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
			
		} catch(SQLException e) {
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
		
		return users;
	}
	
	//Dropdown to show available mentors
	public List<Users> getAllMentor(int id) {
		List<Users> mentorArray = new ArrayList<Users>();
		String sql = "select * from users where user_role = 'Mentor' AND user_id !='"+id+"'";
		
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
				
				mentorArray.add(user);
				System.out.println("The name : " + rs.getString("user_name"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mentor End");
		return mentorArray;
	}
}
