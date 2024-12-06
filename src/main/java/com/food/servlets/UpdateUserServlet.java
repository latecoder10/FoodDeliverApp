package com.food.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;
import com.food.modules.User;
import com.food.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/updateUser")
@MultipartConfig
public class UpdateUserServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract parameters from the form
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String name = request.getParameter("name");
            String userName = request.getParameter("user_name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone_number");
            String address = request.getParameter("address");
            User.UserType userType = User.UserType.valueOf(request.getParameter("user_type"));

            // Perform basic validations
            if (userName == null || userName.isEmpty() || email == null || email.isEmpty()) {
                request.setAttribute("error", "Username and Email are required.");
                request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
                return;
            }

            if (!ValidationUtil.isValidEmail(email)) {
                request.setAttribute("error", "Invalid email format.");
                request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
                return;
            }

            // Handle the profile picture upload
            String imagePath = null;
            Part filePart = request.getPart("profilePicture");
            String uploadPath = getUploadPath(userId);
            File userUploadDir = new File(uploadPath);

            // Check if directory exists, create if not
            if (!userUploadDir.exists()) {
                userUploadDir.mkdirs();
            }

            // Check for existing profile picture
            String existingImagePath = userDAO.getUser(userId).getProfilePicture();
            if (existingImagePath != null && !existingImagePath.isEmpty()) {
                File existingFile = new File(uploadPath, existingImagePath.substring(existingImagePath.lastIndexOf("/") + 1));
                if (existingFile.exists()) {
                    existingFile.delete(); // Delete the old image
                }
            }

            // Upload the new image
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
                File newFile = new File(userUploadDir, fileName);
                
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                imagePath = "uploads/" + userId + "/" + fileName; // Relative path for the database
            }

            // Create a User object
            User user = new User(userId, name, userName, password, email, phoneNumber, address, userType);
            if (imagePath != null) {
                user.setProfilePicture(imagePath); // Set the new profile picture path
            }

            // Update user in the database
            userDAO.updateUser(user);
            
            // Redirect to a success page 
            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating user: " + e.getMessage());
            request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
        }
    }

    private String getUploadPath(int userId) {
        boolean isDevelopment = true; // Set to false for production
        if (isDevelopment) {
            return "C:/Users/Ayan Pal/eclipse-workspace5/FoodDeliverApp/src/main/webapp/uploads/" + userId;
        } else {
            return getServletContext().getRealPath("/uploads") + File.separator + userId;
        }
    }
}
