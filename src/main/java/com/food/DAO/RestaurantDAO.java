package com.food.DAO;

import java.util.List;

import com.food.modules.Restaurant;

public interface RestaurantDAO {
	boolean addRestaurant(Restaurant restaurant);
	Restaurant getRestaurantById(int restaurantId);
	boolean updateRestaurant(Restaurant restaurant);
	boolean deleteRestaurant(int restaurantId);
	List<Restaurant> getAllRestaurant();
	Restaurant getRestaurantByAdminUserId(int adminUserId);
	Restaurant getRestaurantBySlug(String slug);
	boolean updateAvailability(int restaurantId, boolean available);
}
