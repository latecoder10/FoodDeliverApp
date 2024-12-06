<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS_Properties/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .login-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container form {
            display: flex;
            flex-direction: column;
        }
        .login-container label {
            margin-bottom: 5px;
            font-weight: bold;
            text-align: left;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container input[type="submit"] {
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #28a745;
            color: #fff;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #218838;
        }
        .login-container p {
            margin-top: 10px;
        }
        .login-container .success {
            color: green;
        }
        .login-container .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <% if (request.getAttribute("success") != null) { %>
            <p class="success"><%= request.getAttribute("success") %></p>
        <% } %>
        <h2>Login</h2>
        <form action="login" method="post">
            <label for="user_name">Username:</label>
            <input type="text" id="user_name" name="user_name" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">

            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
        </form>
    </div>
</body>
</html>
