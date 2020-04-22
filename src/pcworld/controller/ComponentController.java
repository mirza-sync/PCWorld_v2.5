package pcworld.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
@MultipartConfig
public class ComponentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="img";
	
	String MAIN = "main.jsp";
	String ADD_COMPONENT = "addComponent.jsp";
	String VIEW_COMPONENTS = "viewAllComponents.jsp";
	String UPDATE_FORM = "updateComponent.jsp";
	String TEST_FORM = "testEdit.jsp";
	
    
	ComponentDAO compdao = new ComponentDAO();
	CpuDAO cpudao = new CpuDAO();
	GpuDAO gpudao = new GpuDAO();
	MotherboardDAO mobodao = new MotherboardDAO();
	RamDAO ramdao = new RamDAO();
	StorageDAO storagedao = new StorageDAO();
	PsuDAO psudao = new PsuDAO();
	CasingDAO casingdao = new CasingDAO();
	CoolerDAO coolerdao = new CoolerDAO();
	
	String forward = "";
	
    public ComponentController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("Get action : "+action);
		
		//View component
		if(action.equalsIgnoreCase("viewComponents")) {
			System.out.println("Viewing...");
			String type = request.getParameter("type");
			
			forward = VIEW_COMPONENTS;
			request.setAttribute("type", type);
			
			if(type.equals("CPU")) {
				request.setAttribute("cpus", cpudao.getAllCpu());
			}
			else if(type.equals("GPU")) {			
				request.setAttribute("gpus", gpudao.getAllGpu());
			}
			else if(type.equals("Motherboard")) {
				request.setAttribute("mobos", mobodao.getAllMobo());
			}
			else if(type.equals("RAM")) {
				request.setAttribute("rams", ramdao.getAllRam());
			}
			else if(type.equals("Storage")) {
				request.setAttribute("stors", storagedao.getAllStorage());
			}
			else if(type.equals("PSU")) {
				request.setAttribute("psus", psudao.getAllPsu());
			}
			else if(type.equals("Casing")) {
				request.setAttribute("cases", casingdao.getAllCasing());
			}
			else {
				System.out.println("Component type not found.");
			}
		}
		else if(action.equalsIgnoreCase("viewCompById")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			forward = UPDATE_FORM;
			if(type.equals("CPU")) {
				request.setAttribute("cpu", cpudao.getCpuById(id));
			}
			else if(type.equals("GPU")) {			
				request.setAttribute("gpu", gpudao.getGpuById(id));
			}
			else if(type.equals("Motherboard")) {
				request.setAttribute("mobo", mobodao.getMotherboardById(id));
			}
			else if(type.equals("RAM")) {
				request.setAttribute("ram", ramdao.getRamById(id));
			}
			else if(type.equals("Storage")) {
				request.setAttribute("stor", storagedao.getStorageById(id));
			}
			else if(type.equals("PSU")) {
				request.setAttribute("psu", psudao.getPsuById(id));
			}
			else if(type.equals("Casing")) {
				request.setAttribute("case", casingdao.getCasingById(id));
			}
			else {
				System.out.println("Component not found.");
			}
		}
		//Show update form
		else if(action.equalsIgnoreCase("showEdit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			//forward = UPDATE_FORM;
			forward = TEST_FORM;
			request.setAttribute("comp", compdao.getComponentById(id));
			if(type.equals("CPU")) {
				request.setAttribute("cpu", cpudao.getCpuById(id));
			}
			else if(type.equals("GPU")) {			
				request.setAttribute("gpu", gpudao.getGpuById(id));
			}
			else if(type.equals("Motherboard")) {
				request.setAttribute("mobo", mobodao.getMotherboardById(id));
			}
			else if(type.equals("RAM")) {
				request.setAttribute("ram", ramdao.getRamById(id));
			}
			else if(type.equals("Storage")) {
				request.setAttribute("stor", storagedao.getStorageById(id));
			}
			else if(type.equals("PSU")) {
				request.setAttribute("psu", psudao.getPsuById(id));
			}
			else if(type.equals("Casing")) {
				request.setAttribute("case", casingdao.getCasingById(id));
			}
			else {
				System.out.println("Component not found.");
			}
		}
		//Delete component
		else if(action.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			compdao.deleteComponent(id); 
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

		response.getWriter().append("Server at : ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action is : " + action);
		
		if(action.equalsIgnoreCase("recommend")) {
			System.out.println("Recommending...");
			double budget;
			String size, style, color;
			
			budget = Double.parseDouble(request.getParameter("budget"));
			String[] usage = request.getParameterValues("c_usage");
			style = request.getParameter("r_style");
			color = request.getParameter("r_color");
			
			Input input = new Input(budget, usage, style, color);
			
			forward = "recommend2.jsp";
			request.setAttribute("pc", compdao.recommendPC(input));
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
			return;
		}
		
		String brand, model, type, imageName, imageLast;
		int comp_id;
		double price;
		Part image;
		
		image = request.getPart("image");
		if(image != null) {
			// gets absolute path of the web application
	        String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + SAVE_DIR;
	        
	        File fileSaveDir=new File(savePath);
	        if(!fileSaveDir.exists()){
	            fileSaveDir.mkdir();
	        }
			imageName=extractFileName(image);
			
			
			image.write(savePath + File.separator + imageName);
			imageLast = imageName;
			System.out.println("SAVE PATH is : " + savePath);
		}
		else {
			imageLast = "noimage.png";
		}
		brand = request.getParameter("brand");
		model = request.getParameter("model");
		price = Double.parseDouble(request.getParameter("price"));
		type = request.getParameter("type");
		
		System.out.println("Brand is : " + brand);
		System.out.println("Model is : " + model);
		System.out.println("Price is : " + price);
		System.out.println("Image name is : " + imageLast);
		System.out.println("Type is : " + type);
		
		//Add component
		if(action.equalsIgnoreCase("add")) {	
			Components comp = new Components(brand, model, price, imageLast, type);
			comp_id = compdao.add(comp);
			
			System.out.println("New Id is : " + comp_id);

			if (comp.getType().equalsIgnoreCase("CPU")) {
				String socket = request.getParameter("cpu_socket");
				float base_clock = Float.parseFloat(request.getParameter("base_clock"));
				float max_clock = Float.parseFloat(request.getParameter("max_clock"));
				int num_core = Integer.parseInt(request.getParameter("num_core"));
				int thread = Integer.parseInt(request.getParameter("thread"));
				int watt = Integer.parseInt(request.getParameter("cpu_watt"));
				CPU cpu = new CPU(comp_id, socket, base_clock, max_clock, num_core, thread, watt);
				cpudao.add(cpu);
			}
			else if (comp.getType().equalsIgnoreCase("GPU")){
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
			else if (comp.getType().equalsIgnoreCase("Motherboard")){
				String form = request.getParameter("mobo_form");
				int length = Integer.parseInt(request.getParameter("mobo_length"));
				int width = Integer.parseInt(request.getParameter("mobo_width"));
				int height = Integer.parseInt(request.getParameter("mobo_height"));
				String socket = request.getParameter("mobo_socket");
				String memory_type = request.getParameter("mem_type");
				int memory_slot = Integer.parseInt(request.getParameter("mem_slot"));
				int max_memory = Integer.parseInt(request.getParameter("max_mem"));
				String color = request.getParameter("mobo_color");
				Motherboard mobo = new Motherboard(comp_id, form, length, width, height, socket, memory_type, memory_slot, max_memory, color);
				mobodao.add(mobo);
			}
			else if(comp.getType().equalsIgnoreCase("RAM")){
				int capacity = Integer.parseInt(request.getParameter("ram_capacity"));
				String ram_type = request.getParameter("ram_type");
				int speed = Integer.parseInt(request.getParameter("speed"));
				int module = Integer.parseInt(request.getParameter("module"));
				String color = request.getParameter("ram_color");
				RAM ram = new RAM(comp_id, capacity, ram_type, speed, module, color);
				ramdao.add(ram);
			}
			else if (comp.getType().equalsIgnoreCase("Storage")){
				System.out.println("ID in controller: "+comp_id);
				String storage_type = request.getParameter("storage_type");
				int capacity = Integer.parseInt(request.getParameter("storage_capacity"));
				String form = request.getParameter("storage_form");
				int read_speed = Integer.parseInt(request.getParameter("r_speed"));
				int write_speed = Integer.parseInt(request.getParameter("w_speed"));
				Storage storage = new Storage(comp_id, storage_type, capacity, form, read_speed, write_speed);
				System.out.println("Storage ID before passed : "+storage.getId());
				storagedao.add(storage);
			}
			else if (comp.getType().equalsIgnoreCase("PSU")){
				int wattage = Integer.parseInt(request.getParameter("psu_wattage"));
				String psu_type = request.getParameter("psu_type");
				String efficiency = request.getParameter("efficiency");
				String color = request.getParameter("psu_color");
				PSU psu = new PSU(comp_id, wattage, psu_type, efficiency, color);
				psudao.add(psu);
			}
			else if (comp.getType().equalsIgnoreCase("Cooler")){
				String cooler_type = request.getParameter("cooler_type");
				int length = Integer.parseInt(request.getParameter("cooler_length"));
				int width = Integer.parseInt(request.getParameter("cooler_width"));
				int height = Integer.parseInt(request.getParameter("cooler_height"));
				String color = request.getParameter("cooler_color");
				Cooler cooler = new Cooler(comp_id, cooler_type, length, width, height, color);
				coolerdao.add(cooler);
			}
			else if (comp.getType().equalsIgnoreCase("Casing")){
				String form = request.getParameter("case_form");
				int length = Integer.parseInt(request.getParameter("case_length"));
				int width = Integer.parseInt(request.getParameter("case_width"));
				int height = Integer.parseInt(request.getParameter("case_height"));
				String color = request.getParameter("case_color");
				Casing casing = new Casing(comp_id, form, length, width, height, color);
				casingdao.add(casing);
			}
			forward = ADD_COMPONENT;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("edit")) {
			comp_id = Integer.parseInt(request.getParameter("id"));
			
			System.out.println("Component ID is : " + comp_id);
			
			Components comp = new Components(comp_id, brand, model, price, imageLast, type);
			compdao.update(comp);

			if (comp.getType().equalsIgnoreCase("CPU")) {
				String socket = request.getParameter("cpu_socket");
				float base_clock = Float.parseFloat(request.getParameter("base_clock"));
				float max_clock = Float.parseFloat(request.getParameter("max_clock"));
				int num_core = Integer.parseInt(request.getParameter("num_core"));
				int thread = Integer.parseInt(request.getParameter("thread"));
				int watt = Integer.parseInt(request.getParameter("cpu_watt"));
				CPU cpu = new CPU(comp_id, socket, base_clock, max_clock, num_core, thread, watt);
				cpudao.update(cpu);
			}
			else if (comp.getType().equalsIgnoreCase("GPU")){
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
				gpudao.update(gpu);
			}
			else if (comp.getType().equalsIgnoreCase("Motherboard")){
				String form = request.getParameter("mobo_form");
				int length = Integer.parseInt(request.getParameter("mobo_length"));
				int width = Integer.parseInt(request.getParameter("mobo_width"));
				int height = Integer.parseInt(request.getParameter("mobo_height"));
				String socket = request.getParameter("mobo_socket");
				String memory_type = request.getParameter("mem_type");
				int memory_slot = Integer.parseInt(request.getParameter("mem_slot"));
				int max_memory = Integer.parseInt(request.getParameter("max_mem"));
				String color = request.getParameter("mobo_color");
				Motherboard mobo = new Motherboard(comp_id, form, length, width, height, socket, memory_type, memory_slot, max_memory, color);
				mobodao.update(mobo);
			}
			else if(comp.getType().equalsIgnoreCase("RAM")){
				int capacity = Integer.parseInt(request.getParameter("ram_capacity"));
				String ram_type = request.getParameter("ram_type");
				int speed = Integer.parseInt(request.getParameter("speed"));
				int module = Integer.parseInt(request.getParameter("module"));
				String color = request.getParameter("ram_color");
				RAM ram = new RAM(comp_id, capacity, ram_type, speed, module, color);
				ramdao.update(ram);
			}
			else if (comp.getType().equalsIgnoreCase("Storage")){
				String storage_type = request.getParameter("storage_type");
				int capacity = Integer.parseInt(request.getParameter("storage_capacity"));
				String form = request.getParameter("storage_form");
				int read_speed = Integer.parseInt(request.getParameter("r_speed"));
				int write_speed = Integer.parseInt(request.getParameter("w_speed"));
				Storage storage = new Storage(comp_id, storage_type, capacity, form, read_speed, write_speed);
				storagedao.update(storage);
			}
			else if (comp.getType().equalsIgnoreCase("PSU")){
				int wattage = Integer.parseInt(request.getParameter("psu_wattage"));
				String psu_type = request.getParameter("psu_type");
				String efficiency = request.getParameter("efficiency");
				String color = request.getParameter("psu_color");
				PSU psu = new PSU(comp_id, wattage, psu_type, efficiency, color);
				psudao.update(psu);
			}
			else if (comp.getType().equalsIgnoreCase("Cooler")){
				String cooler_type = request.getParameter("cooler_type");
				int length = Integer.parseInt(request.getParameter("cooler_length"));
				int width = Integer.parseInt(request.getParameter("cooler_width"));
				int height = Integer.parseInt(request.getParameter("cooler_height"));
				String color = request.getParameter("cooler_color");
				Cooler cooler = new Cooler(comp_id, cooler_type, length, width, height, color);
				coolerdao.update(cooler);
			}
			else if (comp.getType().equalsIgnoreCase("Casing")){
				String form = request.getParameter("case_form");
				int length = Integer.parseInt(request.getParameter("case_length"));
				int width = Integer.parseInt(request.getParameter("case_width"));
				int height = Integer.parseInt(request.getParameter("case_height"));
				String color = request.getParameter("case_color");
				Casing casing = new Casing(comp_id, form, length, width, height, color);
				casingdao.update(casing);
			}
			forward = MAIN;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}
