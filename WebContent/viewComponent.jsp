<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>View Component</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card">
			<div class="card-header">
				<h5>View Component</h5>
			</div>
			<form method="GET" action="ComponentController">
				<input type="hidden" name="id" value="${comp.id}"/>
				<input type="hidden" name="type" value="${comp.type}"/>
				<div class="card-body">
					<div class="row d-flex align-items-center ml-2 mb-3">
						<div class="col-md-3">
							<img src="${comp.image}" style="width:200px; height:200px;"/>
						</div>
						<div class="col-md-9 pl-5">
							<div class="form-group row mb-2">
								<label class="col-md-2 col-form-label"><strong>Type</strong></label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext" name="type" value=":	 ${comp.type}" readonly/>
								</div>
							</div>
							<hr class="my-1">
							<div class="form-group row mb-2">
								<label class="col-md-2 col-form-label"><strong>Brand</strong></label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext" name="brand" value=":	 ${comp.brand}" readonly/>
								</div>
							</div>
							<hr class="my-1">
							<div class="form-group row mb-2">
								<label class="col-md-2 col-form-label"><strong>Model</strong></label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext" name="model" value=":	 ${comp.model}" readonly/>
								</div>
							</div>
							<hr class="my-1">
							<div class="form-group row">
								<label class="col-md-2 col-form-label"><strong>Price</strong></label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext" name="price" value=":	 ${comp.price}" readonly/>
								</div>
							</div>
						</div>
					</div>
					<c:choose>
					<c:when test="${comp.type == 'CPU'}">
						<div> <!-- Start CPU -->
							<h5 class="m-2">Processor</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<strong>${cpu.socket}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Base Clock</h6>
										<strong>${cpu.base_clock}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Max Clock</h6>
										<strong>${cpu.max_clock}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of Cores</h6>
										<strong>${cpu.num_core}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Multi-Thread</h6>
										<strong>
											<c:choose>
												<c:when test="${cpu.multithread == 1}">
													Yes
												</c:when>
												<c:otherwise>
													No
												</c:otherwise>
											</c:choose>
										</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Power Usage</h6>
										<strong>${cpu.wattage}</strong>
									</div>
								</li>
							</ul>
						</div> <!--End CPU-->
					</c:when>
					<c:when test="${comp.type == 'GPU'}">
						<div>
							<h5 class="m-2">Graphics Card</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Chipset</h6>
										<strong>${gpu.chipset}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of VRAM</h6>
										<strong>${gpu.num_vram}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Core clock</h6>
										<strong>${gpu.core_clock}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<strong>${gpu.color}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Motherboard'}">
						<div>
							<h5 class="m-2">Motherboard</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<strong>${mobo.formfactor}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Socket</h6>
										<strong>${mobo.socket}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Memory Slot</h6>
										<strong>${mobo.memory_slot}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Maximum Memory</h6>
										<strong>${mobo.max_memory}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<strong>${mobo.color}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'RAM'}">
						<div>
							<h5 class="m-2">RAM</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<strong>${ram.capacity}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Type</h6>
										<strong>${ram.ram_type}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Number of Module</h6>
										<strong>${ram.module}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>RAM Speed</h6>
										<strong>${ram.speed}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<strong>${ram.color}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Storage'}">
						<div>
							<h5 class="m-2">Storage</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Storage Type</h6>
										<strong>${stor.storage_type}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Capacity</h6>
										<strong>${stor.capacity}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<strong>${stor.form}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'PSU'}">
						<div>
							<h5 class="m-2">Power Supply</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Wattage</h6>
										<strong>${psu.wattage}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>PSU type</h6>
										<strong>${psu.modularity}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Efficiency Rating</h6>
										<strong>${psu.efficiency}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<strong>${psu.color}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					<c:when test="${comp.type == 'Casing'}">
						<div>
							<h5 class="m-2">Casing</h5>
							<ul class="list-group mb-3">
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Form Factor</h6>
										<strong>${casing.form}</strong>
									</div>
								</li>
								<li class="list-group-item">
									<div class="mb-3">
										<h6>Color</h6>
										<strong>${casing.color}</strong>
									</div>
								</li>
							</ul>
						</div>
					</c:when>
					</c:choose>
					<div class="col-md-12 d-flex justify-content-between px-0">
						<a href="ComponentController?action=viewComponents&type=<c:out value="${comp.type}" />" class="btn btn-secondary float-right">Back</a>
						<c:if test="${session.role == 'staff'}">
							<a href="ComponentController?action=showEdit&id=${comp.id}&type=${comp.type}" class="btn btn-primary float-right">Edit</a>
						</c:if>
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