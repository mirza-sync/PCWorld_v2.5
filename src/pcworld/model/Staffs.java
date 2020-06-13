package pcworld.model;

public class Staffs {
	private int id;
	private String ic;
	private String name;
	private String password;
	private String role;
	private boolean valid;
	
	public Staffs() {
		super();
	}
	
	public Staffs(String ic, String password) {
		super();
		this.ic = ic;
		this.password = password;
	}
	
	public Staffs(String ic, String name, String password) {
		super();
		this.ic = ic;
		this.name = name;
		this.password = password;
	}
	
	public Staffs(int id, String ic, String name, String password, boolean valid) {
		super();
		this.id = id;
		this.ic = ic;
		this.name = name;
		this.password = password;
		this.valid = valid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIc() {
		return ic;
	}
	public void setIc(String ic) {
		this.ic = ic;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
