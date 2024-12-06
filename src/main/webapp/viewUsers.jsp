<%@page import="java.util.List, com.food.modules.User, com.food.DAO.UserDAO, com.food.DAOImpl.UserDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Users</title>
    <style>
        /* Add your CSS here */
    </style>
</head>
<body>
    <h2>All Users</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Address</th>
            <th>User Type</th>
            <th>Actions</th>
        </tr>
        <% 
            UserDAO userDAO = new UserDAOImpl();
            List<User> users = userDAO.getAllUsers();
            for (User u : users) {
            	System.out.println(u);
        %>
        <tr>
            <td><%= u.getUserId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getUserName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getPhoneNumber() %></td>
            <td><%= u.getAddress() %></td>
            <td><%= u.getUserType() %></td>
            <td>
                <a href="editUser?user_id=<%= u.getUserId() %>">Edit</a> |
                <a href="deleteUser?user_id=<%= u.getUserId() %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
    <br>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
