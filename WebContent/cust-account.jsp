<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Customer - View Account</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-8 offset-md-2">
		<form method="get" action="CustomerController">
		<input type="hidden" name="id" value="<c:out value="${customer.id}"/>">
		<div class="card">
			<div class="card-header">
				<h5>Customer Account</h5>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-3">
						<strong>Name</strong>
					</div>
					<div class="col-md-9">
						<p>: ${customer.name}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<strong>Email</strong>
					</div>
					<div class="col-md-9">
						<p>: ${customer.email}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<strong>Phone</strong>
					</div>
					<div class="col-md-9">
						<p>: ${customer.phone}</p>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<span class="float-right">
					<button type="submit" class="btn btn-primary" name="action" value="showEdit">Edit</button>
					<button type="submit" class="btn btn-danger" name="action" value="delete">Delete Account</button>
				</span>
			</div>
		</div>
		</form>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>