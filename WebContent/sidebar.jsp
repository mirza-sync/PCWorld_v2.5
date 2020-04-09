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
		<div class="col-md-2 sidebar px-0">
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
	<!-- <nav class="col-md-2 d-none d-md-block bg-secondary sidebar">
      <div class="sidebar-sticky">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="#">
              <span data-feather="home"></span>
              Dashboard <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file"></span>
              Orders
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="shopping-cart"></span>
              Products
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="users"></span>
              Customers
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="bar-chart-2"></span>
              Reports
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers"></span>
              Integrations
            </a>
          </li>
        </ul>

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>Saved reports</span>
          <a class="d-flex align-items-center text-muted" href="#">
            <span data-feather="plus-circle"></span>
          </a>
        </h6>
        <ul class="nav flex-column mb-2">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Current month
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Last quarter
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Social engagement
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file-text"></span>
              Year-end sale
            </a>
          </li>
        </ul>
      </div>
    </nav> -->
	</c:when>
</c:choose>