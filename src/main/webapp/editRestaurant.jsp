<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.food.modules.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Restaurant</title>
    <link rel="stylesheet" type="text/css" href="CSS_Properties/style1.css">
</head>
<body>
    <div class="container">
        <h2>Edit Restaurant</h2>

        <% 
            Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
            if (restaurant != null) {
        %>
        <form action="/FoodDeliverApp/updateRestaurant" method="post" class="form-container">
            <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>" />

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= restaurant.getName() %>" />

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= restaurant.getAddress() %>" />

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="<%= restaurant.getPhoneNumber() %>" />

            <label for="rating">Rating:</label>
            <input type="number" id="rating" name="rating" step="0.1" value="<%= restaurant.getRating() %>" />

            <label for="cuisineType">Cuisine Type:</label>
            <input type="text" id="cuisineType" name="cuisineType" value="<%= restaurant.getCuisineType() %>" />

            <label for="active">Active:</label>
            <select id="active" name="isActive">
                <option value="true" <%= restaurant.isActive() ? "selected" : "" %>>Yes</option>
                <option value="false" <%= !restaurant.isActive() ? "selected" : "" %>>No</option>
            </select>

            <label for="estimateTimeArrival">Estimated Time Arrival:</label>
            <input type="text" id="estimateTimeArrival" name="estimateTimeArrival" value="<%= restaurant.getEstimateTimeArrival() %>" />

            <input type="submit" value="Update Restaurant" />
        </form>
        
           <form action="/FoodDeliverApp/deleteRestaurant" method="post" class="form-container" style="margin-top: 20px;">
            <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>" />
            <input type="submit" value="Delete Restaurant" style="background-color: #FF4C4C; color: white;" />
        </form>
        
        <% } else { %>
        <p>Restaurant not found.</p>
        <% } %>
    </div>
</body>
</html>
