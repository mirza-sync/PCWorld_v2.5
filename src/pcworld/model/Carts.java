package pcworld.model;

public class Carts {
	public int order_id;
	public int component_id;
	public int quantity;
	
	public Carts() {
		super();
	}
	public Carts(int order_id, int component_id, int quantity) {
		super();
		this.order_id = order_id;
		this.component_id = component_id;
		this.quantity = quantity;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getComponent_id() {
		return component_id;
	}
	public void setComponent_id(int component_id) {
		this.component_id = component_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
