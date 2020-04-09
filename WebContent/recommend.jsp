<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Recommend PC</title>
</head>
<body>
<div class="container-fluid">
<div class="row">
<%@include file="sidebar.jsp"%>
	<div class="m-5 card">
		<div class="card-header">
			<h6>Lets answer a few questions :</h6>
		</div>
		<form method="post" action="ComponentController?action=recommend">
		<div class="card-body">
			<div class="form-group row">
				<label class="col-md-3 col-form-label">How much is your budget?</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="budget"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">What is the purpose of your PC?</label>
				<div class="col-md-9">
					<input type="checkbox" id="vehicle1" name="c_usage" value="office">
					<label for="vehicle1">Office work</label><br>
					<input type="checkbox" id="vehicle3" name="c_usage" value="office">
					<label for="vehicle3">Web browsing</label><br>
					<input type="checkbox" id="vehicle2" name="c_usage" value="gaming">
					<label for="vehicle2">Gaming</label><br>
					<input type="checkbox" id="vehicle3" name="c_usage" value="video3d">
					<label for="vehicle3">Video editing</label><br>
					<input type="checkbox" id="vehicle3" name="c_usage" value="video3d">
					<label for="vehicle3">3D modeling</label><br>
				</div>
			</div>
			<!-- <div class="form-group row">
				<label class="col-md-3 col-form-label">What games do you play?</label>
				<div class="col-md-9">
					<input type="checkbox" id="vehicle1" name="c_game" value="esport">
					<label for="vehicle1">Esport games</label><br>
					<input type="checkbox" id="vehicle2" name="c_game" value="AAA">
					<label for="vehicle2">Triple A games</label><br>
					<input type="checkbox" id="vehicle3" name="c_game" value="indie">
					<label for="vehicle3">Indie games</label><br>
				</div>
			</div> -->
			<div class="form-group row">
				<label class="col-md-3 col-form-label">How do you want your PC to look?</label>
				<div class="col-md-9">
					<input type="radio" id="male" name="r_style" value="gamer">
					<label for="male">"Gamer"-ish</label><br>
					<input type="radio" id="female" name="r_style" value="minimalist">
					<label for="female">Minimalist</label><br>
					<input type="radio" id="other" name="r_style" value="low_profile">
					<label for="other">Low Profile</label><br>
					<input type="radio" id="meh" name="r_style" value="anything">
					<label for="meh">I don't care</label>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">What color do you want for your PC?</label>
				<div class="col-md-9">
					<input type="radio" id="r1" name="r_color" value="Black">
					<label for="male">Black</label><br>
					<input type="radio" id="r2" name="r_color" value="White">
					<label for="female">White</label><br>
					<input type="radio" id="r3" name="r_color" value="other">
					<label for="other">Other</label><br>
					<input type="radio" id="r4" name="r_color" value="anything">
					<label for="meh">I don't care</label>
				</div>
			</div>
		</div>
		<div class="card-footer">
			<button type="submit" class="btn btn-success">Recommend PC</button>
		</div>
		</form>
	</div>
</div>
</div>
</body>
</html>