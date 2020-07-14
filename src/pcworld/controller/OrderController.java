package pcworld.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pcworld.dao.CustomerDAO;
import pcworld.dao.OrderDAO;
import pcworld.dao.OrderItemDAO;
import pcworld.dao.StaffDAO;
import pcworld.model.Customers;
import pcworld.model.OrderItem;
import pcworld.model.Orders;
import pcworld.model.Staffs;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String MAIN = "main.jsp";
	String ORDERLIST_act = "/OrderController?action=orderlist";
	String ORDERLIST = "cust-orderlist.jsp";
	String STAFFORDER = "staff-orderlist.jsp";
	String MANAGEORDER_act = "/OrderController?action=viewitems&role=staff";
	String MANAGEORDER = "staff-manageorder.jsp";
	String ORDERITEM_act = "/OrderController?action=viewitems";
	String ORDERITEM = "cust-orderitem.jsp";
	String forward = "";
	
	OrderDAO orderdao = new OrderDAO();
	OrderItemDAO orderitemdao = new OrderItemDAO();
	CustomerDAO custdao = new CustomerDAO();
	StaffDAO staffdao = new StaffDAO();
    
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		//Creating new draft (Only if draft doesn't exist)
		if(action.equals("add")) {
			String cust_temp = request.getParameter("cust_id");
			
			if(cust_temp.equals("")) {
				forward = "cust-login.jsp";
			}
			else {
				int cust_id = Integer.parseInt(cust_temp);
				// Get component/item
				// recommend2.jsp - Array of items
				// component.jsp - Single item
				String[] items = request.getParameterValues("items");
				
				//Check if customer has a cart or not
				Orders draft = orderdao.getDraftByCustId(cust_id);
				System.out.println("OrderCont -> Draft id is : "+draft.getId());
				if(draft.isExist() == false) {	//if customer has no cart
					//Create new order; since a cart is dependent on order
					int latest_id = orderdao.add(cust_id);
					System.out.println("OrderCont latest id:" + latest_id);
					
					for(int i=0; i<items.length; i++) {
						int item = Integer.parseInt(items[i]);
						OrderItem orderitem = new OrderItem(latest_id, item, 1);
						//Create new cart/orderitems
						orderitemdao.add(orderitem);
					}
				}
				else {	//if cart already exist
					for(int i=0; i<items.length; i++) {
						int item_id = Integer.parseInt(items[i]);
						OrderItem item = orderitemdao.getItemFromOrder(item_id, draft.getId());
						System.out.println("Existed item id : "+item.getComponent_id());
						if(item.isExist() == false) {		// If item doesn't exist in cart, add new item to cart
							// Find latest draft
							int draft_id = orderdao.getDraftId(cust_id);
							// Insert the component with qty=1 in draft
							OrderItem orderitem = new OrderItem(draft_id, item_id, 1);
							orderitemdao.add(orderitem);
						}
						else {		// If component already exist, update quantity
							System.out.println("Item already exist");
							OrderItem orderitem = new OrderItem(draft.getId(), item_id, item.getQuantity()+1);
							orderitemdao.updateQuantity(orderitem);
						}
					}
				}
				// forward = ORDERITEM_act+"&role=customer&order_id="+draft.getId();
				forward = MAIN;
			}
		}
		else if(action.equals("confirm")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			double total_price = Double.parseDouble(request.getParameter("total_price"));
			
			Orders order = new Orders();
			order.setId(order_id);
			order.setTotal_price(total_price);
			
			orderdao.lockOrder(order);			
			forward = MAIN;
		}
		else if(action.equals("orderlist")) {
			String role = request.getParameter("role");
			if(role.equals("customer")) {
				int cust_id = Integer.parseInt(request.getParameter("cust_id"));
				request.setAttribute("draft", orderdao.getDraftByCustId(cust_id));
				request.setAttribute("orders", orderdao.getOrderByCustId(cust_id));
				forward = ORDERLIST;
			} else {
				request.setAttribute("orders", orderdao.getAllOrder());
				forward = STAFFORDER;
			}
		}
		else if(action.equals("viewitems")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			String role = request.getParameter("role");
			List<OrderItem> orderitems = new ArrayList<OrderItem>();
			if(role.equals("customer")) {
				orderitems = orderitemdao.getItemsByOrderId(order_id);
				double total = 0;
				for(OrderItem oi : orderitems) {
					total += oi.getComponent().getPrice() * oi.getQuantity();
				}
				request.setAttribute("orderitems", orderitems);
				request.setAttribute("order", orderdao.getOrderByID(order_id));
				request.setAttribute("total", total);
				forward = ORDERITEM;
			}
			else {
				Orders order = new Orders();
				order = orderdao.getOrderByID(order_id);
				Customers cust = new Customers();
				cust = custdao.getCustomerById(order.getCustomer_id());
				Staffs staff = new Staffs();
				staff = staffdao.getStaffById(order.getStaff_id());
				
				orderitems = orderitemdao.getItemsByOrderId(order_id);
				
				request.setAttribute("orderitems", orderitems);
				request.setAttribute("order", order);
				request.setAttribute("customer", cust);
				request.setAttribute("staff", staff);
				forward = MANAGEORDER;
			}
		}
		else if (action.equals("updatestatus")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			String status = request.getParameter("status");
			
			Orders order = new Orders();
			order.setId(order_id);
			order.setStatus(status);
			order.setStaff_id(staff_id);
			orderdao.updateOrderStatus(order);
			
			forward = MANAGEORDER_act+"&order_id="+order_id;
		}
