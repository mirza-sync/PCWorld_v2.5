<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>View Components</title>
</head>
<body>
<div class="row">
<%@include file="sidebar.jsp"%>
	<div class="m-5">
		<div class="mb-3">
		<h5>CPU</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Image</th>
					<th>Price</th>
					<th>Socket</th>
					<th>Base Clock</th>
					<th>Max Clock</th>
					<th>Number of Cores</th>
					<th>Multi-Thread</th>
					<th>Wattage</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cpus}" var="cpus">
			<form method="get" action="ComponentController">
			<tr>
				<td><c:out value="${cpus.id}" /></td>
				<td><c:out value="${cpus.brand}" /></td>
				<td><c:out value="${cpus.model}" /></td>
				<td><img src="${cpus.image}"/></td>
				<td>RM<c:out value="${cpus.price}" /></td>
				<td><c:out value="${cpus.socket}" /></td>
				<td><c:out value="${cpus.base_clock}" /> Ghz</td>
				<td><c:out value="${cpus.max_clock}" /> Ghz</td>
				<td><c:out value="${cpus.num_core}" /></td>
				<td><c:out value="${cpus.multithread}" /></td>
				<td><c:out value="${cpus.wattage}" /> Watt</td>
				<td>
					<input type="hidden" name="action" value="showEdit">
					<button type="submit" class="btn btn-primary" name="id" value="${cpus.id}">E</button>
					<a class="btn btn-danger">X</a>
				</td>
			</tr>
			</form>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>GPU</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Chipset</th>
					<th>VRAM</th>
					<th>Core Clock</th>
					<th>Color</th>
					<th>Wattage</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${gpus}" var="gpus">
			<tr>
				<td><c:out value="${gpus.id}" /></td>
				<td><c:out value="${gpus.brand}" /></td>
				<td><c:out value="${gpus.model}" /></td>
				<td>RM<c:out value="${gpus.price}" /></td>
				<td><c:out value="${gpus.chipset}" /></td>
				<td><c:out value="${gpus.num_vram}" />GB</td>
				<td><c:out value="${gpus.core_clock}" /> Mhz</td>
				<td><c:out value="${gpus.color}" /></td>
				<td><c:out value="${gpus.wattage}" /> Watt</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>Motherboard</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Form Factor</th>
					<th>Socket</th>
					<th>Num. of RAM Slot</th>
					<th>Maximum Memory</th>
					<th>Color</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${mobos}" var="mobos">
			<tr>
				<td><c:out value="${mobos.id}" /></td>
				<td><c:out value="${mobos.brand}" /></td>
				<td><c:out value="${mobos.model}" /></td>
				<td>RM<c:out value="${mobos.price}" /></td>
				<td><c:out value="${mobos.formfactor}" /></td>
				<td><c:out value="${mobos.socket}" /></td>
				<td><c:out value="${mobos.memory_slot}" /></td>
				<td><c:out value="${mobos.max_memory}" /></td>
				<td><c:out value="${mobos.color}" /></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>RAM</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Module</th>
					<th>RAM Type</th>
					<th>RAM Speed</th>
					<th>Color</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${rams}" var="rams">
			<tr>
				<td><c:out value="${rams.id}" /></td>
				<td><c:out value="${rams.brand}" /></td>
				<td><c:out value="${rams.model}" /></td>
				<td>RM<c:out value="${rams.price}" /></td>
				<td><c:out value="${rams.module}" /> x <c:out value="${rams.capacity}" />GB</td>
				<td><c:out value="${rams.ram_type}" /></td>
				<td><c:out value="${rams.speed}" /> Mhz</td>
				<td><c:out value="${rams.color}" /></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>Storage</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Type</th>
					<th>Capacity</th>
					<th>Form Factor</th>
					<th>Read Speed</th>
					<th>Write Speed</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${stors}" var="stors">
			<tr>
				<td><c:out value="${stors.id}" /></td>
				<td><c:out value="${stors.brand}" /></td>
				<td><c:out value="${stors.model}" /></td>
				<td>RM<c:out value="${stors.price}" /></td>
				<td><c:out value="${stors.storage_type}" /></td>
				<td><c:out value="${stors.capacity}" /> GB</td>
				<td><c:out value="${stors.form}" /></td>
				<td><c:out value="${stors.read_speed}" /> Mbps</td>
				<td><c:out value="${stors.write_speed}" /> Mbps</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>Power Supply</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Wattage</th>
					<th>Type</th>
					<th>Efficiency</th>
					<th>Color</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${psus}" var="psus">
			<tr>
				<td><c:out value="${psus.id}" /></td>
				<td><c:out value="${psus.brand}" /></td>
				<td><c:out value="${psus.model}" /></td>
				<td>RM<c:out value="${psus.price}" /></td>
				<td><c:out value="${psus.wattage}" /> Watt</td>
				<td><c:out value="${psus.psu_type}" /></td>
				<td><c:out value="${psus.efficiency}" /></td>
				<td><c:out value="${psus.color}" /></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="mb-3">
		<h5>Casing</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Price</th>
					<th>Form Factor</th>
					<th>Length</th>
					<th>Width</th>
					<th>Height</th>
					<th>Color</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cases}" var="cases">
			<tr>
				<td><c:out value="${cases.id}" /></td>
				<td><c:out value="${cases.brand}" /></td>
				<td><c:out value="${cases.model}" /></td>
				<td>RM<c:out value="${cases.price}" /></td>
				<td><c:out value="${cases.form}" /></td>
				<td><c:out value="${cases.length}" /></td>
				<td><c:out value="${cases.width}" /></td>
				<td><c:out value="${cases.height}" /></td>
				<td><c:out value="${cases.color}" /></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
		
		<a href="addComponent.jsp">
			<button class="btn btn-primary">Add New Component</button>
		</a>
		<a href="main.jsp">
			<button class="btn btn-primary">Home</button>
		</a>
	</div>
</div>
</body>
</html>