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
	<form method="post" action="ComponentController?action=recommend">
	<div class="m-5 card">
		<div class="card-header">
			<h6>Lets answer a few questions :</h6>
		</div>
		<div class="card-body">
			<div class="form-group row">
				<label class="col-md-3 col-form-label">How much is your budget?</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="budget"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">What software do you use on your PC?</label>
				<div class="col-md-9">
					<input type="checkbox" id="vehicle1" name="check1" value="Bike">
					<label for="vehicle1">Microsoft Office</label><br>
					<input type="checkbox" id="vehicle3" name="check3" value="Boat">
					<label for="vehicle3">Web browsing</label><br>
					<input type="checkbox" id="vehicle2" name="check2" value="Car">
					<label for="vehicle2">Games</label><br>
					<input type="checkbox" id="vehicle3" name="check3" value="Boat">
					<label for="vehicle3">Video editing</label><br>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">What games do you play?</label>
				<div class="col-md-9">
					<input type="checkbox" id="vehicle1" name="check1" value="Bike">
					<label for="vehicle1">Esport games</label><br>
					<input type="checkbox" id="vehicle2" name="check2" value="Car">
					<label for="vehicle2">Triple A games</label><br>
					<input type="checkbox" id="vehicle3" name="check3" value="Boat">
					<label for="vehicle3">Indie games</label><br>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-md-3 col-form-label">How do you want your PC to look?</label>
				<div class="col-md-9">
					<input type="radio" id="male" name="gender" value="gamer">
					<label for="male">"Gamer"-ish</label><br>
					<input type="radio" id="female" name="gender" value="minimalist">
					<label for="female">Minimalist</label><br>
					<input type="radio" id="other" name="gender" value="low_profile">
					<label for="other">Low Profile</label><br>
					<input type="radio" id="meh" name="gender" value="anything">
					<label for="meh">I don't care</label>
				</div>
			</div>
		</div>
		<div class="card-footer">
			<button type="submit" class="btn btn-success">Recommend PC</button>
		</div>
	</div>
	</form>
</body>
</html>