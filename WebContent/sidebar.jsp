<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="false">
		<div class="col-md-2 bg-secondary">
			<nav class="nav bg-dark text-white h-100 flex-column">
				<h2 class="nav-link"><a href="main.jsp">PCWorld</a></h2>
				<a href="ComponentController?action=viewComponents&type=CPU" class="nav-link">CPU</a>
				<a href="ComponentController?action=viewComponents&type=GPU" class="nav-link">GPU</a>
				<a href="ComponentController?action=viewComponents&type=Motherboard" class="nav-link">Motherboard</a>
				<a href="ComponentController?action=viewComponents&type=RAM" class="nav-link">RAM</a>
				<a href="ComponentController?action=viewComponents&type=Storage" class="nav-link">Storage</a>
				<a href="ComponentController?action=viewComponents&type=PSU" class="nav-link">PSU</a>
				<a href="ComponentController?action=viewComponents&type=Casing" class="nav-link">Casing</a>
			</nav>
		</div>
	</c:when>
	<c:when test="true">
		<div class="sidenav" id="mySidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<ul class="nav bg-dark text-white h-100 flex-column list-group">
				<h2 class="nav-link"><a href="main.jsp">PCWorld</a></h2>
 				<li class="list-group-item">
					<a href="ComponentController?action=viewComponents&type=CPU">CPU</a>
				</li>
				<li class="list-group-item">
					<a href="ComponentController?action=viewComponents&type=GPU">GPU</a>
				</li>
				<li class="list-group-item">
					<a href="ComponentController?action=viewComponents&type=Motherboard">Motherboard</a>
				</li>
				<li class="nav-item">
					<a href="ComponentController?action=viewComponents&type=RAM" class="nav-link">RAM</a>
				</li>
				<li class="nav-item">
					<a href="ComponentController?action=viewComponents&type=Storage" class="nav-link">Storage</a>
				</li>
				<li class="nav-item">
					<a href="ComponentController?action=viewComponents&type=PSU" class="nav-link">PSU</a>
				</li>
				<li class="nav-item">
					<a href="ComponentController?action=viewComponents&type=Casing" class="nav-link">Casing</a>
				</li>
			</ul>
		</div>
	
	</c:when>
</c:choose>
<span id="menu" style="font-size:30px;cursor:pointer" class="m-3" onclick="openNav()">&#9776;Menu</span>