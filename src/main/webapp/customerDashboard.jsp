<%@ page import="java.util.List, com.food.modules.OrderHistory, com.food.DAO.OrderHistoryDAO, com.food.DAOImpl.OrderHistoryDAOImpl, com.food.modules.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .profile-info {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .profile-header {
            text-align: center;
        }
        .profile-header img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            border: 2px solid #007bff;
            margin-bottom: 15px;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .card {
            border-radius: 10px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .btn-primary, .btn-warning, .btn-danger {
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">YourApp</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-4">
        <h2 class="text-center">Customer Dashboard</h2>

        <div class="profile-info">
            <div class="profile-header">
                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                %>
                <img src="<%= user.getProfilePicture() %>" alt="Profile Picture" onerror="this.src='images/User-Profile-default.png';">
                <h4><%= user.getName() %></h4>
                <p><strong>ID:</strong> <%= user.getUserId() %></p>
                <p><strong>User Type:</strong> <%= user.getUserType() %></p>
                <%
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                %>
            </div>

            <form action="updateUser" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="user_id" value="<%= user != null ? user.getUserId() : "" %>">
                <input type="hidden" name="user_type" value="<%= user != null ? user.getUserType() : "" %>">
                
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" class="form-control" value="<%= user != null ? user.getName() : "" %>" required>
                </div>
                <div class="form-group">
                    <label for="user_name">Username</label>
                    <input type="text" name="user_name" class="form-control" value="<%= user != null ? user.getUserName() : "" %>" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" value="<%= user != null ? user.getPassword() : "" %>" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" class="form-control" value="<%= user != null ? user.getEmail() : "" %>" required>
                </div>
                <div class="form-group">
                    <label for="phone_number">Phone Number</label>
                    <input type="text" name="phone_number" class="form-control" value="<%= user != null ? user.getPhoneNumber() : "" %>">
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type="text" name="address" class="form-control" value="<%= user != null ? user.getAddress() : "" %>">
                </div>
                <div class="form-group">
                    <label for="profilePicture">Profile Picture</label>
                    <input type="file" name="profilePicture" accept="image/*" class="form-control-file">
                </div>
                <button type="submit" class="btn btn-primary">Update Profile</button>
            </form>
        </div>

        <%
        // Check if user is logged in before accessing order history
        if (user != null) {
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            List<OrderHistory> orderHistories = orderHistoryDAO.getAllOrderHistoriesByUser(user.getUserId());
        %>

        <div class="card mb-4">
            <div class="card-header">
                Recent Orders
            </div>
            <div class="card-body">
                <ul class="list-group">
                    <% if (orderHistories.isEmpty()) { %>
                        <li>No Orders yet!</li>
                    <% } else {
                        // Calculate the start index for the recent orders
                        int startIndex = Math.max(0, orderHistories.size() - 3);
                        for (int i = orderHistories.size() - 1; i >= startIndex; i--) {
                    %>
                        <li class="list-group-item">Order #<%= orderHistories.get(i).getOrderId() %> - <%= orderHistories.get(i).getStatus() %></li>
                    <% 
                        }
                    } 
                    %>
                </ul>
                <div class="text-right">
                    <a href="yourOrders.jsp" class="btn btn-secondary">View All Orders</a>
                </div>
            </div>
        </div>

        <div class="card mb-4">
            <div class="card-header">
                Account Settings
            </div>
            <div class="card-body">
                <p>Manage your account settings, payment methods, and privacy options.</p>
                <a href="settings" class="btn btn-warning">Go to Settings</a>
            </div>
        </div>

        <div class="text-center">
            <a href="logout" class="btn btn-danger">Logout</a>
        </div>
        <% } %> <!-- Close user check -->
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
