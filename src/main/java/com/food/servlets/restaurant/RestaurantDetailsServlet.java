package com.food.servlets.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Menu;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
public class RestaurantDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;//fili/o operation we have to foll0w

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String requestURI = request.getRequestURI();
//        
//        // Ensure we only process URLs that are meant for restaurant details
//        if (requestURI.contains("/restaurants/") && !requestURI.endsWith(".jsp") && !requestURI.endsWith(".jpg")) {
//            String[] parts = requestURI.split("/");
//            String restaurantName = parts[parts.length - 1];
//
//            System.out.println("Requested URI: " + requestURI);
//            System.out.println("Extracted Restaurant Name: " + restaurantName);
//
//            RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
//            Restaurant restaurant = restaurantDAO.getRestaurantBySlug(restaurantName);
//
//            if (restaurant != null) {
//                request.setAttribute("restaurant", restaurant);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/restaurant_details.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                System.out.println("Restaurant not found, redirecting to error page.");
//                response.sendRedirect("errorPage.jsp");
//            }
//        } else {
//            // Forward to the default servlet or return 404 for unsupported resources
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
    	
    	
    	String parameter = request.getParameter("id");
    	
    	 if (parameter != null) {
             try {
                 int restaurantId = Integer.parseInt(parameter);
                 RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
                 Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

                 if (restaurant != null) {
                     request.setAttribute("restaurant", restaurant);
                     request.getRequestDispatcher("restaurant_details.jsp").forward(request, response);
                 } else {
                     response.sendRedirect("errorPage.jsp");
                 }
             } catch (NumberFormatException e) {
                 response.sendRedirect("errorPage.jsp");
             }
         } else {
             response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
    	
    	
    }

}
