<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>View Staff Account</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-8 offset-md-2">
		<div class="card">
			<div class="card-header">
				<h5>View Staff Account</h5>
			</div>
			<form method="get" action="StaffController">
				<input type="hidden" name="id" value="<c:out value="${staff.id}"/>">
				<div class="card-body">
					<div class="row">
						<div class="col-md-3">
							<strong>Staff ID</strong>
						</div>
						<div class="col-md-9">
							<p>: ${staff.ic}</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<strong>Staff Name</strong>
						</div>
						<div class="col-md-9">
							<p>: ${staff.name}</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<strong>Staff Role</strong>
						</div>
						<div class="col-md-9">
							<p>: ${staff.role}</p>
						</div>
					</div>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-between">
						<c:if test="${session.role == 'admin'}">
							<button type="submit" class="btn btn-primary" name="action" value="showEdit">Edit</button>
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