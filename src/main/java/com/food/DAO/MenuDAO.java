package com.food.DAO;

import java.util.List;

import com.food.modules.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu> getAllMenusByRestaurant(int restaurantId);
	Integer getRestaurantIdByItemId(String itemId);
}
