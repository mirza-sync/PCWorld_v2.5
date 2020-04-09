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
<div class="container-fluid bg-success" style="height:100vh">
<div class="row bg-dark">
<%@include file="sidebar.jsp"%>
	<div class="col-10 bg-secondary">
		<img class="img-fluid" src="img/banner-nvidia1.jpg" style="width: 100%">
		<div class="card my-5 border-0">
		<img class="card-img-top" src="img/banner3.jpg">
			<div class="card-body">
				<ul class="list-group">
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=CPU">CPU</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=GPU">GPU</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=Motherboard">Motherboard</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=RAM">RAM</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=Storage">Storage</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=PSU">PSU</a>
				  </li>
				  <li class="list-group-item">
				  	<a href="ComponentController?action=viewComponents&type=Casing">Casing</a>
				</ul>
				<a href="ComponentController?action=viewComponents" class="float-right">
					<button class="btn btn-success">View Components</button>
				</a>
			</div>
		</div>
		<div class="card my-5 border-0">
		<img class="card-img-top" src="img/banner1.jpg" class="float-right">
			<div class="card-body">
				<a href="recommend.jsp" class="float-right">
					<button class="btn btn-success">Recommend</button>
				</a>
			</div>
		</div>
		<div class="card my-5 border-0">
		<img class="card-img-top" src="img/banner-nvidia1.jpg" class="float-right">
			<div class="card-body">
				<a href="addComponent.jsp" class="float-right">
					<button class="btn btn-success">Add Components</button>
				</a>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>