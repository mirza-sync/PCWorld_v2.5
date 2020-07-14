<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>Staff - Order List</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-12"><!-- start col-12 -->
	<div class="row">
		<div style="padding-left:0" class="col-6 mb-3 float-right">
			<div class="card">
				<div class="card-body px-5">
					<input type="hidden" id="id" value="${session.id}">
					<select class="col-12" id="ordertype" name="ordertype" onchange="orderBy()">
						<option value="all">All</option>
						<option value="Pending">Pending</option>
						<option value="Processing">Processing</option>
						<option value="Cancelled">Cancelled</option>
						<option value="Completed">Completed</option>
						<option value="assigned">Assigned to Me</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<h5>Orders List</h5>
		<table class="table table-bordered table-hover text-center bg-white">
			<thead class="thead-dark">
	  			<tr>
		  			<th>Order ID</th>
		  			<th>Timestamp</th>
		  			<th>Total Price</th>
		  			<th>Staff</th>
		  			<th>Status</th>
		  			<th>Action</th>
		  		</tr>
	  		</thead>
	  		<tbody id="tbody">
	  		<%-- <c:forEach items="${orders}" var="order">
	  			<tr>
	  				<td>${order.id}</td>
	  				<td>${order.date}</td>
	  				<td>RM${order.total_price}</td>
	  				<td>${order.status}</td>
	  				<td>
	  					<a href="OrderController?action=viewitems&order_id=${order.id}&role=${session.role}" class="btn btn-primary">View</a>
	  					<a href="OrderController?action=deleteorder&order_id=${order.id}&cust_id=${session.id}" class="btn btn-danger">Delete</a>
	  				</td>
	  			</tr>
	  			\${\${order.status}=="Cancelled" ? '<a href="OrderController?action=viewitems&order_id=\${order.id}&role=staff" class="btn btn-danger">Cancel</a>' : 'WTH'}
	  		</c:forEach> --%>
	  		</tbody>
		</table>
	</div>	<!-- close row -->
	</div><!-- close col-12 -->
</div>
</div>
<script src="js/myjs.js"></script>
<script type="text/javascript">
function orderBy(){
	var ordertype = document.getElementById("ordertype").value;
	var staffid = document.getElementById("id").value;
	console.log(ordertype);
	$.ajax({
		type : 'get',
		dataType: 'json',
		url : 'OrderController',
		data : {
			action : "orderby",
			status : ordertype,
			staff_id : staffid
		},
		success : function(responseOrder){
			console.log(responseOrder);
			var myorder = '';
			$.each(responseOrder, function(key, order){
				const xbutton = order.status == 'Pending' || order.status == 'Processing' ?
						'<a href="OrderController?action=deleteorder&order_id='+order.id+'&role=staff" class="btn btn-danger">Cancel</a>'
						:"";
				var staffName = order.staff_name;
				if(order.staff_name === undefined){
					staffName = "";
				}
				let bg = "";
				let font = "white";
				if(order.status == "Completed"){
					bg = "success";
				} else if(order.status == "Processing"){
					bg = "primary";
				} else if(order.status == "Pending"){
					bg = "warning";
					font = "black"
				} else {
					bg = "danger";
				}
				
				myorder +=
				`<tr>
	  				<td>\${order.id}</td>
	  				<td>\${order.date}</td>
	  				<td>RM\${order.total_price}</td>
	  				<td>\${staffName}</td>
	  				<td class="bg-\${bg} text-\${font} font-weight-bold">\${order.status}</td>
	  				<td>
	  					<a href="OrderController?action=viewitems&order_id=\${order.id}&role=staff" class="btn btn-primary">View</a>
	  					\${xbutton}
	  				</td>
	  			</tr>`;
			});
			myorder += '';
			$('#tbody').html(myorder);
		}
	});
}

$(window).load(orderBy());
</script>
</body>
</html>