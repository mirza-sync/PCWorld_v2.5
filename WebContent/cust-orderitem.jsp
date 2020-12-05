<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<title>My Cart</title>
</head>
<body>
<div class="container-fluid">
<%@include file="sidebar.jsp"%>
<div id="main" class="row">
	<div class="col-md-12">
		<form action="OrderController" method="get">
		<ul class="list-group mb-5 pb-5">
			<c:forEach items="${orderitems}" var="item">
				<li class="list-group-item d-flex media position-relative">
					<c:if test="${order.status == 'Draft'}">
						<a onclick="deleteItem(${item.component.id},${order.id},event)" class="position-absolute btn btn-sm btn-danger" style="top:0;left:0;margin-top:5px;margin-left:5px">
							<i class="fa fa-times" style="color:#fff"></i>
						</a>
					</c:if>
					<img src="${item.component.image}" class="ml-3 mr-5 align-self-center" style="width:50px; height:50px"/>
					<div class="media-body">
						<div class="d-flex justify-content-between">
							<div>
								${item.component.type} : <a href="ComponentController?action=showEdit&id=${item.component.id}&type=${item.component.type}">${item.component.brand} ${item.component.model}</a>
							</div>
							<div>RM <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.component.price}"/></div>
							<input type="hidden" id="price${item.component.id}" value="${item.component.price}">
						</div>
						<hr>
						<div class="float-right">
							<c:choose>
								<c:when test="${order.status == 'Draft'}">
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<button type="button" class="btn btn-primary" onclick="minus(${item.component.id},${order.id})">-</button>
										</div>
										<input style="width: 50px;" id="quantity${item.component.id}" onchange="calculate()" class="pl-3" type="text" name="quantityArray" value="${item.quantity}">
										<div class="input-group-append">
											<button type="button" class="btn btn-primary" onclick="plus(${item.component.id},${order.id})">+</button>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="d-block">
										<small class="text-muted font-weight-bold">qty : </small>
										<strong>${item.quantity}</strong>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
		<div class="fixed-bottom bg-light pr-4">
			<div class="m-3 float-right">
				<div>
					<span class="font-weight-bold mr-3">Total : RM <span id="total"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}"/></span></span>
					<input type="hidden" id="h-total" name="total_price" value="${total}">
					<c:if test="${order.status == 'Draft'}">
						<button type="submit" class="btn btn-success">Confirm Order</button>
						<input type="hidden" name="action" value="confirm">
						<input type="hidden" name="order_id" value="${order.id}">
					</c:if>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>
</div>
<script src="js/myjs.js"></script>
<script type="text/javascript">
function deleteItem(compid, orderid, e) {
	$.ajax({
		type : 'get',
		url : 'OrderController',
		data : {
			action : "deleteitem",
			component_id : compid,
			order_id : orderid
		},
		success : function(){
			console.log("Done delete item");
		}
	});
	var tr = e.target.parentNode.parentNode;
	tr.parentNode.removeChild(tr);
	calculate();
}
function plus(comp_id,o_id) {
	var num = 0;
	$('#quantity'+comp_id).val(function(i, value){
		num = parseFloat(value, 10) + 1;
		return num;
	});
	calculate();
	
	$.ajax({
		type : 'get',
		url : 'OrderController',
		data : {
			action : "updateitem",
			component_id : comp_id,
			order_id : o_id,
			quantity : num
		},
		success : function(){
			console.log("Done + item");
		}
	});
}
function minus(comp_id,o_id) {
	var num = 0;
	$('#quantity'+comp_id).val(function(i, value){
		if(value <= 1){
			console.log("Less than 1!");
			return num = parseFloat(1, 10);
		}
		else{
			console.log("Decrement");
	        return num = parseFloat(value, 10) - 1;
		}
	});
	calculate();
	
	$.ajax({
		type : 'get',
		url : 'OrderController',
		data : {
			action : "updateitem",
			component_id : comp_id,
			order_id : o_id,
			quantity : num
		},
		success : function(){
			console.log("Done - item");
		}
	});
}
function calculate() {
	var total = 0;
	var prices = document.querySelectorAll('[id^="price"]');
	var qtys = document.querySelectorAll('[id^="quantity"]');
	for(var i = 0; i<prices.length; i++){
		total += parseFloat(prices[i].value)*parseFloat(qtys[i].value);
	}
	total = total.toFixed(2);
	return $('#total').text(total) && $('#h-total').val(total);
}
</script>
</body>
</html>