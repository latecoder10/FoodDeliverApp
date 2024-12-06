package com.food.servlets;

import java.io.IOException;
import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;
import com.food.modules.User;
import com.food.modules.User.UserType;
import com.food.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String address = request.getParameter("address");
        String userTypeStr = request.getParameter("user_type");

        if (ValidationUtil.isNullOrEmpty(userName) || ValidationUtil.isNullOrEmpty(password) || 
            ValidationUtil.isNullOrEmpty(email) || ValidationUtil.isNullOrEmpty(userTypeStr)) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidUserType(userTypeStr)) {
            request.setAttribute("error", "Invalid user type.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {
            UserType userType = UserType.valueOf(userTypeStr.toUpperCase());
            String hashedPassword = ValidationUtil.hashPassword(password);

            User user = new User();
            user.setUserId(generateNewUserId()); // Generate a unique user ID
            user.setName(name);
            user.setUserName(userName);
            user.setPassword(hashedPassword);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setUserType(userType);

            if (userDAO.addUser(user)) {
                request.setAttribute("success", "User registered successfully.");

                // Redirect based on user type
                if (userType == UserType.ADMIN) {
                	request.setAttribute("registerdAdmin", user);
                	System.out.println(user);
                    request.getRequestDispatcher("registerRestaurant.jsp").forward(request, response); // Redirect to Register Your Restaurant page
                } else {
                    response.sendRedirect("index.jsp"); // Redirect to index for CUSTOMER and STAFF
                }
            } else {
                request.setAttribute("error", "Username or email already exists.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid user type.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private int generateNewUserId() {
        // Implement user ID generation logic here. This should be unique for each user.
        return 1; // Placeholder implementation
    }
}
