<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Customer - Edit Account</title>
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
				<h5>Edit Account</h5>
			</div>
			<div class="card-body">
				<div class="form-group row">
					<label class="col-md-3 col-form-label">Name</label>
					<div class="col-md-9">
						<input type="text" id="sname" class="form-control" name="name" value="<c:out value="${customer.name}"/>"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-3 col-form-label">Email</label>
					<div class="col-md-9">
						<input type="email" class="form-control" name="email" value="<c:out value="${customer.email}"/>"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-3 col-form-label">Phone</label>
					<div class="col-md-9">
						<input type="text" class="form-control" name="phone" value="<c:out value="${customer.phone}"/>"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-3 col-form-label">Password</label>
					<div class="col-md-9">
						<input type="text" id="spass" class="form-control" name="password" value="<c:out value="${customer.password}"/>"/>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<span class="float-right">
					<button type="submit" class="btn btn-primary" name="action" value="update">Update</button>
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