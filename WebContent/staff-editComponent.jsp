<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Edit Component</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card">
			<div class="card-header">
				<h5>Edit Component</h5>
			</div>
			<!-- <form method="POST" action="ComponentController" enctype="multipart/form-data"> -->
			<form method="POST" action="ComponentController">
				<input type="hidden" name="action" value="edit"/>
				<input type="hidden" name="id" value="${comp.id}"/>
				<div class="card-body">
					<div class="row d-flex align-items-center">
						<div class="col-md-3">
							<img src="${comp.image}" style="width:200px; height:200px;"/>
						</div>
						<div class="col-md-9 pl-4">
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Type</label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext pl-2" name="type" value="${comp.type}" readonly/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Brand</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="brand" value="${comp.brand}"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Model</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="model" value="${comp.model}"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Price</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="price" value="${comp.price}"/>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									<label class="col-form-label">Image</label>
								</div>
								<div class="col-md-10">
									<input type="text" class="form-control" name="image" value="${comp.image}"/>
								</div>
							</div>
						</div>
					</div>
					<c:choose>
					<c:when test="${comp.type == 'CPU'}">
						<div> <!-- Start CPU -->
							<h4 class="mb-2">Processor</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<input type="text" class="form-control" name="cpu_socket" value="${cpu.socket}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Base Clock</h6>
										<input type="number" class="form-control" name="base_clock" value="${cpu.base_clock}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Max Clock</h6>
										<input type="number" class="form-control" name="max_clock" value="${cpu.max_clock}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of Cores</h6>
										<input type="number" class="form-control" name="num_core" value="${cpu.num_core}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Multi-Thread</h6>
										<select id="multithread" name="multithread" class="form-control">
											<option value="${cpu.multithread}">
												<c:choose>
													<c:when test="${cpu.multithread == 1}">
														Yes
													</c:when>
													<c:otherwise>
														No
													</c:otherwise>
												</c:choose>
											</option>
											<option value="1">Yes</option>
											<option value="0">No</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Power Usage</h6>
										<input type="number" class="form-control" name="cpu_watt" value="${cpu.wattage}">
									</div>
								</li>
							</ul>
						</div> <!--End CPU-->
					</c:when>
					<c:when test="${comp.type == 'GPU'}">
						<div>
							<h4 class="mb-2">Graphics Card</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Chipset</h6>
										<input type="text" class="form-control" name="chipset" value="${gpu.chipset}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of VRAM</h6>
										<input type="text" class="form-control" name="num_vram" value="${gpu.num_vram}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Core clock</h6>
										<input type="text" class="form-control" name="core_clock" value="${gpu.core_clock}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="gpu_color" value="${gpu.color}">
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Motherboard'}">
						<div>
							<h4 class="mb-2">Motherboard</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="form" name="mobo_form" class="form-control">
											<option value="${mobo.formfactor}"><c:out value="${mobo.formfactor}" /></option>
											<option value="ATX">ATX</option>
											<option value="Micro ATX">Micro ATX</option>
											<option value="Mini ITX">Mini ITX</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<input type="text" class="form-control" name="mobo_socket" value="${mobo.socket}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Memory Slot</h6>
										<input type="number" class="form-control" name="mem_slot" value="${mobo.memory_slot}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Maximum Memory</h6>
										<input type="number" class="form-control" name="max_mem" value="${mobo.max_memory}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="mobo_color" value="${mobo.color}">
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'RAM'}">
						<div>
							<h4 class="mb-2">RAM</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<input type="number" class="form-control" name="ram_capacity" value="${ram.capacity}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Type</h6>
										<input type="text" class="form-control" name="ram_type" value="${ram.ram_type}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of Module</h6>
										<input type="text" class="form-control" name="module" value="${ram.module}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Speed</h6>
										<input type="number" class="form-control" name="speed" value="${ram.speed}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="ram_color" value="${ram.color}">
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Storage'}">
						<div>
							<h4 class="mb-2">Storage</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Storage Type</h6>
										<select id="storage_type" name="storage_tpye" class="form-control">
											<option value="${stor.storage_type}"><c:out  value="${stor.storage_type}"/></option>
											<option value="5400RPM">5400RPM</option>
											<option value="7200RPM">7200RPM</option>
											<option value="Hybrid">Hybrid</option>
											<option value="SSD">SSD</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<input type="text" class="form-control" name="storage_capacity" value="${stor.capacity}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="storage_form" name="storage_form" class="form-control">
											<option value="${stor.form}"><c:out value="${stor.form}"/></option>
											<option value='2.5"'>2.5 inch</option>
											<option value='3.5"'>3.5 inch</option>
											<option value="M.2-2280">M.2-2280</option>
											<option value="PCI-E">PCI-E</option>
										</select>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'PSU'}">
						<div>
							<h4 class="mb-2">Power Supply</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Wattage</h6>
										<input type="number" class="form-control" name="psu_wattage" value="${psu.wattage}">
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>PSU type</h6>
										<select id="modularity" name="modularity" class="form-control">
											<option hidden="true" value="${psu.modularity}"><c:out value="${psu.modularity}"/></option>
											<option value="No">No</option>
											<option value="Semi">Semi</option>
											<option value="Full">Full</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Efficiency Rating</h6>
										<select id="efficiency" name="efficiency" class="form-control">
											<option value="${psu.efficiency}"><c:out value="${psu.efficiency}"/></option>
											<option value="80+">80+</option>
											<option value="80+ Bronze">80+ Bronze</option>
											<option value="80+ Silver">80+Silver</option>
											<option value="80+ Gold">80+ Gold</option>
											<option value="80+ Platinum">80+ Platinum</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="psu_color" value="${psu.color}">
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Casing'}">
						<div>
							<h4 class="mb-2">Casing</h4>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<select id="case_form" name="case_form" class="form-control">
											<option value="${casing.form}"><c:out value="${casing.form}"/></option>
											<option value="ATX">ATX</option>
											<option value="Micro ATX">Micro ATX</option>
											<option value="Mini ITX">Mini ITX</option>
										</select>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<input type="text" class="form-control" name="case_color" value="${casing.color}">
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					</c:choose>
					<div class="col-md-12 d-flex justify-content-between px-0">
						<a href="ComponentController?action=viewComponents&type=<c:out value="${comp.type}" />" class="btn btn-primary float-right">Back</a>
						<button class="btn btn-primary float-right">Save</button>
					</div>
				</div> <!-- Close card body -->
			</form> <!-- Close form -->
		</div> <!--Close card-->
	</div> <!--Close column-->
</div> <!--Close row-->
</div> <!--Close container-->
<script src="js/myjs.js"></script>
</body>
</html>