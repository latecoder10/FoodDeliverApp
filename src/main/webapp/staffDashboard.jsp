<%@page import="com.food.modules.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
        h2 {
            text-align: center;
        }
        .profile-info {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .profile-info label {
            font-weight: bold;
        }
        .profile-info p {
            margin: 0;
        }
        .actions {
            text-align: center;
        }
        .actions a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }
        .actions a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Staff Dashboard</h2>
        
        <div class="profile-info">
            <h3>Profile Information</h3>
            <% 
                // Retrieve user object from session
                User user = (User) session.getAttribute("user");
                
                if (user != null) {
            %>
            <p><strong>ID:</strong> <%= user.getUserId() %></p>
            <p><strong>Name:</strong> <%= user.getName() != null ? user.getName() : "N/A" %></p>
            <p><strong>Username:</strong> <%= user.getUserName() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() != null ? user.getEmail() : "N/A" %></p>
            <p><strong>Phone Number:</strong> <%= user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A" %></p>
            <p><strong>Address:</strong> <%= user.getAddress() != null ? user.getAddress() : "N/A" %></p>
            <p><strong>User Type:</strong> <%= user.getUserType() %></p>
            <% } else { %>
            <p>No user information available. Please log in.</p>
            <% } %>
        </div>
        
        <div class="actions">
            <% if (user != null) { %>
                <a href="editUser?user_id=<%= user.getUserId() %>">Edit Profile</a>
                <br><br>
            <% } %>
            <a href="logout">Logout</a>
        </div>
    </div>
</body>
</html>
