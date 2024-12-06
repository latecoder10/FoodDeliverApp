package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderHistoryDAO;
import com.food.modules.OrderHistory;
import com.food.util.DBConnectionUtil;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		String query = "INSERT INTO orderhistory (user_id, order_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DBConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, orderHistory.getUserId());
			pst.setInt(2, orderHistory.getOrderId());
			pst.setTimestamp(3, Timestamp.valueOf(orderHistory.getOrderDate()));
			pst.setBigDecimal(4, orderHistory.getTotalAmount());
			pst.setString(5, orderHistory.getStatus().name()); // Assuming `status` is an enum

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderHistory> getAllOrderHistoriesByUser(int userId) {
		List<OrderHistory> orderHistories = new ArrayList<>();
		String query = "SELECT * FROM orderhistory WHERE user_id = ?";

		try (Connection connection = DBConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				OrderHistory orderHistory = new OrderHistory();
				orderHistory.setOrderHistoryId(rs.getInt("orderhistory_id"));
				orderHistory.setUserId(rs.getInt("user_id"));
				orderHistory.setOrderId(rs.getInt("order_id"));
				orderHistory.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
				orderHistory.setTotalAmount(rs.getBigDecimal("total_amount"));
				orderHistory.setStatus(OrderHistory.OrderStatus.valueOf(rs.getString("status").toUpperCase()));
				orderHistories.add(orderHistory);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderHistories;
	}

}
