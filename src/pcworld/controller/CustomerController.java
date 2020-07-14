package pcworld.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import pcworld.dao.CustomerDAO;
import pcworld.model.Customers;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String CUSTOMER_LOGIN = "cust-login.jsp";
	String CUSTOMER_ACCOUNT = "cust-account.jsp";
	String EDIT_ACCOUNT = "cust-editAccount.jsp";
	String MAIN = "main.jsp";
	String forward = "";
	
	CustomerDAO custdao = new CustomerDAO();
	
    public CustomerController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action cust : "+action);
		if(action.equals("view")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("customer", custdao.getCustomerById(id));
			forward = CUSTOMER_ACCOUNT;
		}
		else if(action.equals("showEdit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("customer", custdao.getCustomerById(id));
			forward = EDIT_ACCOUNT;
		}
		else if(action.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			
			Customers customer = new Customers(id, name, email, phone, password);
			
			customer = custdao.updateAccount(customer);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("sessionname", customer.getName());
			request.setAttribute("customer", custdao.getCustomerById(id));
			forward = CUSTOMER_ACCOUNT;
		}
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			custdao.deleteCustomer(id);
			
			//Logout the customer
			HttpSession session = request.getSession();
			session.removeAttribute("session");
			session.invalidate();
			forward = MAIN;
		}
		else {
			System.out.println("Action not found");
			forward = MAIN;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		Customers customer = new Customers(name, email, phone, password);
		
		//Guest -> Register new customer
		custdao.add(customer);
		
		forward = CUSTOMER_LOGIN;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
