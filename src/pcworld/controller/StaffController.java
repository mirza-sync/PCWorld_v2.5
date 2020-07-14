package pcworld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pcworld.dao.StaffDAO;
import pcworld.model.Staffs;

@WebServlet("/StaffController")
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String MAIN = "main.jsp";
	String STAFF_LIST = "admin-staffList.jsp";
	String STAFF_ACCOUNT = "staff-account.jsp";
	String EDIT_ACCOUNT = "admin-editAccount.jsp";
	String STAFF_LOGIN = "staff-login.jsp";
	String forward = "";
	
	StaffDAO staffdao = new StaffDAO();
	
    public StaffController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("viewlist")) {
			List<Staffs> staffs = new ArrayList<Staffs>();
			staffs = staffdao.getAllStaff();
			forward = STAFF_LIST;
			request.setAttribute("staffs", staffs);
		}
		else if(action.equals("view")) {
			int id = Integer.parseInt(request.getParameter("id"));
			forward = STAFF_ACCOUNT;
			request.setAttribute("staff", staffdao.getStaffById(id));
		}
		else if(action.equals("showEdit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			forward = EDIT_ACCOUNT;
			request.setAttribute("staff", staffdao.getStaffById(id));
		}
		else if(action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			staffdao.deleteStaff(id);
			forward = STAFF_LIST;
			request.setAttribute("staffs", staffdao.getAllStaff());
		}
		else {
			forward = MAIN;
			System.out.println("No action found!");
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String ic = request.getParameter("ic");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Staffs staff = new Staffs(ic, name, password);
		
		if(action.equals("add")) {
			//Register new staff
			staffdao.add(staff);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			staff.setId(id);
			staffdao.updateStaff(staff);
			HttpSession session = request.getSession(true);
			Staffs admin = (Staffs) session.getAttribute("session");
			admin = staffdao.getStaffById(admin.getId());
			session.setAttribute("sessionname", admin.getName());
		}
		forward = STAFF_LIST;
		request.setAttribute("staffs", staffdao.getAllStaff());
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}
