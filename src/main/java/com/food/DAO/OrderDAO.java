package com.food.DAO;

import java.util.List;

import com.food.modules.Order;

public interface OrderDAO {
	int addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getAllOrdersByUser(int userId);
	List<Order> getPendingOrdersByRestaurantId(int restaurantId);
	boolean updateOrderStatus(int orderId, String newStatus);
	double getTotalOrderAmount(int restaurantId,String dateFilter);
	int getOrdersCountByStatus(int restaurantId, String status, String string);
	List<Order>getAceptedOrdersByRestaurantId(int restaurantId);
	List<Order> getCancelledOrdersByRestaurantId(int restaurantId);
}
