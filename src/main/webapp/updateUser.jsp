<%@page import="java.util.List,com.food.modules.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h2>Update User</h2>
    <form action="updateUser" method="post">
        <input type="hidden" name="user_id" value="<%= ((User) request.getAttribute("user")).getUserId() %>">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= ((User) request.getAttribute("user")).getName() %>"><br><br>

        <label for="user_name">Username:</label>
        <input type="text" id="user_name" name="user_name" value="<%= ((User) request.getAttribute("user")).getUserName() %>" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br> <!-- Optional -->

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= ((User) request.getAttribute("user")).getEmail() %>" required><br><br>

        <label for="phone_number">Phone Number:</label>
        <input type="text" id="phone_number" name="phone_number" value="<%= ((User) request.getAttribute("user")).getPhoneNumber() %>"><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= ((User) request.getAttribute("user")).getAddress() %>"><br><br>

        <label for="user_type">User Type:</label>
        <select id="user_type" name="user_type" required>
            <option value="CUSTOMER" <%= "CUSTOMER".equals(((User) request.getAttribute("user")).getUserType().name()) ? "selected" : "" %>>Customer</option>
            <option value="STAFF" <%= "STAFF".equals(((User) request.getAttribute("user")).getUserType().name()) ? "selected" : "" %>>Staff</option>
            <option value="ADMIN" <%= "ADMIN".equals(((User) request.getAttribute("user")).getUserType().name()) ? "selected" : "" %>>Admin</option>
        </select><br><br>

        <input type="submit" value="Update">
    </form>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
