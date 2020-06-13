package pcworld.model;

public class OrderItem {
	private int order_id;
	private int component_id;
	private int quantity;
	private Components component;
	private boolean exist;
	
	public OrderItem() {
		super();
	}
	public OrderItem(int order_id, int component_id, int quantity) {
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
	public Components getComponent() {
		return component;
	}
	public void setComponent(Components component) {
		this.component = component;
	}
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
}
