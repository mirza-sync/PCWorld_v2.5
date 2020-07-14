<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Customer Login</title>
</head>
<body>
<div class="container-fluid">
<div id="main" class="row">
	<div class="col-md-6 offset-md-3">
		<div class="card mt-5">
			<div class="card-header">
				<h5>Customer Login</h5>
			</div>
			<form method="post" action="LoginController">
				<input type="hidden" name="type" value="customer">
				<div class="card-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Email</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="email"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Password</label>
						<div class="col-md-9">
							<input type="password" class="form-control" name="password"/>
						</div>
					</div>
				</div>
				<div class="card-footer">
					<button class="btn btn-success">Login</button>
				</div>
			</form>
		</div>
		<div>
			Don't have an account? <a href="guest-register.jsp">Register Here</a>
		</div>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>