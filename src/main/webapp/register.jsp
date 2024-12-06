<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>

        <label for="user_name">Username:</label>
        <input type="text" id="user_name" name="user_name" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="phone_number">Phone Number:</label>
        <input type="text" id="phone_number" name="phone_number"><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address"><br><br>

        <label for="user_type">User Type:</label>
        <select id="user_type" name="user_type" required>
            <option value="CUSTOMER">Customer</option>
            <option value="STAFF">Staff</option>
            <option value="ADMIN">Restaurant Owner</option>
      
        </select><br><br>

        <input type="submit" value="Register">
    </form>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
