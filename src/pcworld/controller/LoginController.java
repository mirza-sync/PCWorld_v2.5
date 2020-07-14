package pcworld.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcworld.dao.CustomerDAO;
import pcworld.dao.StaffDAO;
import pcworld.model.Customers;
import pcworld.model.Staffs;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String MAIN = "main.jsp";
	String CUSTOMER_LOGIN = "cust-login.jsp";
	String forward = "";
	
	CustomerDAO custdao = new CustomerDAO();
	StaffDAO staffdao = new StaffDAO();
		
    public LoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String logtype = request.getParameter("type");
		HttpSession session = null;
		
		if(logtype.equals("customer")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Customers customer = new Customers(email, password);
			customer = custdao.login(customer);
			
			if(customer.isValid() == false) {
				forward = CUSTOMER_LOGIN;
			}
			else {
				session = request.getSession();
				customer.setRole("customer");
				session.setAttribute("session", customer);
				session.setAttribute("sessionname", customer.getName());
				forward = MAIN;
			}
		}
		else {
			String ic = request.getParameter("ic");
			String password = request.getParameter("password");
			Staffs staff = new Staffs(ic, password);
			staffdao.login(staff);
			
			if(staff.isValid() == false) {
				forward = CUSTOMER_LOGIN;
			}
			else {
				session = request.getSession();
				session.setAttribute("session", staff);
				session.setAttribute("sessionname", staff.getName());
				forward = MAIN;
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
