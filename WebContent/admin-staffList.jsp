<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>View Staff List</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-10 offset-md-1">
	  	<table class="table table-bordered table-hover text-center">
	  		<thead class="thead-dark">
	  			<tr>
		  			<th>Staff IC</th>
		  			<th>Staff Name</th>
		  			<th>Role</th>
		  			<th>Action</th>
		  		</tr>
	  		</thead>
	  		<tbody>
	  			<c:forEach items ="${staffs}" var="staff">
	  			<tr>
	  				<td><c:out value="${staff.ic}"/></td>
	  				<td><c:out value="${staff.name}"/></td>
	  				<td><c:out value="${staff.role}"/></td>
	  				<td>
	  					<form action="StaffController" method="GET" class="d-flex justify-content-center form-inline">
		  					<button type="submit" name="action" value="view" class="btn btn-primary mr-1">View</button>
		  					<c:if test="${staff.role == 'staff'}">
								<button type="submit" class="btn btn-danger" name="action" value="delete">Delete Account</button>
							</c:if>
		  					<input type="hidden" name="id" value="${staff.id}"/>
	  					</form>
	  				</td>
	  			</tr>
	  			</c:forEach>
	  		</tbody>
	  	</table>
	  	<div class="float-right">
  			<a href="staff-register.jsp" class="btn btn-primary">New Staff</a>
  		</div>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
</body>
</html>