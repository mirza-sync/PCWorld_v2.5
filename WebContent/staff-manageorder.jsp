<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Staff : Manage Order</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
<div class="col-md-12">	<!-- Start surrogate col -->
	<div class="row mb-4">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header pt-2 pb-1 pl-3" style="background-color: #fafafa">
					<h5>Customer Details</h5>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item py-2">Customer : ${customer.name}</li>
					<li class="list-group-item py-2">Phone : ${customer.phone}</li>
					<li class="list-group-item py-2">Email : ${customer.email}</li>
				</ul>
			</div>
		</div>
		<div class="col-md-6">
		<form action="OrderController" method="get">
		<input type="hidden" name="action" value="updatestatus"/>
		<input type="hidden" name="order_id" value="${order.id}"/>
		<input type="hidden" name="staff_id" value="${session.id}"/>
			<div class="card">
				<div class="card-header pt-2 pb-1 pl-3" style="background-color: #fafafa">
					<h5>Order Details</h5>
				</div>
				<div class="card-body p-3">
					<div class="d-flex justify-content-between">
						<div>Status</div>
						<div>
							<select name="status" style="width: 300px">
								<option value="${order.status}"><c:out value="${order.status}"/></option>
								<option value="Processing">Processing</option>
								<option value="Completed">Completed</option>
								<option value="Cancelled">Cancelled</option>
							</select>
						</div>
					</div>
					<hr class="my-2">
					<div class="d-flex justify-content-between">
						<div>Order Date</div>
						<div>
							${order.date}
						</div>
					</div>
					<hr class="my-2">
					<div class="d-flex justify-content-between">
						<div>Managed By</div>
						<div>
							${staff.name}
						</div>
					</div>
				</div>
				<div class="card-footer p-2 pr-3 bg-white">
					<button type="submit" class="btn btn-success font-weight-bold float-right" style="padding: 3px 10px">Save</button>
				</div>
			</div>
		</form>
		</div>
	</div> <!-- Close first row -->
	<div class="row">
	<div class="col-md-12">
	<form action="OrderController" method="get">
		<ul class="list-group mb-5 pb-5">
			<c:forEach items="${orderitems}" var="item">
				<li class="list-group-item d-flex media position-relative">
					<img src="${item.component.image}" class="ml-3 mr-5 align-self-center" style="width:50px; height:50px"/>
					<input type="hidden" name="itemArray" value="${item.component.id}">
					<div class="media-body">
						<div class="d-flex justify-content-between">
							<div><c:out value="${item.component.type}" /> : <c:out value="${item.component.brand}"/> <c:out value="${item.component.model}" /></div>
							<div>RM <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.component.price}"/></div>
							<input type="hidden" id="price${item.component.id}" value="${item.component.price}">
						</div>
						<hr>
						<div class="float-right">
							<strong>Quantity : ${item.quantity}</strong>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</form>
	</div>
	</div><!-- Close 2nd row -->
</div> <!-- Close surrogate col -->
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>



