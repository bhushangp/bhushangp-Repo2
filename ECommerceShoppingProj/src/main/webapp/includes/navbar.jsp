<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp"><h4>ECommerce Shopping Cart</h4></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp"><h6>Home </h6></a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp"><h6>Cart<span class="badge badge-danger px-1">${cartlist.size()} </span></h6></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="orders.jsp"><h6>Orders</h6></a>
				</li>
				<%
				if (request.getSession().getAttribute("user") != null) {
				%>

				<li class="nav-item"><a class="nav-link" href="LogoutServlet"><h6>Logout</h6></a></li>

				</li>
				<%
				} else
				{%>
				<li class="nav-item"><a class="nav-link" href="login.jsp"><h6>Login</h6></a>
				<%} %>
			</ul>
		</div>
	</div>
</nav>
