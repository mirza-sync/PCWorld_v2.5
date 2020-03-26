package pcworld.model;

public class Orders {
	public int id;
	public int user_id;
	public String order_date;
	public String status;
	
	public Orders() {
		super();
	}
	public Orders(int id, int user_id, String order_date, String status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.order_date = order_date;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
