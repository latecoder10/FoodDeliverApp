package com.food.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.RestaurantDAO;
import com.food.modules.Restaurant;
import com.food.util.DBConnectionUtil;

public class RestaurantDAOImpl implements RestaurantDAO {

	@Override
	public boolean addRestaurant(Restaurant restaurant) {
		 String sql = "INSERT INTO restaurants (name, address, phone_number, rating, cuisine_type, isActive, estimate_time_arrival, admin_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        boolean isUpdated=false;
	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
	            
	            pstmt.setString(1, restaurant.getName());
	            pstmt.setString(2, restaurant.getAddress());
	            pstmt.setString(3, restaurant.getPhoneNumber());
	            pstmt.setBigDecimal(4, restaurant.getRating());
	            pstmt.setString(5, restaurant.getCuisineType());
	            pstmt.setBoolean(6, restaurant.isActive());
	            pstmt.setInt(7, restaurant.getEstimateTimeArrival());
	            pstmt.setInt(8, restaurant.getAdminUserId());

	            int rowsUpdated = pstmt.executeUpdate();
	            isUpdated = (rowsUpdated > 0);
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	        return isUpdated;
	}

	@Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurants WHERE restaurant_id = ?";
        
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
             
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhoneNumber(rs.getString("phone_number"));
                restaurant.setRating(rs.getBigDecimal("rating"));
                restaurant.setCuisineType(rs.getString("cuisine_type"));
                restaurant.setActive(rs.getBoolean("isActive"));
                restaurant.setEstimateTimeArrival(rs.getInt("estimate_time_arrival"));
                restaurant.setImagePath(rs.getString("image_path"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
        return restaurant;
    }


	@Override
	public boolean updateRestaurant(Restaurant restaurant) {
	    String sql = "UPDATE restaurants SET name = ?, address = ?, phone_number = ?, rating = ?, cuisine_type = ?, isActive = ?, estimate_time_arrival = ?, image_path = ? WHERE restaurant_id = ?";
	    boolean isUpdated = false;

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setString(1, restaurant.getName());
	        pstmt.setString(2, restaurant.getAddress());
	        pstmt.setString(3, restaurant.getPhoneNumber());
	        pstmt.setBigDecimal(4, restaurant.getRating());
	        pstmt.setString(5, restaurant.getCuisineType());
	        pstmt.setBoolean(6, restaurant.isActive());
	        pstmt.setInt(7, restaurant.getEstimateTimeArrival());
	        pstmt.setString(8, restaurant.getImagePath()); // Add this line to set the image path
	        pstmt.setInt(9, restaurant.getRestaurantId()); // Adjusted index for restaurantId
	        
	        int rowsUpdated = pstmt.executeUpdate();
	        isUpdated = (rowsUpdated > 0); // Update was successful if rowsUpdated > 0
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isUpdated;
	}


    @Override
    public boolean deleteRestaurant(int restaurantId) {
        String sql = "DELETE FROM restaurants WHERE restaurant_id = ?";
        boolean isDeleted = false;

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, restaurantId);
            int rowsDeleted = pstmt.executeUpdate();
            isDeleted = (rowsDeleted > 0); // Deletion was successful if rowsDeleted > 0
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

	@Override
	public List<Restaurant> getAllRestaurant() {
	    List<Restaurant> restaurants = new ArrayList<Restaurant>();
	    String query = "SELECT * FROM restaurants";
	    
	    try (Connection connection = DBConnectionUtil.getConnection();
	         Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery(query)) {
	        
	        // Iterate through the result set
	        while (resultSet.next()) {
	            // Extract data from the current row
	            int id = resultSet.getInt("restaurant_id"); // Use column name instead of index for clarity
	            String name = resultSet.getString("name");
	            String address = resultSet.getString("address");
	            String phone_number = resultSet.getString("phone_number");
	            BigDecimal rating = resultSet.getBigDecimal("rating");
	            String cuisine_type = resultSet.getString("cuisine_type");
	            boolean isActive = resultSet.getBoolean("isActive");
	            Integer etr = resultSet.getObject("estimate_time_arrival", Integer.class); // Use getObject for nullable fields
	            Integer admin_uid = resultSet.getObject("admin_user_id", Integer.class);
	            String image_path = resultSet.getString("image_path");
	            
	            // Create a new Restaurant object
	            Restaurant restaurant = new Restaurant(id, name, address, phone_number, rating, cuisine_type, isActive, etr, admin_uid, image_path);
	            
	            // Add the Restaurant object to the list
	            restaurants.add(restaurant);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return restaurants;
	}

	@Override
	public Restaurant getRestaurantByAdminUserId(int adminUserId) {
		 Restaurant restaurant = null;
	        try (Connection con = DBConnectionUtil.getConnection()) {
	            String sql = "SELECT * FROM restaurants WHERE admin_user_id = ?";
	            PreparedStatement pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, adminUserId);
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                restaurant = new Restaurant();
	                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
	                restaurant.setName(rs.getString("name"));
	                restaurant.setAddress(rs.getString("address"));
	                restaurant.setPhoneNumber(rs.getString("phone_number"));
	                restaurant.setRating(rs.getBigDecimal("rating"));
	                restaurant.setCuisineType(rs.getString("cuisine_type"));
	                restaurant.setActive(rs.getBoolean("isActive"));
	                restaurant.setEstimateTimeArrival(rs.getInt("estimate_time_arrival"));
	                restaurant.setAdminUserId(rs.getInt("admin_user_id"));
	                restaurant.setImagePath(rs.getString("image_path"));
	            }
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        return restaurant;
	    
		
	}
	
	@Override
	public Restaurant getRestaurantBySlug(String slug) {
	    Restaurant restaurant = null;
	    String query = "SELECT * FROM restaurants WHERE LOWER(REPLACE(name, ' ', '-')) = ?";
	    
	    try (Connection connection = DBConnectionUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        preparedStatement.setString(1, slug);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                // Extract data from the result set
	                int id = resultSet.getInt("restaurant_id");
	                String name = resultSet.getString("name");
	                String address = resultSet.getString("address");
	                String phoneNumber = resultSet.getString("phone_number");
	                BigDecimal rating = resultSet.getBigDecimal("rating");
	                String cuisineType = resultSet.getString("cuisine_type");
	                boolean isActive = resultSet.getBoolean("isActive");
	                Integer estimateTimeArrival = resultSet.getObject("estimate_time_arrival", Integer.class);
	                Integer adminUserId = resultSet.getObject("admin_user_id", Integer.class);
	                String imagePath = resultSet.getString("image_path");

	                restaurant = new Restaurant(id, name, address, phoneNumber, rating, cuisineType, isActive, estimateTimeArrival, adminUserId, imagePath);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return restaurant;
	}

	@Override
	public boolean updateAvailability(int restaurantId, boolean available) {
	    String sql = "UPDATE restaurants SET isActive = ? WHERE restaurant_id = ?";
	    boolean isUpdated = false;

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {

	        pstmt.setBoolean(1, available);
	        pstmt.setInt(2, restaurantId);

	        int rowsUpdated = pstmt.executeUpdate();
	        isUpdated = (rowsUpdated > 0); // Update was successful if rowsUpdated > 0
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return isUpdated;
	}





}
