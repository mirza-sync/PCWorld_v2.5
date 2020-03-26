package pcworld.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pcworld.dao.CasingDAO;
import pcworld.dao.ComponentDAO;
import pcworld.dao.CoolerDAO;
import pcworld.dao.CpuDAO;
import pcworld.dao.GpuDAO;
import pcworld.dao.MotherboardDAO;
import pcworld.dao.PsuDAO;
import pcworld.dao.RamDAO;
import pcworld.dao.StorageDAO;
import pcworld.model.CPU;
import pcworld.model.Casing;
import pcworld.model.Components;
import pcworld.model.Cooler;
import pcworld.model.GPU;
import pcworld.model.Input;
import pcworld.model.Motherboard;
import pcworld.model.PSU;
import pcworld.model.RAM;
import pcworld.model.Storage;

@WebServlet("/ComponentController")
public class ComponentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String VIEW_COMPONENT = "viewComponent.jsp";
	String UPDATE_FORM = "updateComponent.jsp";
	
    
	ComponentDAO compdao = new ComponentDAO();
	CpuDAO cpudao = new CpuDAO();
	GpuDAO gpudao = new GpuDAO();
	MotherboardDAO mobodao = new MotherboardDAO();
	RamDAO ramdao = new RamDAO();
	StorageDAO storagedao = new StorageDAO();
	PsuDAO psudao = new PsuDAO();
	CasingDAO casingdao = new CasingDAO();
	CoolerDAO coolerdao = new CoolerDAO();
	
	String type;	
	String forward = "";
	
    public ComponentController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//View component
		if(action.equalsIgnoreCase("viewComponent")) {
			System.out.println("Viewing...");
			forward = VIEW_COMPONENT;
			request.setAttribute("cpus", cpudao.getAllCpu());
			request.setAttribute("gpus", gpudao.getAllGpu());
			request.setAttribute("mobos", mobodao.getAllMobo());
			request.setAttribute("rams", ramdao.getAllRam());
			request.setAttribute("stors", storagedao.getAllStorage());
			request.setAttribute("psus", psudao.getAllPsu());
			request.setAttribute("cases", casingdao.getAllCasing());
		}
		//Show add component form
		else if(action.equalsIgnoreCase("showAddComp")) {
			request.setAttribute("comps", compdao.getAllComponentByType(type)); 
		}
		//Show update form
		else if(action.equalsIgnoreCase("showUpdateComp")) {
			int id = Integer.parseInt(request.getParameter("id"));
			forward = UPDATE_FORM;
			request.setAttribute("comps", compdao.getComponentById(id)); 
		}
		//Delete component
		else if(action.equalsIgnoreCase("deleteComp")) {
			int id = Integer.parseInt(request.getParameter("id"));
			compdao.deleteComponent(id); 
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

		response.getWriter().append("Server at : ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String brand, model, image, type;
		int comp_id;
		double price;
		
		String action = request.getParameter("action");

		brand = request.getParameter("brand");
		model = request.getParameter("model");
		price = Double.parseDouble(request.getParameter("price"));
		image = request.getParameter("image");
		type = request.getParameter("type");
		
		System.out.println("Action is : " + action);
		System.out.println("Brand is : " + brand);
		System.out.println("Model is : " + model);
		System.out.println("Price is : " + price);
		System.out.println("Type is : " + type);
		
		//Add component
		if(action.equalsIgnoreCase("add")) {
			
			Components comp = new Components(brand, model, price, image, type);
			comp_id = compdao.add(comp);
			
			System.out.println("Id is : " + comp_id);
			System.out.println("Type is : " + comp.getType());

			if (comp.getType().equalsIgnoreCase("cpu")) {
				String socket = request.getParameter("cpu_socket");
				int clock_speed = Integer.parseInt(request.getParameter("clock_speed"));
				int num_core = Integer.parseInt(request.getParameter("num_core"));
				int watt = Integer.parseInt(request.getParameter("cpu_watt"));
				CPU cpu = new CPU(comp_id, socket, clock_speed, num_core, watt);
				cpudao.add(cpu);
			}
			else if (comp.getType().equalsIgnoreCase("gpu")){
				String chipset = request.getParameter("chipset");
				int num_vram = Integer.parseInt(request.getParameter("num_vram"));
				String vram_type = request.getParameter("vram_type");
				int length = Integer.parseInt(request.getParameter("gpu_length"));
				int width = Integer.parseInt(request.getParameter("gpu_width"));
				int height = Integer.parseInt(request.getParameter("gpu_height"));
				String color = request.getParameter("gpu_color");
				int core_clock = Integer.parseInt(request.getParameter("core_clock"));
				int watt = Integer.parseInt(request.getParameter("gpu_watt"));
				GPU gpu = new GPU(comp_id, chipset, num_vram, vram_type, length, width, height, color, core_clock, watt);
				gpudao.add(gpu);
			}
			else if (comp.getType().equalsIgnoreCase("mobo")){
				String form = request.getParameter("mobo_form");
				int length = Integer.parseInt(request.getParameter("mobo_length"));
				int width = Integer.parseInt(request.getParameter("mobo_width"));
				int height = Integer.parseInt(request.getParameter("mobo_height"));
				String socket = request.getParameter("mobo_socket");
				String memory_type = request.getParameter("mem_type");
				int memory_slot = Integer.parseInt(request.getParameter("mem_slot"));
				int max_memory = Integer.parseInt(request.getParameter("max_mem"));
				Motherboard mobo = new Motherboard(comp_id, form, length, width, height, socket, memory_type, memory_slot, max_memory, color);
				mobodao.add(mobo);
			}
			else if(comp.getType().equalsIgnoreCase("ram")){
				int capacity = Integer.parseInt(request.getParameter("ram_capacity"));
				String ram_type = request.getParameter("ram_type");
				int speed = Integer.parseInt(request.getParameter("speed"));
				RAM ram = new RAM(comp_id, capacity, ram_type, speed);
				ramdao.add(ram);
			}
			else if (comp.getType().equalsIgnoreCase("storage")){
				String storage_type = request.getParameter("storage_type");
				int capacity = Integer.parseInt(request.getParameter("storage_capacity"));
				String form = request.getParameter("storage_form");
				int read_speed = Integer.parseInt(request.getParameter("r_speed"));
				int write_speed = Integer.parseInt(request.getParameter("w_speed"));
				Storage storage = new Storage(comp_id, storage_type, capacity, form, read_speed, write_speed);
				storagedao.add(storage);
			}
			else if (comp.getType().equalsIgnoreCase("psu")){
				int wattage = Integer.parseInt(request.getParameter("psu_wattage"));
				String psu_type = request.getParameter("psu_type");
				String efficiency = request.getParameter("efficiency");
				String color = request.getParameter("psu_color");
				PSU psu = new PSU(comp_id, wattage, psu_type, efficiency, color);
				psudao.add(psu);
			}
			else if (comp.getType().equalsIgnoreCase("cooler")){
				String cooler_type = request.getParameter("cooler_type");
				int length = Integer.parseInt(request.getParameter("cooler_length"));
				int width = Integer.parseInt(request.getParameter("cooler_width"));
				int height = Integer.parseInt(request.getParameter("cooler_height"));
				String color = request.getParameter("cooler_color");
				Cooler cooler = new Cooler(comp_id, cooler_type, length, width, height, color);
				coolerdao.add(cooler);
			}
			else if (comp.getType().equalsIgnoreCase("casing")){
				String form = request.getParameter("case_form");
				int length = Integer.parseInt(request.getParameter("case_length"));
				int width = Integer.parseInt(request.getParameter("case_width"));
				int height = Integer.parseInt(request.getParameter("case_height"));
				String color = request.getParameter("case_color");
				Casing casing = new Casing(comp_id, form, length, width, height, color);
				casingdao.add(casing);
			}
			forward = VIEW_COMPONENT;
			request.setAttribute("comps", compdao.getAllComponent());
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}

//		else if(action.equalsIgnoreCase("update")){
//			rph_id = Integer.parseInt(request.getParameter("rph_id"));
//			RPHS rph = new RPHS(rph_id, rph_date, rph_time, rph_type, rph_submitStatus, teacher_id, subject_id);
//			rphdao.updateRph(rph);
//			System.out.println("Do update");
//			if (rph.getRph_type().equalsIgnoreCase("Lessons")) {
//				String topic_name = request.getParameter("topic_name");
//				String rph_activities = request.getParameter("rph_activities");
//				int rph_numstudents = Integer.parseInt(request.getParameter("rph_numstudents"));
//				String rph_moralvalue = request.getParameter("rph_moralvalue");
//				String rph_kbat = request.getParameter("rph_kbat");
//				String rph_bbb = request.getParameter("rph_bbb");
//				Lessons lesson = new Lessons(rph_id, rph_date, rph_time, rph_type, rph_submitStatus, teacher_id, subject_id, topic_name, rph_activities, rph_numstudents, rph_moralvalue,rph_kbat,rph_bbb);
//				lessondao.updateLesson(lesson);
//				
////				request.setAttribute("lessons", lessondao.getLessonById(rph));
////				forward = REDIRECT_VIEW+rph_id;
////				RequestDispatcher view = request.getRequestDispatcher(forward);
////				view.forward(request, response);
//			} 
//			else if (rph.getRph_type().equalsIgnoreCase("Events")) {
//				String event_description = request.getParameter("event_description");
//				Events event = new Events(rph_id, rph_date, rph_time, rph_type, rph_submitStatus, teacher_id, subject_id, event_description);
//				eventdao.updateEvent(event);
//				
////				request.setAttribute("events", eventdao.getEventById(rph));
////				forward = REDIRECT_VIEW+rph_id;
////				RequestDispatcher view = request.getRequestDispatcher(forward);
////				view.forward(request, response);
//			}
//			forward = VIEW_OWN_RPH;
//			request.setAttribute("rphs", rphdao.getAllRphByTeacher(teacher_id));
//			RequestDispatcher view = request.getRequestDispatcher(forward);
//			view.forward(request, response);
//		}
		else if(action.equalsIgnoreCase("recommend")) {
			System.out.println("Recommending...");
			int budget;
			String usage, size, style;
			
//			budget = Integer.parseInt(request.getParameter("budget"));
//			usage = request.getParameter("usage");
//			size = request.getParameter("size");
//			style = request.getParameter("style");
//			
//			Input input = new Input(budget, usage, size, style);
			Input input = new Input(1000, "office", "big", "gamer");
			System.out.println("Input initialized...");
			
			forward = "main.jsp";
			request.setAttribute("comps", compdao.recommendPC(input));
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}

}
