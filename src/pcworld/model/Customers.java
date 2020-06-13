package pcworld.model;

public class Customers {
	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private boolean valid;
	private String role;
	
	public Customers() {
		super();
	}
	
	//Constructor for login
	public Customers(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	//Constructor for register
		public Customers(String name, String email, String phone, String password) {
			super();
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.password = password;
		}
	
	public Customers(int id, String name, String password, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
