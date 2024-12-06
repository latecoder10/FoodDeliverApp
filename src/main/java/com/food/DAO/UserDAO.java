package com.food.DAO;

import java.util.List;

import com.food.modules.User;

public interface UserDAO {
	boolean addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	int deleteUser(int userId);
	User getUserByUserName(String userName);
	List<User> getAllUsers();
	
}
