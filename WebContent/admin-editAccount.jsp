<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Update Staff Account</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card">
			<div class="card-header">
				<h5>Update Staff Account</h5>
			</div>
			<form method="get" action="StaffController">
				<input type="hidden" name="id" value="<c:out value="${staff.id}"/>">
				<div class="card-body">
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Staff ID</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="ic" value="<c:out value="${staff.ic}"/>"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Staff Name</label>
						<div class="col-md-9">
							<input type="text" id="sname" class="form-control" name="name" value="<c:out value="${staff.name}"/>"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-md-3 col-form-label">Staff Password</label>
						<div class="col-md-9">
							<input type="text" id="spass" class="form-control" name="password" value="<c:out value="${staff.password}"/>"/>
						</div>
					</div>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-between">
						<c:if test="${session.role == 'admin'}">
							<button type="submit" class="btn btn-primary" formmethod="post" name="action" value="update">Update</button>
							<c:if test="${staff.role != 'admin'}">
								<button type="submit" class="btn btn-danger" name="action" value="delete">Delete Account</button>
							</c:if>
						</c:if>
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