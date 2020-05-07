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
<div class="row">
<%@include file="sidebar.jsp"%>
<div class="col-md-10">
	<div class="my-5">
		<ul class="list-group mb-3">
			<li id="c1" class="list-group-item d-flex justify-content-between">
				<div>
					<img src="${pc.cpu.image}" style="width:80px; height:80px"/>
				</div>
				<div>
					<div>Model : <c:out value="${pc.cpu.model}" /></div>
					<div>Base Clock : <c:out value="${pc.cpu.base_clock}" /></div>
					<div>Max Clock : <c:out value="${pc.cpu.max_clock}" /></div>
				</div>
				<div>
					<div>Multi-Thread : <c:out value="${pc.cpu.multithread}" /></div>
					<div>Price : <c:out value="${pc.cpu.price}" /></div>
				</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>GPU</h6> -->
					<div>
						<img src="${pc.gpu.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.gpu.model}" />
						</div>
						<div>
							<c:out value="${pc.gpu.color}" />
						</div>
					</div>
					<div>
						<div>
							<c:out value="${pc.gpu.core_clock}" />
						</div>
						<div class="float-right">
							<c:out value="${pc.gpu.price}" />
						</div>
					</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>Motherboard</h6> -->
					<div>
						<img src="${pc.mobo.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.mobo.model}" />
						</div>
						<div>
							<c:out value="${pc.mobo.formfactor}" />
						</div>
						<div>
							<c:out value="${pc.mobo.socket}" />
						</div>
					</div>
					<div>
						<div>
							<c:out value="${pc.mobo.color}" />
						</div>
						<div class="float-right">
							<c:out value="${pc.mobo.price}" />
						</div>
					</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>RAM</h6> -->
					<div>
						<img src="${pc.ram.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.ram.model}" />
						</div>
						<div>
							<c:out value="${pc.ram.color}" />
						</div>
					</div>
					<div>
						<div class="float-right">
							<c:out value="${pc.ram.price}" />
						</div>
					</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>Storage</h6> -->
					<div>
						<img src="${pc.storage.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.storage.model}" />
						</div>
						<div>
							<c:out value="${pc.storage.storage_type}" />
						</div>
					</div>
					<div class="float-right">
						<c:out value="${pc.storage.price}" />
					</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>PSU</h6> -->
					<div>
						<img src="${pc.psu.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.psu.model}" />
						</div>
						<div>
							<c:out value="${pc.psu.wattage}" />
						</div>
					</div>
					<div class="float-right">
						<c:out value="${pc.psu.price}" />
					</div>
			</li>
			<li class="list-group-item d-flex justify-content-between">
					<!-- <h6>Casing</h6> -->
					<div>
						<img src="${pc.casing.image}" style="width:80px; height:80px"/>
					</div>
					<div>
						<div>
							<c:out value="${pc.casing.model}" />
						</div>
						<div>
							<c:out value="${pc.casing.color}" />
							<c:out value="${pc.casing.form}" />
						</div>
					</div>
					<div class="float-right">
						<c:out value="${pc.casing.price}" />
					</div>
			</li>
		</ul>
		<hr>
	<div>
		<strong>
		Total Price : <fmt:formatNumber value="${pc.totalPrice}" minFractionDigits="2"/>
		</strong>
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