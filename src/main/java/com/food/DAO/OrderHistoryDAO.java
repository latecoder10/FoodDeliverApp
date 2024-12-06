package com.food.DAO;

import java.util.List;

import com.food.modules.OrderHistory;

public interface OrderHistoryDAO {
	void addOrderHistory(OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getAllOrderHistoriesByUser(int userId);
}
