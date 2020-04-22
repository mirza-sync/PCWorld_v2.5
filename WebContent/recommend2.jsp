<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Recommended PC for you</title>
</head>
<body>
<div class="container-fluid">
<div class="row">
<%@include file="sidebar.jsp"%>
<div class="col-md-10">
	<div>
		<ul class="list-group mb-3">
			<li id="c1" class="list-group-item d-flex justify-content-between">
				<div>
					<img src="${pageContext.request.contextPath}/img/${cpu.image}" style="width:80px; height:80px"/>
				</div>
				<div>
					<div>Model : <c:out value="${pc.cpu.model}" /></div>
					<div>Base Clock : <c:out value="${pc.cpu.base_clock}" /></div>
					<div>Max Clock : <c:out value="${pc.cpu.max_clock}" /></div>
				</div>
				<div>
					<div>Num. of Thread : <c:out value="${pc.cpu.thread}" /></div>
					<div>Price : <c:out value="${pc.cpu.price}" /></div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>GPU</h6>
					<div>
						<c:out value="${pc.gpu.model}" />
					</div>
					<div>
						<c:out value="${pc.gpu.color}" />
						<c:out value="${pc.gpu.core_clock}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.gpu.price}" />
					</div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>Motherboard</h6>
					<div>
						<c:out value="${pc.mobo.model}" />
					</div>
					<div>
						<c:out value="${pc.mobo.formfactor}" />
						<c:out value="${pc.mobo.socket}" />
						<c:out value="${pc.mobo.color}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.mobo.price}" />
					</div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>RAM</h6>
					<div>
						<c:out value="${pc.ram.model}" />
					</div>
					<div>
						<c:out value="${pc.ram.color}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.ram.price}" />
					</div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>Storage</h6>
					<div>
						<c:out value="${pc.storage.model}" />
					</div>
					<div>
						<c:out value="${pc.storage.storage_type}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.storage.price}" />
					</div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>PSU</h6>
					<div>
						<c:out value="${pc.psu.model}" />
					</div>
					<div>
						<c:out value="${pc.psu.wattage}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.psu.price}" />
					</div>
				</div>
			</li>
			<li class="list-group-item">
				<div class="mb-3">
					<h6>Casing</h6>
					<div>
						<c:out value="${pc.casing.model}" />
					</div>
					<div>
						<c:out value="${pc.casing.color}" />
						<c:out value="${pc.casing.form}" />
					</div>
					<div class="float-right">
						<c:out value="${pc.casing.price}" />
					</div>
				</div>
			</li>
		</ul>
		<hr>
	<div>
		Total Price : <c:out value="${pc.totalPrice}" />
	</div>
	<div id="ga"></div>
	</div>
	
</div>	<!--  Close col -->
</div>	<!--  Close row -->
</div>	<!--  Close container -->
</body>
<script>
canvas = document.getElementById('ga');

</script>
</html>