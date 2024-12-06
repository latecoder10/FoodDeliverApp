package com.food.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.MenuDAO;
import com.food.modules.Menu;
import com.food.util.DBConnectionUtil;

public class MenuDAOImpl implements MenuDAO  {

	@Override
	public void addMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Menu getMenu(int menuId) {
		String sql="SELECT * FROM menu WHERE menu_id= ?";
		Menu menu=null;
		try(Connection connection = DBConnectionUtil.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()) {
				
				int menu_id=resultSet.getInt("menu_id");
				Integer restaurant_id=resultSet.getInt("restaurant_id");
				String item_name=resultSet.getString("item_name");
				String description=resultSet.getString("description");
				BigDecimal price=resultSet.getBigDecimal("price");
				boolean isAvailable=resultSet.getBoolean("isAvailable");
				
				menu= new Menu(menu_id, restaurant_id, item_name, description, price, isAvailable);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenu(int menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurantId) {
		String sql="SELECT * FROM menu WHERE restaurant_id= ?";
		Menu menu=null;
		List<Menu> menus = new ArrayList<>();
		try(Connection connection = DBConnectionUtil.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, restaurantId);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
					int menu_id=resultSet.getInt("menu_id");
					Integer restaurant_id=resultSet.getInt("restaurant_id");
					String item_name=resultSet.getString("item_name");
					String description=resultSet.getString("description");
					BigDecimal price=resultSet.getBigDecimal("price");
					boolean isAvailable=resultSet.getBoolean("isAvailable");
					
					 menu= new Menu(menu_id, restaurant_id, item_name, description, price, isAvailable);
					 
					 menus.add(menu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}

	@Override
	public Integer getRestaurantIdByItemId(String itemId) {
		String query="SELECT restaurant_id FROM menu WHERE menu_id=?";
		Integer restaurant_id=null;
		try(Connection connection = DBConnectionUtil.getConnection()) {
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1,Integer.parseInt(itemId));
			ResultSet resultSet = pstmt.executeQuery();
			resultSet.next();
				restaurant_id=resultSet.getInt("restaurant_id");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurant_id;
	}
	
	
	
	

}
