package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.food.DAO.OrderItemDAO;
import com.food.modules.OrderItem;
import com.food.util.DBConnectionUtil;

public class OrderItemDAOImpl implements OrderItemDAO{

	@Override
    public void addOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO orderitem (order_id, menu_id, quantity) VALUES (?, ?, ?)";
        
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {

            pst.setInt(1, orderItem.getOrderId());
            pst.setInt(2, orderItem.getMenuId());
            pst.setInt(3, orderItem.getQuantity());

            pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, you can handle this error better, e.g., logging or rethrowing
        }
    }

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderItem> getAllOrderItemsByOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
