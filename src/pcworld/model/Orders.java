package pcworld.model;

public class Orders {
	private int id;
	private int customer_id;
	private String date;
	private String status;
	private double total_price;
	private int staff_id;
	private boolean exist;
	
	public Orders() {
		super();
	}
	public Orders(int id, int customer_id, String date, String status, double total_price, int staff_id) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.date = date;
		this.status = status;
		this.total_price = total_price;
		this.staff_id = staff_id;
	}
	//Simplified constructor that will be used to create new order
	public Orders(int customer_id, double total_price) {
		super();
		this.customer_id = customer_id;
		this.total_price = total_price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