//		else if(action.equals("vieworder")) {
//			List<OrderItem> orderitems = new ArrayList<OrderItem>();
//			int order_id = Integer.parseInt(request.getParameter("order_id"));
//			orderitems = orderitemdao.getItemsByOrderId(order_id);
//			request.setAttribute("orderitems", orderitems);
//			request.setAttribute("order_id", order_id);
//			forward = ORDERITEM;
//		}
//		else if(action.equals("additem")) {
//			int order_id = Integer.parseInt(request.getParameter("order_id"));
//			int component_id = Integer.parseInt(request.getParameter("component_id"));
//			int cust_id = Integer.parseInt(request.getParameter("cust_id"));
//			// If component doesn't exist in DB...
//			if(orderitemdao.checkExist(component_id) == false) {
//				// Find latest draft
//				int draft_id = orderdao.getDraftId(cust_id);
//				// Insert the component with qty=1 in draft
//				OrderItem orderitem = new OrderItem(draft_id, component_id, 1);
//				orderitemdao.add(orderitem);
//			}
//			else {	// If component already exist, addQuanntity
//				int qty = Integer.parseInt(request.getParameter("quantity"));
//				OrderItem orderitem = new OrderItem(order_id, component_id, qty);
//				orderitemdao.updateQuantity(orderitem);
//			}
//		}
		else if(action.equals("updateitem")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			int component_id = Integer.parseInt(request.getParameter("component_id"));
			int qty = Integer.parseInt(request.getParameter("quantity"));
			OrderItem orderitem = new OrderItem(order_id, component_id, qty);
			orderitemdao.updateQuantity(orderitem);
			return;
		}
		else if(action.equals("deleteitem")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			int component_id = Integer.parseInt(request.getParameter("component_id"));
			orderitemdao.deleteItemFromOrder(component_id, order_id);
			return;
		}
		else if(action.equals("deleteorder")) {
			int order_id = Integer.parseInt(request.getParameter("order_id"));
			String role = request.getParameter("role");
			orderdao.deleteOrder(order_id);
			forward = ORDERLIST_act+"&role="+role;
		}
		else if(action.equals("orderby")) {
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			String status = request.getParameter("status");
			Gson gson = new Gson();
			List<Orders> orders = orderdao.orderBy(status, staff_id);
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(orders));
			out.flush();
			out.close();
			return;
		}
		else {
			System.out.println("No action found");
			forward = MAIN;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
