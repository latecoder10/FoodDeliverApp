package com.food.servlets.restaurant;

import java.io.IOException;
import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.modules.Order;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/acceptedOrders")
public class AcceptedOrdersServlet extends HttpServlet {
	private OrderDAO orderDAO;
	@Override
	public void init() throws ServletException {

		 orderDAO = new OrderDAOImpl();
	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int restaurantId=((Restaurant)session.getAttribute("restaurant")).getRestaurantId();
		List<Order> aceptedOrders = orderDAO.getAceptedOrdersByRestaurantId(restaurantId);
		request.setAttribute("aceptedOrders", aceptedOrders);
		request.getRequestDispatcher("/accepted_orders.jsp").forward(request, response);
	}

	

}
