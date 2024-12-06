<%@ page import="java.util.List, com.food.modules.OrderHistory, com.food.DAO.OrderHistoryDAO, com.food.DAOImpl.OrderHistoryDAOImpl, com.food.modules.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <link rel="stylesheet" href="CSS_Properties/test.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .order-table th, .order-table td {
            vertical-align: middle;
        }
        .order-status {
            text-transform: capitalize;
        }
    </style>
</head>
<body>
    <%@ include file="Header/navbar.jsp" %>

    <section class="container my-4">
        <%
            
            User user1 = (User) session.getAttribute("user");
            if (user1 == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            List<OrderHistory> orderHistories = orderHistoryDAO.getAllOrderHistoriesByUser(user.getUserId());
        %>

        <h1 class="text-center mb-4">Your Orders</h1>
        <% if (orderHistories.isEmpty()) { %>
            <div class="alert alert-info text-center">
                You have no orders yet.
            </div>
        <% } else { %>
            <table class="table table-striped table-bordered order-table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (OrderHistory orderHistory : orderHistories) { %>
                        <tr>
                            <td>Order#<%= orderHistory.getOrderId() %></td>
                            <td><%= orderHistory.getOrderDate() %></td>
                            <td>$<%= orderHistory.getTotalAmount() %></td>
                            <td class="order-status"><%= orderHistory.getStatus() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
    </section>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
