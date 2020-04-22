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
<%@include file="sidebar.jsp"%>
<div class="container-fluid">
	<div class="row">
		<div class="jumbotron col-md-12" style="background-image:url('img/pcbanner2.png'); background-size:cover; padding:100px 60px; border-radius:0px">
			<div class="row">
				<div class="col-md-3">
					<h1 class="text-white display-4">Welcome to PCWorld</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="card col-md-10 offset-md-1">
		<div class="row">
			<div class="col-md-4 p-3">
			  	<a href="ComponentController?action=viewComponents&type=CPU">
			  			<img src="img/pcicon/png/075-cpu-1.png" style="width:80px; height:80px">
			  			CPU
			  	</a>
			</div>
			<div class="col-md-4 shadow p-3">
			  	<a href="ComponentController?action=viewComponents&type=GPU">
			  			<img src="img/pcicon/png/012-video-card.png" style="width:80px; height:80px">
			  			GPU
			  	</a>
			</div>
			<div class="col-md-4 p-3">
			  	<a href="ComponentController?action=viewComponents&type=Motherboard">
			  			<img src="img/pcicon/png/070-motherboard.png" style="width:80px; height:80px">
			  			Motherboard
			  	</a>
			</div>
			<div class="col-md-4 shadow p-3">
			  	<a href="ComponentController?action=viewComponents&type=RAM">
			  			<img src="img/pcicon/png/067-ram.png" style="width:80px; height:80px">
			  			RAM
			  	</a>
			</div>
			<div class="col-md-4 p-3">
			  	<a href="ComponentController?action=viewComponents&type=Storage">
			  			<img src="img/pcicon/png/071-hdd.png" style="width:80px; height:80px">
			  			Storage
			  	</a>
			</div>
			<div class="col-md-4 shadow p-3">
			  	<a href="ComponentController?action=viewComponents&type=PSU">
			  			<img src="img/pcicon/png/084-supply.png" style="width:80px; height:80px">
			  			PSU
			  	</a>
			</div>
			<div class="col-md-4 p-3">
			  	<a href="ComponentController?action=viewComponents&type=Casing">
			  			<img src="img/pcicon/png/091-desktop.png" style="width:80px; height:80px">
			  			Casing
			  	</a>
			</div>
		</div>
		</div><!-- Close col -->
	</div>
	<div class="row justify-content-center">
		<div class="card my-5 border-0">
		<img class="card-img-top" src="img/banner1.jpg" class="float-right">
			<div class="card-body">
				<a href="recommend.jsp" class="float-right">
					<button class="btn btn-success">Recommend</button>
				</a>
			</div>
		</div>
	</div>
	<div class="row justify-content-center">
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
</body>
</html>