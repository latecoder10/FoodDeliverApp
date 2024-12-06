package com.food.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Restaurant;
import com.food.util.DBConnectionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	
	Connection connection=null;
	@Override
		public void init() throws ServletException {
			connection = DBConnectionUtil.getConnection();
			
		}
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
			List<Restaurant> allRestaurant = restaurantDAOImpl.getAllRestaurant();
			req.setAttribute("restaurantList", allRestaurant);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.include(req, resp);
		}

}
