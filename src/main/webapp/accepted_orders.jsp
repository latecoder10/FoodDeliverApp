<%@ page
    import="com.food.modules.Order,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

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
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-2 m-0">Accepted Orders</h2>
                </div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false"> <i class="fas fa-user me-2"></i>John
                                Doe</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <%
                List<Order> acceptedOrders = (List<Order>) request.getAttribute("aceptedOrders");
                if (acceptedOrders == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            %>

            <div class="container-fluid px-4">
                <div class="row my-5">
                	<div class= row>
                    <h3 class="fs-4 mb-3">Accepted Orders</h3>

                    <!-- Filtering and Sorting Controls -->
                    <div class="col mb-3">
                        <form id="filterForm">
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="orderStatus">Order Status:</label>
                                    <select id="orderStatus" class="form-select" onchange="filterOrders()">
                                        <option value="">All</option>
                                        <option value="IN_PROGRESS">In Progress</option>
                                        <option value="DELIVERED">Delivered</option>
                                    </select>
                                </div>

                                <div class="col-md-4">
                                    <label for="paymentMethod">Payment Method:</label>
                                    <select id="paymentMethod" class="form-select" onchange="filterOrders()">
                                        <option value="">All</option>
                                        <option value="CREDIT_CARD">Credit Card</option>
                                        <option value="DEBIT_CARD">Debit Card</option>
                                        <option value="CASH">Cash</option>
                                        <option value="UPI">Upi</option>
                                    </select>
                                </div>

                                <div class="col-md-4">
                                    <label for="sortSelect">Sort By:</label>
                                    <select id="sortSelect" class="form-select" onchange="sortOrders()">
                                        <option value="">Select</option>
                                        <option value="orderIdAsc">Order ID Ascending</option>
                                        <option value="orderIdDesc">Order ID Descending</option>
                                        <option value="userIdAsc">User ID Ascending</option>
                                        <option value="userIdDesc">User ID Descending</option>
                                        <option value="orderDateAsc">Order Date Ascending</option>
                                        <option value="orderDateDesc">Order Date Descending</option>
                                        <option value="totalAmountAsc">Total Amount Ascending</option>
                                        <option value="totalAmountDesc">Total Amount Descending</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
					</div>
                    <div class="col">
                        <table class="table bg-white rounded shadow-sm table-hover" id="ordersTable">
                            <thead>
                                <tr>
                                    <th scope="col" width="50">Order ID</th>
                                    <th scope="col">User ID</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Total Amount</th>
                                    <th scope="col">Order Status</th>
                                    <th scope="col">Payment Method</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Order order : acceptedOrders) { %>
                                    <tr data-order-id="<%= order.getOrderId() %>"
                                        data-user-id="<%= order.getUserId() %>"
                                        data-order-date="<%= order.getOrderDate() %>"
                                        data-total-amount="<%= order.getTotalAmount() %>"
                                        data-order-status="<%= order.getOrderStatus() %>"
                                        data-payment-method="<%= order.getPaymentMethod() %>">
                                        <th scope="row"><%= order.getOrderId() %></th>
                                        <td>U<%= order.getUserId() %></td>
                                        <td><%= order.getOrderDate() %></td>
                                        <td>$<%= order.getTotalAmount() %></td>
                                        <td><%= order.getOrderStatus() %></td>
                                        <td><%= order.getPaymentMethod() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <!-- /#page-content-wrapper -->

        
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="dashboard/dashboardIndex.js"></script>
        <script src="dashboard/filterOrders.js"></script>
        
</body>

</html>
