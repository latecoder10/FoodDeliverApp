<%@page import="java.util.List, com.food.modules.Menu, com.food.DAO.UserDAO, com.food.DAOImpl.UserDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		
	%>

	<h2>Menus</h2>
    <table border="1">
        <tr>
            <th>MenuId</th>
            <th>restaurantId</th>
            <th>ItemName</th>
            <th>Description</th>
            <th>Price</th>
            <th>IsAvailable</th>
           
        </tr>
        <% 
            UserDAO userDAO = new UserDAOImpl();
	        List<Menu> menus=(List<Menu>)request.getAttribute("Menus");
			for(Menu menu:menus){
				
			
        %>
        <tr>
            <td><%= menu.getMenuId() %></td>
            <td><%= menu.getRestaurantId() %></td>
            <td><%= menu.getItemName() %></td>
            <td><%= menu.getDescription() %></td>
            <td><%= menu.getPrice() %></td>
            <td><%= menu.getIsAvailable() %></td>
       
        </tr>
        <% } %>
    </table>

</body>
</html>