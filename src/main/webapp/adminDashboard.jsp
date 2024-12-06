<%@ page
	import="com.food.modules.Restaurant, com.food.modules.User,com.food.modules.Order,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet" href="dashboard/dashboard.css">
<title>Bootstrap 5 Responsive Admin Dashboard</title>

</head>

<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar -->
		<%@include file="dashboard/sidebar.jsp"%>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<nav
				class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
				<div class="d-flex align-items-center">
					<i class="fas fa-align-left primary-text fs-4 me-3"
						id="menu-toggle"></i>
					<h2 class="fs-2 m-0">Dashboard</h2>
				</div>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle second-text fw-bold" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> <i class="fas fa-user me-2"></i>John
								Doe
						</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">Profile</a></li>
								<li><a class="dropdown-item" href="#">Settings</a></li>
								<li><a class="dropdown-item" href="logout">Logout</a></li>
							</ul></li>
					</ul>
				</div>
			</nav>

			<div class="container-fluid px-4">
				<!-- Availability Button -->
				<div class="availability-container">
					<%
					Restaurant restaurant = null;

					if (session != null) {
						restaurant = (Restaurant) session.getAttribute("restaurant");
					}

					boolean isAvailable = (restaurant != null) && restaurant.isActive();
					%>
					<button id="availabilityBtn"
						class="btn <%=isAvailable ? "btn-outline-success" : "btn-outline-danger"%> availability-btn">
						<%=isAvailable ? "Available" : "Unavailable"%>
					</button>
				</div>

				<div class="row g-3 my-2">
					<!-- Duration Selection -->
					<div class="col-md-12 text-center">
						<div class="btn-group" role="group"
							aria-label="Duration Selection">
							<button type="button"
								class="btn btn-outline-primary duration-btn"
								data-duration="today" >Today</button>
							<button type="button"
								class="btn btn-outline-primary duration-btn"
								data-duration="weekly">Weekly</button>
							<button type="button"
								class="btn btn-outline-primary duration-btn"
								data-duration="monthly">Monthly</button>
							<button type="button"
								class="btn btn-outline-primary duration-btn"
								data-duration="yearly">Yearly</button>
							<button type="button"
								class="btn btn-outline-primary duration-btn"
								data-duration="allTime">All Time</button>
						</div>
					</div>
					<!-- Hidden inputs for dynamic data -->
					
				    <input type="hidden" id="ordersReceived" value="<%= request.getAttribute("ordersReceived") %>">
				    <input type="hidden" id="ordersDelivered" value="<%= request.getAttribute("ordersDelivered") %>">
				    <input type="hidden" id="ordersCancelled" value="<%= request.getAttribute("ordersCancelled") %>">
				    <input type="hidden" id="ordersPending" value="<%= request.getAttribute("ordersPending") %>">
				    <input type="hidden" id="ordersInProgress" value="<%= request.getAttribute("ordersInProgress") %>">
				    <input type="hidden" id="totalOrderAmount" value="<%= request.getAttribute("totalOrderAmount") %>">
				    <%
				    
				    	System.out.println("chartData :" +request.getAttribute("ordersReceived")+" "+request.getAttribute("ordersDelivered")+" "+request.getAttribute("ordersCancelled")+" "+request.getAttribute("ordersPending")+" "+request.getAttribute("ordersInProgress")+" "+request.getAttribute("totalOrderAmount") );
				    
				    %>
					<!-- Orders Received Chart -->
					<div class="col-md-3">
						<canvas id="ordersReceivedChart"></canvas>
						<p class="fs-5">Orders Received</p>
					</div>
					<!-- Orders Delivered Chart -->
					<div class="col-md-3">
						<canvas id="ordersDeliveredChart"></canvas>
						<p class="fs-5">Orders Delivered</p>
					</div>
					<!-- Orders Cancelled Chart -->
					<div class="col-md-3">
						<canvas id="ordersCancelledChart"></canvas>
						<p class="fs-5">Orders Cancelled</p>
					</div>
					<!-- Total Order Amount Chart -->
					<div class="col-md-3">
						<canvas id="totalOrderAmountChart"></canvas>
						<p class="fs-5">Total Order Amount</p>
					</div>
				</div>
				<div class="row my-5">
					<h3 class="fs-4 mb-3">Recent Orders</h3>

					<div class="col">
						<table class="table bg-white rounded shadow-sm table-hover">
							<thead>
								<tr>
									<th scope="col" width="50">Order ID</th>
									<th scope="col">User ID</th>
									<th scope="col">Order Date</th>
									<th scope="col">Amount</th>
									<th scope="col">Status</th>
									<th scope="col">Payment Method</th>
									<th scope="col" width="150">Actions</th>
								</tr>
							</thead>
							<tbody>
								<%
								// Retrieve pending orders from the request attribute
								List<Order> pendingOrders = (List<Order>) request.getAttribute("pendingOrders");
								if (pendingOrders != null && !pendingOrders.isEmpty()) {
									for (Order order : pendingOrders) {
								%>
								<tr>
									<th scope="row"><%=order.getOrderId()%></th>
									<td><%=order.getUserId()%></td>
									<td><%=order.getOrderDate()%></td>
									<td>$<%=order.getTotalAmount()%></td>
									<td class="status"><%=order.getOrderStatus()%></td>
									<td><%=order.getPaymentMethod()%></td>
									<td class="text-center">
										<button class="btn btn-success btn-sm accept-btn"
											data-order-id="<%=order.getOrderId()%>">Accept</button>
										<button class="btn btn-danger btn-sm cancel-btn"
											data-order-id="<%=order.getOrderId()%>">Cancel</button>
									</td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td colspan="7" class="text-center">No pending orders
										found.</td>
								</tr>
								<%
								}
								%>
								
								<%
									System.out.println(pendingOrders);
								%>
							</tbody>
						</table>
					</div>
				</div>



			</div>
			<!-- /#page-content-wrapper -->
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>

		<script src="dashboard/dashboardIndex.js"></script>

	</div>
</body>

</html>
