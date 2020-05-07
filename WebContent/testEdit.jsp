<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Edit CPU</title>
</head>
<body>
<div class="container-fluid">
<div class="row">
	<%@include file="sidebar.jsp"%>
	<div class="col-md-8">
		<div class="card">
			<div class="card-header">
				<h5>Edit Component</h5>
			</div>
			<form method="POST" action="ComponentController" enctype="multipart/form-data">
				<input type="hidden" name="action" value="edit"/>
				<input type="hidden" name="id" value="${comp.id}"/>
				<div class="card-body">
					<div class="row d-flex align-items-center">
						<div class="col-md-3">
							<img src="${cpu.image}" style="width:200px; height:200px;"/>
						</div>
						<div class="col-md-9 pl-4">
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Type</label>
								<div class="col-md-10">
									<input type="text" class="form-control-plaintext pl-2" name="type" value="${comp.type}" readonly/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Brand</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="brand" value="${comp.brand}"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Model</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="model" value="${comp.model}"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 col-form-label">Price</label>
								<div class="col-md-10">
									<input type="text" class="form-control" name="price" value="${comp.price}"/>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-2">
									<label class="col-form-label">Image</label>
								</div>
								<div class="col-md-10">
									<input type="file" class="form-control" name="image"/>
								</div>
							</div>
						</div>
					</div>
					<!-- Hidden forms inside card body-->
					<div id="f1" style="display: block">
						<h4 class="mb-2">Processor</h4>
						<ul class="list-group mb-3">
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Socket</h6>
									<input type="text" class="form-control" name="cpu_socket" value="${cpu.socket}">
								</div>
							</li>
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Base Clock</h6>
									<input type="number" class="form-control" name="base_clock" value="${cpu.base_clock}">
								</div>
							</li>
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Max Clock</h6>
									<input type="number" class="form-control" name="max_clock" value="${cpu.max_clock}">
								</div>
							</li>
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Number of Cores</h6>
									<input type="number" class="form-control" name="num_core" value="${cpu.num_core}">
								</div>
							</li>
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Multi-Thread</h6>
									<select id="multithread" name="multithread" class="form-control">
										<option selected hidden disabled value="${cpu.multithread}"><c:out value="${cpu.multithread}"/></option>
										<option value="1">Yes</option>
										<option value="0">No</option>
									</select>
								</div>
							</li>
							<li class="list-group-item">
								<div class="mb-3">
									<h6>Power Usage</h6>
									<input type="number" class="form-control" name="cpu_watt" value="${cpu.wattage}">
								</div>
							</li>
						</ul>
					</div> <!--Close CPU-->
					<div class="col-md-12 d-flex justify-content-between px-0">
						<a href="ComponentController?action=viewComponents&type=<c:out value="${comp.type}" />" class="btn btn-primary float-right">Back</a>
						<button class="btn btn-primary float-right">Save</button>
					</div>
				</div> <!-- Close card body -->
			</form> <!-- Close form -->
		</div> <!--Close card-->
	</div> <!--Close column-->
</div> <!--Close row-->
</div> <!--Close container-->
</body>
</html>