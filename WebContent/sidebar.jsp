<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${session.role == 'customer'}">
		<div class="sidenav" id="mySidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<div class="h-100">
				<h1><a href="main.jsp">PCWorld</a></h1>
				<hr>
				<button class="dropdown-btn">PC Components 
					<i class="fas fa-caret-down"></i>
				</button>
				<div class="dropdown-container">
					<ul class="nav text-white flex-column list-group">
		 				<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=CPU">CPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=GPU">GPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Motherboard">Motherboard</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=RAM">RAM</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Storage">Storage</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=PSU">PSU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Casing">Casing</a>
						</li>
					</ul>
				</div>
				<a href="CustomerController?action=view&id=${session.id}">My Account</a>
				<a href="OrderController?action=orderlist&cust_id=${session.id}&role=customer">My Orders</a>
				<a href="LogoutController">Logout</a>
			</div>
		</div>
	</c:when>
	<c:when test="${session.role == 'staff'}">
		<div class="sidenav" id="mySidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<div class="h-100">
				<h1><a href="main.jsp">PCWorld</a></h1>
				<hr>
				<button class="dropdown-btn">Manage Components 
					<i class="fas fa-caret-down"></i>
				</button>
				<div class="dropdown-container">
					<ul class="nav text-white flex-column list-group">
		 				<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=CPU">CPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=GPU">GPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Motherboard">Motherboard</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=RAM">RAM</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Storage">Storage</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=PSU">PSU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Casing">Casing</a>
						</li>
					</ul>
				</div>
				<a href="OrderController?action=orderlist&role=staff">Orders</a>
				<a href="LogoutController">Logout</a>
			</div>
		</div>
	</c:when>
	<c:when test="${session.role == 'admin'}">
		<div class="sidenav" id="mySidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<div class="h-100">
				<h1><a href="main.jsp">PCWorld</a></h1>
				<hr>
				<a href="StaffController?action=view&id=${session.id}">My Account</a>
				<a href="StaffController?action=viewlist">Staff List</a>
				<a href="staff-register.jsp">New Staff</a>
				<a href="LogoutController">Logout</a>
			</div>
		</div>
	</c:when>
	<c:otherwise> <!-- GUEST -->
		<div class="sidenav" id="mySidenav">
			<div>
				<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			</div>
			<div class="h-100">
				<h1><a href="main.jsp">PCWorld</a></h1>
				<hr>
				<button class="dropdown-btn">PC Components 
					<i class="fas fa-caret-down"></i>
				</button>
				<div class="dropdown-container">
					<ul class="nav text-white flex-column list-group">
		 				<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=CPU">CPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=GPU">GPU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Motherboard">Motherboard</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=RAM">RAM</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Storage">Storage</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=PSU">PSU</a>
						</li>
						<li class="nav-item">
							<a href="ComponentController?action=viewComponents&type=Casing">Casing</a>
						</li>
					</ul>
				</div>
				<a href="cust-login.jsp">Log in</a>
				<a href="guest-register.jsp">Sign up</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>
<div class="d-flex justify-content-between pt-2 px-2">
	<div id="menu" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;Menu</div>
	<div class="d-flex align-self-center" style="font-size: 20px">
		<div class="mr-2">
			<c:choose>
				<c:when test="${session.role == 'customer'}">
					Welcome Customer : ${sessionname}
				</c:when>
				<c:when test="${session.role == 'staff'}">
					Welcome Staff : ${sessionname}
				</c:when>
				<c:when test="${session.role == 'admin'}">
					Welcome Admin : ${sessionname}
				</c:when>
				<c:otherwise>
					Welcome Guest
				</c:otherwise>
			</c:choose>
		</div>
			<c:choose>
				<c:when test="${session.role == 'customer'}">
					<a href="OrderController?action=orderlist&cust_id=${session.id}&role=customer" class="btn btn-success mx-1"><i class="fa fa-lg fa-shopping-cart"></i></a>
					<a href="CustomerController?action=view&id=${session.id}" class="btn btn-primary mx-1"><i class="fa fa-lg fa-user-circle"></i></a>
					<a href="LogoutController" class="btn btn-danger mx-1"><i class="fa fa-power-off"></i></a>
				</c:when>
				<c:when test="${session.role == 'staff'}">
					<a href="StaffController?action=view&id=${session.id}" class="btn btn-primary mx-1"><i class="fa fa-lg fa-user-circle"></i></a>
					<a href="LogoutController" class="btn btn-danger mx-1"><i class="fa fa-power-off"></i></a>
				</c:when>
				<c:when test="${session.role == 'admin'}">
					<a href="StaffController?action=view&id=${session.id}" class="btn btn-primary mx-1"><i class="fa fa-lg fa-user-circle"></i></a>
					<a href="LogoutController" class="btn btn-danger mx-1"><i class="fa fa-power-off"></i></a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		
		
	</div>
</div>