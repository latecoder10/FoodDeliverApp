package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.UserDAO;
import com.food.modules.User;
import com.food.modules.User.UserType;
import com.food.util.DBConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean addUser(User user) {
	     String checkSql = "SELECT MAX(user_id) FROM users";
	        String insertSql = "INSERT INTO users(user_id, name, user_name, password, email, phone_number, address, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        try (Connection connection = DBConnectionUtil.getConnection();
	             Statement checkStmt = connection.createStatement();
	             ResultSet rs = checkStmt.executeQuery(checkSql)) {

	            int newUserId = 1; // Default to 1 if the table is empty

	            if (rs.next()) {
	                newUserId = rs.getInt(1) + 1; // Increment the maximum user_id found
	            }

	            // Check if the username or email already exists
	            String existsSql = "SELECT COUNT(*) FROM users WHERE user_name = ? OR email = ?";
	            try (PreparedStatement existsStmt = connection.prepareStatement(existsSql)) {
	                existsStmt.setString(1, user.getUserName());
	                existsStmt.setString(2, user.getEmail());
	                ResultSet existsRs = existsStmt.executeQuery();
	                if (existsRs.next() && existsRs.getInt(1) > 0) {
	                    System.out.println("Username or email already exists.");
	                    return false; // Exit if username or email already exists
	                }
	            }

	            // Proceed with insertion
	            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	                insertStmt.setInt(1, newUserId); // Set new user_id
	                insertStmt.setString(2, user.getName()); // Nullable
	                insertStmt.setString(3, user.getUserName());
	                insertStmt.setString(4, user.getPassword()); // Hashed password
	                insertStmt.setString(5, user.getEmail());
	                insertStmt.setString(6, user.getPhoneNumber()); // Nullable
	                insertStmt.setString(7, user.getAddress()); // Nullable
	                insertStmt.setString(8, user.getUserType().name());
	                insertStmt.executeUpdate();
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Properly handle exceptions
	        }
	        return true;
		
	}

	@Override
	public User getUser(int userId) {
		  String sql = "SELECT * FROM users WHERE user_id = ?";
		    User user = null;

		    try (Connection connection = DBConnectionUtil.getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql)) {

		        pstmt.setInt(1, userId);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            String name = rs.getString("name");
		            String userName = rs.getString("user_name");
		            String password = rs.getString("password");
		            String email = rs.getString("email");
		            String phoneNumber = rs.getString("phone_number");
		            String address = rs.getString("address");
		            String profilePicture=rs.getString("profile_picture");
		            UserType userType = UserType.valueOf(rs.getString("user_type").toUpperCase());

		            user = new User(userId, name, userName, password, email, phoneNumber, address, userType,profilePicture);
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // Properly handle exceptions
		    }

		    return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE users SET name = ?, user_name = ?, password = ?, email = ?, phone_number = ?, address = ?, user_type = ? , profile_picture= ? WHERE user_id = ?";

	    try (Connection connection = DBConnectionUtil.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	        pstmt.setString(1, user.getName()); // Nullable
	        pstmt.setString(2, user.getUserName());
	        pstmt.setString(3, user.getPassword()); // Hashed password
	        pstmt.setString(4, user.getEmail());
	        pstmt.setString(5, user.getPhoneNumber()); // Nullable
	        pstmt.setString(6, user.getAddress()); // Nullable
	        pstmt.setString(7, user.getUserType().name());
	        pstmt.setString(8, user.getProfilePicture());
	        pstmt.setInt(9, user.getUserId());
	        int rowsUpdated = pstmt.executeUpdate();
	        if (rowsUpdated == 0) {
	            System.out.println("No user found with the given user_id.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }

		
	}

	@Override
	public int deleteUser(int userId) {
		 String sql = "DELETE FROM users WHERE user_id = ?";
		 int rowsDeleted=0;
		    try (Connection connection = DBConnectionUtil.getConnection();
		         PreparedStatement pstmt = connection.prepareStatement(sql)) {

		        pstmt.setInt(1, userId);
		         rowsDeleted = pstmt.executeUpdate();
		        if (rowsDeleted == 0) {
		            System.out.println("No user found with the given user_id.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); 
		    }
		return rowsDeleted;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try(Connection connection =DBConnectionUtil.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String user_name = resultSet.getString(3);
				String pass = resultSet.getString(4);
				String eamil = resultSet.getString(5);
				String phno = resultSet.getString(6);
				String add = resultSet.getString(7);
	            // Convert the String to UserType enum
				UserType user_type = UserType.valueOf(resultSet.getString(8).toUpperCase());
				String profile=resultSet.getString(11);
				User user = new User(id,name, user_name, pass, eamil,phno, add, user_type,profile);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserByUserName(String userName) {
		String sql="SELECT * FROM users WHERE user_name= ?";
		User user=null;
		try(Connection connection = DBConnectionUtil.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	int userId = rs.getInt("user_id");
	            String name = rs.getString("name");
	            String userName1 = rs.getString("user_name");
	            String password = rs.getString("password");
	            String email = rs.getString("email");
	            String phoneNumber = rs.getString("phone_number");
	            String address = rs.getString("address");
	            String profile=rs.getString("profile_picture");
	            UserType userType = UserType.valueOf(rs.getString("user_type").toUpperCase());

	            user = new User(userId, name, userName1, password, email, phoneNumber, address, userType,profile);
	        }    
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
		return user;
		
	}
	
	
	
	

}
