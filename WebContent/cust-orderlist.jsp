<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>My Order</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div>
		<h5>My Cart</h5>
		<c:choose>
			<c:when test="${draft.exist == true}">
				<table class="table table-bordered table-hover text-center bg-white">
					<thead class="thead-dark">
			  			<tr>
				  			<th>Order ID</th>
				  			<th>Status</th>
				  			<th>Action</th>
				  		</tr>
			  		</thead>
			  		<tbody>
			  			<tr>
			  				<td>${draft.id}</td>
			  				<td>${draft.status}</td>
			  				<td>
			  					<a href="OrderController?action=viewitems&order_id=${draft.id}&role=${session.role}" class="btn btn-primary">View</a>
			  				</td>
			  			</tr>
			  		</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<strong>Your have no items in cart</strong>
			</c:otherwise>
		</c:choose>	
	</div>
	<hr>
	<br>
	<div>
		<h5>My Orders</h5>
		<table class="table table-bordered table-hover text-center bg-white">
			<thead class="thead-dark">
	  			<tr>
		  			<th>Order ID</th>
		  			<th>Timestamp</th>
		  			<th>Total Price</th>
		  			<th>Status</th>
		  			<th>Action</th>
		  		</tr>
	  		</thead>
	  		<c:forEach items="${orders}" var="order">
	  			<tr>
	  				<td>${order.id}</td>
	  				<td>${order.date}</td>
	  				<td>RM${order.total_price}</td>
	  				<td>${order.status}</td>
	  				<td>
	  					<a href="OrderController?action=viewitems&order_id=${order.id}&role=${session.role}" class="btn btn-primary">View</a>
	  				</td>
	  			</tr>
	  		</c:forEach>
	  		</tbody>
		</table>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>