package com.food.servlets;

import java.io.IOException;
import java.util.List;

import com.food.DAO.MenuDAO;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.modules.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDAO menuDAO = new MenuDAOImpl();
		String parameterId = request.getParameter("restaurant_id");
		
		try {
			List<Menu> allMenusByRestaurant = menuDAO.getAllMenusByRestaurant(Integer.parseInt(parameterId));
			for(Menu menu :allMenusByRestaurant) {
				System.out.println(menu);
				
			}
			request.setAttribute("Menus",allMenusByRestaurant);
			request.getRequestDispatcher("/testing.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
