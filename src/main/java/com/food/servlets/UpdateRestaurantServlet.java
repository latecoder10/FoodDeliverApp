package com.food.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.food.DAO.RestaurantDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.modules.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/updateRestaurant")
@MultipartConfig
public class UpdateRestaurantServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        BigDecimal rating = new BigDecimal(request.getParameter("rating"));
        String cuisineType = request.getParameter("cuisineType");
        Integer estimateTimeArrival = Integer.parseInt(request.getParameter("estimateTimeArrival"));
        boolean isActive = true;

        String imagePath = null;
        String uploadPath = getUploadPath(restaurantId);
        File uploadDir = new File(uploadPath);
        Part filePart = request.getPart("restaurantImage");

        // Fetch existing restaurant data
        Restaurant existingRestaurant = restaurantDAO.getRestaurantById(restaurantId);
        if (existingRestaurant == null) {
            System.out.println("Restaurant not found for ID: " + restaurantId);
            response.sendRedirect("adminDashboard.jsp?updateError=true");
            return;
        }

        // Use existing image path if no new image is uploaded
        String existingImagePath = existingRestaurant.getImagePath();

        // Handle file upload
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            if (!uploadDir.exists()) uploadDir.mkdir(); // Create directory if it doesn't exist

            // Optionally delete the old image if it exists
            if (existingImagePath != null && !existingImagePath.isEmpty()) {
                File existingFile = new File(uploadPath, existingImagePath.substring(existingImagePath.lastIndexOf("/") + 1));
                if (existingFile.exists()) {
                    existingFile.delete(); // Delete the old image
                }
            }

            // Save the new file
            File file = new File(uploadDir, fileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            imagePath = "uploads2/" + restaurantId + "/" + fileName; // Save the relative path to the database
        } else {
            // If no new image is uploaded, keep the existing image path
            imagePath = existingImagePath;
            System.out.println(imagePath);
        }

        // Create a new Restaurant object with updated data
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setRating(rating);
        restaurant.setCuisineType(cuisineType);
        restaurant.setActive(isActive);
        restaurant.setEstimateTimeArrival(estimateTimeArrival);
        restaurant.setImagePath(imagePath); // Use existing or new image path

        boolean rowsUpdated = restaurantDAO.updateRestaurant(restaurant);
        if (rowsUpdated) {
            response.sendRedirect("adminDashboard.jsp?updateSuccess=true");
        } else {
            response.sendRedirect("adminDashboard.jsp?updateError=true");
        }
    }


	private String getUploadPath(int restaurantId) {
		return "C:/Users/Ayan Pal/eclipse-workspace5/FoodDeliverApp/src/main/webapp/uploads2/" + restaurantId;
	}
}
