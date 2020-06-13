<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Register New Staff</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card mt-5">
			<div class="card-header">
				<h5>Register New Staff</h5>
			</div>
			<form method="post" action="StaffController">
			<input type="hidden" name="action" value="add">
				<div class="card-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">IC</label>
						<div class="col-md-9">
							<input type="number" id="sic" class="form-control" name="ic"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Name</label>
						<div class="col-md-9">
							<input type="text" id="sname" class="form-control" name="name"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Password</label>
						<div class="col-md-9">
							<input type="password" id="spass" class="form-control" name="password"/>
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
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>