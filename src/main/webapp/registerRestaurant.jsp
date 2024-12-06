<%@page import="com.food.modules.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Restaurant</title>
    <link rel="stylesheet" type="text/css" href="CSS_Properties/style1.css">
</head>
<body>
	<%
		User registeredAdmin=(User)request.getAttribute("registerdAdmin");
		if(registeredAdmin==null){
			response.sendRedirect("error.jsp");
		}
	%>
    <div class="container">
        <h2>Register Restaurant</h2>
        
        <form action="registerRestaurant" method="post" class="form-container">
           <input type="hidden" name="registerdAdminUID" value="<%=registeredAdmin.getUserId() %>">
            <label for="name">Restaurant Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <label for="phone_number">Phone Number:</label>
            <input type="text" id="phone_number" name="phone_number" required>

            <label for="rating">Rating:</label>
            <input type="number" id="rating" name="rating" step="0.1" min="0" max="5" required>

            <label for="cuisine_type">Cuisine Type:</label>
            <input type="text" id="cuisine_type" name="cuisine_type" required>

            <label for="is_active">Active:</label>
            <select id="is_active" name="is_active">
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select>

            <label for="estimate_time_arrival">Estimated Time Arrival:</label>
            <input type="text" id="estimate_time_arrival" name="estimate_time_arrival" required>

            <input type="submit" value="Register Restaurant">
        </form>
    </div>
</body>
</html>
