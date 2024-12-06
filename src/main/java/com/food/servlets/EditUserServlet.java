package com.food.servlets;

import java.io.IOException;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;
import com.food.modules.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user_id from request parameter
        String userIdParam = request.getParameter("user_id");
        
        // Check if user_id is valid
        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                int userId = Integer.parseInt(userIdParam);

                // Fetch user details from DAO
                User user = userDAO.getUser(userId);

                if (user != null) {
                    // Set user object as request attribute
                    request.setAttribute("user", user);
                    // Forward to updateUser.jsp for editing
                    request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
                } else {
                    // Handle user not found scenario
                    response.sendRedirect("error.jsp"); // Redirect to error page or show a relevant message
                }
            } catch (NumberFormatException e) {
                // Handle invalid user_id format
                response.sendRedirect("error.jsp"); // Redirect to error page or show a relevant message
            }
        } else {
            // Handle missing user_id parameter
            response.sendRedirect("error.jsp"); // Redirect to error page or show a relevant message
        }
    }
}

