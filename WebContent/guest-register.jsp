<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>New Customer Account</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card mt-5">
			<div class="card-header">
				<h5>New Customer Account</h5>
			</div>
			<form method="post" action="CustomerController">
				<div class="card-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Name</label>
						<div class="col-md-9">
							<input type="text" id="cname" class="form-control" name="name"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Email</label>
						<div class="col-md-9">
							<input type="email" id="cmail" class="form-control" name="email"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Phone</label>
						<div class="col-md-9">
							<input type="text" id="cphone" class="form-control" name="phone"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Password</label>
						<div class="col-md-9">
							<input type="password" id="cpass" class="form-control" name="password"/>
						</div>
					</div>
				</div>
				<div class="card-footer pb-5">
					<div class="float-right">
						<button class="btn btn-success">Register</button>
					</div>
				</div>
			</form>
		</div>
		<div>
			Already have an account? <a href="customer-login.jsp">Login Here</a>
		</div>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>