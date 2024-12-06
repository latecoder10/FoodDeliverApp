package com.food.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.food.DAO.MenuDAO;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.modules.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    private String createJsonResponse(boolean success, String message) {
        return String.format("{\"success\": \"%s\", \"message\": \"%s\"}", success ? "true" : "false", message);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(createJsonResponse(false, "User not logged in."));
            return;
        }

        // Retrieve the cart map from the session or initialize it if it does not exist
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Get current restaurant ID from the session
        Integer currentRestaurantId = (Integer) session.getAttribute("currentRestaurantId");
        if (currentRestaurantId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write(createJsonResponse(false, "No restaurant selected."));
            return;
        }

        // Get item details from request
        String itemId = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");
        int quantity;

        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write(createJsonResponse(false, "Invalid quantity: " + e.getMessage()));
            return;
        }

        // Check if the cart already contains items from a different restaurant
        boolean isDifferentRestaurant = false;
        MenuDAO menu = new MenuDAOImpl();
        for (String cartItemId : cart.keySet()) {
            // Assuming you can get restaurant ID by item ID; you should replace this with your actual logic
        	
            Integer itemRestaurantId = menu.getRestaurantIdByItemId(cartItemId); // Implement this method
            if (!currentRestaurantId.equals(itemRestaurantId)) {
                isDifferentRestaurant = true;
                break;
            }
        }

        if (isDifferentRestaurant) {
            // Clear the cart if it contains items from a different restaurant
            cart.clear();
        }

        // Add or update item in the cart
        cart.put(itemId, cart.getOrDefault(itemId, 0) + quantity);

        // Save the cart map back to the session
        session.setAttribute("cart", cart);

        // Send a successful response
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(createJsonResponse(true, "Item added to cart successfully."));
    }

    // Placeholder method; you need to implement this to get the restaurant ID based on item ID
    
}
