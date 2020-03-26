<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Main Menu</title>
</head>
<body>
	<div class="col-md-6 offset-md-3 card my-5">
		<div class="card-body">
			<a href="addComponent.jsp">
				<button class="btn btn-success">Add Components</button>
			</a>
			<a href="ComponentController?action=viewComponent">
				<button class="btn btn-success">View Components</button>
			</a>
			<a href="recommend.jsp">
				<button class="btn btn-success">Recommend</button>
			</a>
			<a href="ComponentController?action=run">
				<button class="btn btn-primary">Add Product</button>
			</a>
		</div>
	</div>
</body>
</html>