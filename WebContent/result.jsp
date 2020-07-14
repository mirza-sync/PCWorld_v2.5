<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<%@include file="sidebar.jsp"%>
<div class="row">
<div class="col-md-10 offset-md-1">
<form action="OrderController" method="get">
<input type="hidden" name="cust_id" value="${session.id}">
	<div class="mb-5">
		<ul class="list-group mb-3">
			<li class="list-group-item d-flex media">
				<img src="${pc.cpu.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
					<div class="d-flex justify-content-between">
						<div>CPU : <c:out value="${pc.cpu.model}" /></div>
						<div>RM <c:out value="${pc.cpu.price}" /></div>
					</div>
					<hr>
					<div class="d-flex justify-content-between">
						<div>
							<div>Socket : <c:out value="${pc.cpu.socket}" /></div>
							<div>Multi-Thread : <c:out value="${pc.cpu.multithread}" /></div>
							<div>Base Clock : <c:out value="${pc.cpu.base_clock}" /> MHz</div>
							<div>Max Clock : <c:out value="${pc.cpu.max_clock}" /> MHz</div>
						</div>
						<div class="align-self-center">
							<button class="btn btn-primary">Add to Order</button>
							<input type="hidden" name="items" value="${pc.cpu.id}">
						</div>
					</div>
				</div>
			</li>
			<li class="list-group-item d-flex media">
				<img src="${pc.gpu.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
					<div class="d-flex justify-content-between">
						<div>GPU : <c:out value="${pc.gpu.chipset}" /> <c:out value="${pc.gpu.model}" /></div>
						<div>RM <c:out value="${pc.gpu.price}" /></div>
					</div>
					<hr>
					<div class="d-flex justify-content-between">
						<div>
							<div>Brand : <c:out value="${pc.gpu.brand}" /></div>
							<div>Color : <c:out value="${pc.gpu.color}" /></div>
							<div>Core clock : <c:out value="${pc.gpu.core_clock}" /> MHz</div>
							<div>VRAM : <c:out value="${pc.gpu.num_vram}" />GB</div>
						</div>
						<div class="align-self-center">
							<button class="btn btn-primary">Add to Order</button>
							<input type="hidden" name="items" value="${pc.gpu.id}">
						</div>
					</div>
				</div>
			</li>
			<li class="list-group-item d-flex media">
				<img src="${pc.mobo.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
					<div class="d-flex justify-content-between">
						<div>Motherboard : <c:out value="${pc.mobo.model}" /></div>
						<div>RM <c:out value="${pc.mobo.price}" /></div>
					</div>
					<hr>
					<div class="d-flex justify-content-between">
						<div>
							<div>Form : <c:out value="${pc.mobo.formfactor}" /></div>
							<div>Socket : <c:out value="${pc.mobo.socket}" /></div>
						</div>
						<div class="align-self-center">
							<button class="btn btn-primary">Add to Order</button>
							<input type="hidden" name="items" value="${pc.mobo.id}">
						</div>
					</div>
				</div>
			</li>
			<li class="list-group-item d-flex media">
					<img src="${pc.ram.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
					<div class="media-body">
						<div class="d-flex justify-content-between">
							<div>RAM :  <c:out value="${pc.ram.brand}" /> <c:out value="${pc.ram.model}" /></div>
							<div>RM <c:out value="${pc.ram.price}" /></div>
						</div>
						<hr>
						<div class="d-flex justify-content-between">
							<div>
								<div>Number of module : <c:out value="${pc.ram.module}" /></div>
								<div>Speed : <c:out value="${pc.ram.speed}" /> Mhz</div>
								<div>Type : <c:out value="${pc.ram.ram_type}" /></div>
								<div>Color : <c:out value="${pc.ram.color}" /></div>
							</div>
							<div class="align-self-center">
								<button class="btn btn-primary">Add to Order</button>
								<input type="hidden" name="items" value="${pc.ram.id}">
							</div>
						</div>
					</div>
			</li>
			<li class="list-group-item d-flex media">
				<img src="${pc.storage.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
					<div class="d-flex justify-content-between">
						<div>Storage : <c:out value="${pc.storage.brand}" /> <c:out value="${pc.storage.model}" /></div>
						<div>RM <c:out value="${pc.storage.price}" /></div>
					</div>
					<hr>
					<div class="d-flex justify-content-between">
						<div>
							<div>Type : <c:out value="${pc.storage.storage_type}" /></div>
							<div>Capacity : <c:out value="${pc.storage.capacity}" /></div>
							<div>Form : <c:out value="${pc.storage.form}" /></div>
						</div>
						<div class="align-self-center">
							<button class="btn btn-primary">Add to Order</button>
							<input type="hidden" name="items" value="${pc.storage.id}">
						</div>
					</div>
				</div>
			</li>
			<li class="list-group-item d-flex media">
				<img src="${pc.psu.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
					<div class="d-flex justify-content-between">
						<div>PSU : <c:out value="${pc.psu.brand}" /> <c:out value="${pc.psu.model}" /></div>
						<div>RM <c:out value="${pc.psu.price}" /></div>
					</div>
					<hr>
					<div class="d-flex justify-content-between">
						<div>
							<div>Wattage : <c:out value="${pc.psu.wattage}" />W</div>
							<div>Efficiency : <c:out value="${pc.psu.efficiency}" /></div>
							<div>Modularity : <c:out value="${pc.psu.modularity}" /></div>
							<div>Color : <c:out value="${pc.psu.color}" /></div>
						</div>
						<div class="align-self-center">
							<button class="btn btn-primary">Add to Order</button>
							<input type="hidden" name="items" value="${pc.psu.id}">
						</div>
					</div>
				</div>
			</li>
			<li class="list-group-item d-flex media">
				<img src="${pc.casing.image}" class="ml-3 mr-5 align-self-center" style="width:80px; height:80px"/>
				<div class="media-body">
				<div class="d-flex justify-content-between">
					<div>Casing : <c:out value="${pc.casing.brand}" /> <c:out value="${pc.casing.model}" /></div>
					<div>RM <c:out value="${pc.casing.price}" /></div>
				</div>
				<hr>
				<div class="d-flex justify-content-between">
					<div>
						<div>Color : <c:out value="${pc.casing.color}" /></div>
						<div>Form : <c:out value="${pc.casing.form}" /></div>
					</div>
					<div class="align-self-center">
						<button class="btn btn-primary">Add to Order</button>
						<input type="hidden" name="items" value="${pc.casing.id}">
					</div>
				</div>
				</div>
			</li>
		</ul>
	</div>
	<hr>
	<div class="fixed-bottom bg-light py-3 px-5">
		<div class="d-flex justify-content-between">
			<strong>
			Total Price : <fmt:formatNumber value="${pc.totalPrice}" minFractionDigits="2"/>
			<input type="hidden" name="total_price" value="${pc.totalPrice}">
			</strong>
			<button class="btn btn-success">Order Now</button>
			<input type="hidden" name="action" value="add">
		</div>
	</div>
</form>
</div>	<!--  Close col -->
</div>	<!--  Close row -->
</div>	<!--  Close container -->
</body>
<script>
canvas = document.getElementById('ga');

</script>
<script src="js/myjs.js"></script>
</html>