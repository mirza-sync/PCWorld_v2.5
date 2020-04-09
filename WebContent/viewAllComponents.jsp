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
<div class="container-fluid">
<div class="row">
<%@include file="sidebar.jsp"%>
<div class="col-10 bg-secondary">
	<div>
		<c:choose>
		<c:when test="${type == 'CPU'}">
		<h5>CPU</h5>
		<table class="table table-bordered table-hover text-center bg-white">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th style="width:15%">Name</th>
					<th>Socket</th>
					<th>Base Clock</th>
					<th>Max Clock</th>
					<th>Core Count</th>
					<th>Thread</th>
					<th>Wattage</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cpus}" var="cpus">
			<tr>
				<td><c:out value="${cpus.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${cpus.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${cpus.id}">
						<c:out value="${cpus.brand}" /> <c:out value="${cpus.model}" />
					</a>
				</td>
				<td><c:out value="${cpus.socket}" /></td>
				<td><c:out value="${cpus.base_clock}" /> Ghz</td>
				<td><c:out value="${cpus.max_clock}" /> Ghz</td>
				<td><c:out value="${cpus.num_core}" /></td>
				<td><c:out value="${cpus.thread}" /></td>
				<td><c:out value="${cpus.wattage}" /> Watt</td>
				<td>RM<c:out value="${cpus.price}" /></td>
				<td class="">
					<a href="ComponentController?action=showEdit&id=${cpus.id}&type=${cpus.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${cpus.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'GPU'}">
		<h5>GPU</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Chipset</th>
					<th>VRAM</th>
					<th>Core Clock</th>
					<th>Color</th>
					<th>Wattage</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${gpus}" var="gpus">
			<tr>
				<td><c:out value="${gpus.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${gpus.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${gpus.id}">
						<c:out value="${gpus.brand}" /> <c:out value="${gpus.model}" />
					</a>
				</td>
				<td><c:out value="${gpus.chipset}" /></td>
				<td><c:out value="${gpus.num_vram}" />GB <c:out value="${gpus.vram_type}" /></td>
				<td><c:out value="${gpus.core_clock}" /> Mhz</td>
				<td><c:out value="${gpus.color}" /></td>
				<td><c:out value="${gpus.wattage}" /> Watt</td>
				<td>RM<c:out value="${gpus.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${gpus.id}&type=${gpus.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${gpus.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'Motherboard'}">
		<h5>Motherboard</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Form Factor</th>
					<th>Socket</th>
					<th>Supported Memory</th>
					<th>Num. of RAM Slot</th>
					<th>Maximum Memory</th>
					<th>Color</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${mobos}" var="mobos">
			<tr>
				<td><c:out value="${mobos.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${mobos.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${mobos.id}">
						<c:out value="${mobos.brand}" /> <c:out value="${mobos.model}" />
					</a>
				</td>
				<td><c:out value="${mobos.formfactor}" /></td>
				<td><c:out value="${mobos.socket}" /></td>
				<td><c:out value="${mobos.memory_type}" /></td>
				<td><c:out value="${mobos.memory_slot}" /></td>
				<td><c:out value="${mobos.max_memory}" /></td>
				<td><c:out value="${mobos.color}" /></td>
				<td>RM<c:out value="${mobos.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${mobos.id}&type=${mobos.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${mobos.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'RAM'}">
		<h5>RAM</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Module</th>
					<th>RAM Type</th>
					<th>RAM Speed</th>
					<th>Color</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${rams}" var="rams">
			<tr>
				<td><c:out value="${rams.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${rams.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${rams.id}">
						<c:out value="${rams.brand}" /> <c:out value="${rams.model}" />
					</a>
				</td>
				<td><c:out value="${rams.module}" /> x <c:out value="${rams.capacity}" />GB</td>
				<td><c:out value="${rams.ram_type}" /></td>
				<td><c:out value="${rams.speed}" /> Mhz</td>
				<td><c:out value="${rams.color}" /></td>
				<td>RM<c:out value="${rams.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${rams.id}&type=${rams.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${rams.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'Storage'}">
		<h5>Storage</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Type</th>
					<th>Capacity</th>
					<th>Form Factor</th>
					<th>Read Speed</th>
					<th>Write Speed</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${stors}" var="stors">
			<tr>
				<td><c:out value="${stors.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${stors.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${stors.id}">
						<c:out value="${stors.brand}" /> <c:out value="${stors.model}" />
					</a>
				</td>
				<td><c:out value="${stors.storage_type}" /></td>
				<td><c:out value="${stors.capacity}" /> GB</td>
				<td><c:out value="${stors.form}" /></td>
				<td><c:out value="${stors.read_speed}" /> Mbps</td>
				<td><c:out value="${stors.write_speed}" /> Mbps</td>
				<td>RM<c:out value="${stors.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${stors.id}&type=${stors.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${stors.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'PSU'}">
		<h5>Power Supply</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Wattage</th>
					<th>Type</th>
					<th>Efficiency</th>
					<th>Color</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${psus}" var="psus">
			<tr>
				<td><c:out value="${psus.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${psus.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${psus.id}">
						<c:out value="${psus.brand}" /> <c:out value="${psus.model}" />
					</a>
				</td>
				<td><c:out value="${psus.wattage}" /> Watt</td>
				<td><c:out value="${psus.psu_type}" /></td>
				<td><c:out value="${psus.efficiency}" /></td>
				<td><c:out value="${psus.color}" /></td>
				<td>RM<c:out value="${psus.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${psus.id}&type=${psus.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${psus.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		
		<c:when test="${type == 'Casing'}">
		<h5>Casing</h5>
		<table class="table table-bordered table-hover text-center">
			<thead class="thead-dark">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Form Factor</th>
					<th>Length</th>
					<th>Width</th>
					<th>Height</th>
					<th>Color</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cases}" var="cases">
			<tr>
				<td><c:out value="${cases.id}" /></td>
				<td><img src="${pageContext.request.contextPath}/img/${cases.image}"/></td>
				<td>
					<a href="ComponentController?action=viewCompById&id=${cases.id}">
						<c:out value="${cases.brand}" /> <c:out value="${cases.model}" />
					</a>
				</td>
				<td><c:out value="${cases.form}" /></td>
				<td><c:out value="${cases.length}" /></td>
				<td><c:out value="${cases.width}" /></td>
				<td><c:out value="${cases.height}" /></td>
				<td><c:out value="${cases.color}" /></td>
				<td>RM<c:out value="${cases.price}" /></td>
				<td>
					<a href="ComponentController?action=showEdit&id=${cases.id}&type=${cases.type}" class="btn btn-primary">E</a>
					<a href="ComponentController?action=delete&id=${cases.id}" class="btn btn-danger">X</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		</c:choose>
		
		
		<a href="addComponent.jsp">
			<button class="btn btn-primary">Add New Component</button>
		</a>
		<a href="main.jsp">
			<button class="btn btn-primary">Home</button>
		</a>
	</div>
</div>	<!-- Close col-9 -->
</div>	<!-- Close row nav+main -->
</div>	<!-- Close container -->
</body>
</html>