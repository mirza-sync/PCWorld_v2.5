<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Staff - Order List</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<h5>Pending Orders</h5>
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
  					<a href="OrderController?action=deleteorder&order_id=${order.id}&cust_id=${session.id}" class="btn btn-danger">Delete</a>
  				</td>
  			</tr>
  		</c:forEach>
  		</tbody>
	</table>
	<hr>
	<h5>Completed Orders</h5>
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
  					<a href="OrderController?action=deleteorder&order_id=${order.id}&cust_id=${session.id}" class="btn btn-danger">Delete</a>
  				</td>
  			</tr>
  		</c:forEach>
  		</tbody>
	</table>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>