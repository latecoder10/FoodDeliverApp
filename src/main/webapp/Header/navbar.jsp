<%@page import="com.food.modules.User" %>
<section id="navigation">
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img src="images/logo1.gif"
				alt="Logo"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Search</a></li>

					<!-- Cart Icon -->
					<li class="nav-item"><a class="nav-link" href="cart_page.jsp">
							<img src="images/cart_icon.png" alt="Cart"
							style="width: 30px; height: 30px;"> <span id="cart-count"
							class="badge bg-danger">0</span>
					</a></li>
					<%
					HttpSession userSession = request.getSession(false);
					User user=(User)userSession.getAttribute("user");
					%>
					<%
					if (userSession != null && userSession.getAttribute("user") != null) {
						
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<img src="<%=user.getProfilePicture() %>" alt="Account"
							style="width: 30px; height: 30px; border-radius: 50%;" onerror="this.src='images/User-Profile-default.png';">
					</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="customerDashboard.jsp">Your Profile</a></li>
							<li><a class="dropdown-item" href="yourOrders.jsp">Your Orders</a></li>
							<li><a class="dropdown-item" href="logout">Logout</a></li>
						</ul></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link" href="#"
						data-bs-toggle="modal" data-bs-target="#loginModal">Login</a></li>
					<%
					}
					%>

				</ul>
			</div>
		</div>
	</nav>
</section>