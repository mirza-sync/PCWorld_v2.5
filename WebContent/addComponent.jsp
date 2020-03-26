<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<title>Add Component</title>
<%@include file="header.jsp"%>
</head>
<body>
<div class="container my-5">
	<div class="row">
		<div class="col-md-8 offset-md-2">
			<div class="card">
				<div class="card-header">
					<h5>Add Component</h5>
				</div>
				<form method="POST" action="ComponentController" enctype="multipart/form-data">
					<input type="hidden" name="action" value="add"/>
					<div class="card-body">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Type</label>
							<div class="col-md-9">
								<select id="opts" name="component_type" class="form-control" onchange="showForm()">
									<option value="">Select Component Type</option>
									<option value="cpu">CPU</option>
									<option value="gpu">GPU</option>
									<option value="mobo">Motherboard</option>
									<option value="ram">RAM</option>
									<option value="storage">Storage</option>
									<option value="psu">PSU</option>
									<option value="cooler">Cooler</option>
									<option value="casing">Casing</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Brand</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="brand"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Model</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="model"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Price</label>
							<div class="col-md-9">
								<input type="number" class="form-control" name="price"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Image</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="image"/>
							</div>
						</div>
						<!-- Hidden forms inside card body-->
						<div id="f1" style="display: none">
							<h4 class="mb-2">Processor</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<input type="text" class="form-control" name="cpu_socket" placeholder="Enter socket type">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Clock Speed</h6>
										<input type="text" class="form-control" name="clock_speed" placeholder="Enter clock speed">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Num. of Cores</h6>
										<input type="text" class="form-control" name="num_core" placeholder="Enter number of cores">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Power Usage</h6>
										<input type="number" class="form-control" name="cpu_watt" placeholder="Enter power in watt">
									</div>
								</li>
							</ul>
						</div> <!--Close CPU-->
						<div id="f2" style="display: none">
							<h4 class="mb-2">Graphics Card</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Chipset</h6>
										<input type="text" class="form-control" name="chipset" placeholder="NVIDIA / AMD">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of VRAM</h6>
										<input type="text" class="form-control" name="vram_num" placeholder="Enter Number of VRAM">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Type of VRAM</h6>
										<input type="text" class="form-control" name="vram_type" placeholder="DDR4, DDR5, etc">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Length</h6>
										<input type="text" class="form-control" name="gpu_length" placeholder="Enter length">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Width</h6>
										<input type="text" class="form-control" name="gpu_width" placeholder="Enter width">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Height</h6>
										<input type="text" class="form-control" name="gpu_height" placeholder="Enter height">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="gpu_color" placeholder="Enter color">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Core clock</h6>
										<input type="text" class="form-control" name="core_clock" placeholder="Enter core clock">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Wattage</h6>
										<input type="text" class="form-control" name="gpu_watt" placeholder="Enter power in watt">
									</div>
								</li>
							</ul>
						</div> <!--Close GPU-->
						<div id="f3" style="display: none">
							<h4 class="mb-2">Motherboard</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="form" name="mobo_form" class="form-control">
											<option value="ATX">ATX</option>
											<option value="Micro ATX">Micro ATX</option>
											<option value="Mini ITX">Mini ITX</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Length</h6>
										<input type="text" class="form-control" name="mobo_length" placeholder="Enter length">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Width</h6>
										<input type="text" class="form-control" name="mobo_width" placeholder="Enter width">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Height</h6>
										<input type="text" class="form-control" name="mobo_height" placeholder="Enter height">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<input type="text" class="form-control" name="mobo_socket" placeholder="AM4, LG1150, etc">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Supported Memory Type</h6>
										<input type="text" class="form-control" name="mem_type" placeholder="Enter supported memory">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Memory Slot</h6>
										<input type="text" class="form-control" name="mem_slot" placeholder="Enter number of RAM slot">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Maximum Memory</h6>
										<input type="text" class="form-control" name="max_mem" placeholder="Enter maximum supported memory">
									</div>
								</li>
							</ul>
						</div> <!--Close Mobo-->
						<div id="f4" style="display: none">
							<h4 class="mb-2">RAM</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<input type="text" class="form-control" name="ram_capacity" placeholder="Enter RAM capacity">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Type</h6>
										<input type="text" class="form-control" name="ram_type" placeholder="DDR3, DDR4, DDR5, etc">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Speed</h6>
										<input type="text" class="form-control" name="speed" placeholder="Enter RAM speed">
									</div>
								</li>
							</ul>
						</div> <!--Close RAM-->
						<div id="f5" style="display: none">
							<h4 class="mb-2">Storage</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Storage Type</h6>
										<select id="s_type" name="s_tpye" class="form-control">
											<option value="HDD">HDD</option>
											<option value="SSD">SSD</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<input type="text" class="form-control" name="storage_capacity" placeholder="Enter Storage capacity">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="storage_form" name="storage_form" class="form-control">
											<option value="2.5 inch">2.5 inch</option>
											<option value="3.5 inch">3.5 inch</option>
											<option value="M.2 SATA">M.2 SATA</option>
											<option value="M.2 NVME">M.2 NVME</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Read Speed</h6>
										<input type="text" class="form-control" name="r_speed" placeholder="Enter read speed">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Write Speed</h6>
										<input type="text" class="form-control" name="w_speed" placeholder="Enter write speed">
									</div>
								</li>
							</ul>
						</div> <!--Close Storage-->
						<div id="f6" style="display: none">
							<h4 class="mb-2">Power Supply</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Wattage</h6>
										<input type="text" class="form-control" name="psu_wattage" placeholder="Enter wattage">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>PSU type</h6>
										<select id="psu_type" name="psu_type" class="form-control">
											<option value="Non Modular">Non Modular</option>
											<option value="Semi Modular">Semi Modular</option>
											<option value="Modular">Modular</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Efficiency Rating</h6>
										<select id="efficiency" name="efficiency" class="form-control">
											<option value="Bronze">Bronze</option>
											<option value="Silver">Silver</option>
											<option value="Gold">Gold</option>
											<option value="Platinum">Platinum</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="psu_color" placeholder="Enter color">
									</div>
								</li>
							</ul>
						</div> <!--Close PSU-->
						<div id="f7" style="display: none">
							<h4 class="mb-2">Cooler</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Cooler Type</h6>
										<select id="cooler_type" name="cooler_type" class="form-control">
											<option value="Air Cooler">Air Cooler</option>
											<option value="AIO Liquid Cooler">AIO Liquid Cooler</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Length</h6>
										<input type="text" class="form-control" name="cooler_length" placeholder="Enter length">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Width</h6>
										<input type="text" class="form-control" name="cooler_width" placeholder="Enter width">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Height</h6>
										<input type="text" class="form-control" name="cooler_height" placeholder="Enter height">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="cooler_color" placeholder="Enter color">
									</div>
								</li>
							</ul>
						</div> <!--Close Cooler-->
						<div id="f8" style="display: none">
							<h4 class="mb-2">Casing</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="case_type" name="case_type" class="form-control">
											<option value="ATX">ATX</option>
											<option value="Micro ATX">Micro ATX</option>
											<option value="Mini ITX">Mini ITX</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Length</h6>
										<input type="text" class="form-control" name="case_length" placeholder="Enter length">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Width</h6>
										<input type="text" class="form-control" name="case_width" placeholder="Enter width">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Height</h6>
										<input type="text" class="form-control" name="case_height" placeholder="Enter height">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="case_color" placeholder="Enter color">
									</div>
								</li>
							</ul>
						</div> <!--Close Casing-->
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" name="action" value="add" type="submit">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function showForm() {
	var selopt = document.getElementById("opts").value;
	if (selopt == "cpu") {
		document.getElementById("f1").style.display = "block";
		document.getElementById("f2").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "gpu") {
		document.getElementById("f1").style.display = "none";
		document.getElementById("f2").style.display = "block";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "mobo") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "block";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "ram") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "block";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "storage") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "block";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "psu") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "block";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "cooler") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "block";
		document.getElementById("f8").style.display = "none";
	}
	if (selopt == "casing") {
		document.getElementById("f2").style.display = "none";
		document.getElementById("f1").style.display = "none";
		document.getElementById("f3").style.display = "none";
		document.getElementById("f4").style.display = "none";
		document.getElementById("f5").style.display = "none";
		document.getElementById("f6").style.display = "none";
		document.getElementById("f7").style.display = "none";
		document.getElementById("f8").style.display = "block";
	}
}
</script>
</body>
</html>