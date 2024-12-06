package com.food.servlets.restaurant;

import java.io.IOException;
import java.io.PrintWriter;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Restaurant; // Make sure to import your Restaurant class

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateAvailabilityServlet")
public class UpdateAvailabilityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        // Retrieve the 'available' parameter from the request
        String availableParam = request.getParameter("available");
        boolean available = Boolean.parseBoolean(availableParam);

        // Get the current session
        HttpSession session = request.getSession(false); // Get existing session, do not create a new one
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant"); // Retrieve the restaurant object from the session
        if (restaurant == null) {
            out.print("No restaurant found in session");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Update availability in the database using restaurant ID
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        boolean updateSuccess = restaurantDAO.updateAvailability(restaurant.getRestaurantId(),available); // Use the restaurant ID

        if (updateSuccess) {
            out.print("Availability updated successfully");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            out.print("Failed to update availability");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        out.flush();
      
    }
}
