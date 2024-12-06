package com.food.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.modules.Order;
import com.food.modules.Order.OrderStatus;
import com.food.modules.Order.PaymentMethod;
import com.food.util.DBConnectionUtil;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public int addOrder(Order order) {
		int generatedOrderId = -1; // Default value for failure

		String query = "INSERT INTO ordertable (user_id, restaurant_id, order_date, totalamount, order_status, paymentmethod) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DBConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pst.setInt(1, order.getUserId());
			pst.setInt(2, order.getRestaurantId());
			pst.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
			pst.setBigDecimal(4, order.getTotalAmount());
			pst.setString(5, order.getOrderStatus().name());
			pst.setString(6, getDatabaseValueFromPaymentMethod(order.getPaymentMethod()));

			// Execute the update
			int affectedRows = pst.executeUpdate();

			// Check if the insert was successful
			if (affectedRows > 0) {
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedOrderId = generatedKeys.getInt(1); // Retrieve the generated order_id
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return generatedOrderId; // Return the generated order_id
	}

	@Override
	public Order getOrder(int orderId) {
		Order order = null;
		try (Connection connection = DBConnectionUtil.getConnection()) {
			String query = "SELECT * FROM orders WHERE order_id = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, orderId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setRestaurantId(rs.getInt("restaurant_id"));
				order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
				order.setTotalAmount(rs.getBigDecimal("total_amount"));
				order.setOrderStatus(getOrderStatusFromDatabase(rs.getString("order_status")));
				order.setPaymentMethod(getPaymentMethodFromDatabase(rs.getString("payment_method")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getAllOrdersByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getPendingOrdersByRestaurantId(int restaurantId) {
		List<Order> pendingOrders = new ArrayList<>();
		String sql = "SELECT order_id, user_id, restaurant_id, order_date, totalamount, order_status, paymentmethod "
				+ "FROM ordertable WHERE order_status = 'Pending' AND restaurant_id = ?";

		try (Connection con = DBConnectionUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, restaurantId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setRestaurantId(rs.getInt("restaurant_id"));
					order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
					order.setTotalAmount(rs.getBigDecimal("totalamount"));
					order.setOrderStatus(getOrderStatusFromDatabase(rs.getString("order_status")));
					order.setPaymentMethod(getPaymentMethodFromDatabase(rs.getString("paymentmethod")));
					pendingOrders.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pendingOrders;
	}

	private Order.OrderStatus getOrderStatusFromDatabase(String dbValue) {
		switch (dbValue.toLowerCase()) {
		case "pending":
			return Order.OrderStatus.PENDING;
		case "delivered":
			return Order.OrderStatus.DELIVERED;
		case "cancelled":
			return Order.OrderStatus.CANCELLED;
		case "in progress":
			return Order.OrderStatus.IN_PROGRESS;
		default:
			throw new IllegalArgumentException("Unknown Order Status: " + dbValue);
		}
	}

	private String getDatabaseValueFromPaymentMethod(Order.PaymentMethod method) {
		switch (method) {
		case CREDIT_CARD:
			return "credit card";
		case DEBIT_CARD:
			return "debit card";
		case CASH:
			return "cash";
		case UPI:
			return "upi";
		default:
			throw new IllegalArgumentException("Unknown payment method: " + method);
		}
	}

	private Order.PaymentMethod getPaymentMethodFromDatabase(String dbValue) {
		switch (dbValue.toLowerCase()) {
		case "credit card":
			return Order.PaymentMethod.CREDIT_CARD;
		case "debit card":
			return Order.PaymentMethod.DEBIT_CARD;
		case "cash":
			return Order.PaymentMethod.CASH;
		case "upi":
			return Order.PaymentMethod.UPI;
		default:
			throw new IllegalArgumentException("Unknown payment method: " + dbValue);
		}
	}

	@Override
	public boolean updateOrderStatus(int orderId, String newStatus) {
		String sql = "UPDATE ordertable SET order_status = ? WHERE order_id = ?";

		try (Connection con = DBConnectionUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, newStatus);
			pstmt.setInt(2, orderId);
			int rowsAffected = pstmt.executeUpdate();

			return rowsAffected > 0; // Return true if at least one row was updated
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Return false in case of an error
		}
	}

	@Override
	public int getOrdersCountByStatus(int restaurantId, String status, String dateFilter) {
		String query = null;
		switch (dateFilter) {
		case "today":
			query = "SELECT COUNT(*) FROM ordertable WHERE restaurant_id = ? AND order_status = ? AND order_date >= CURDATE() AND order_date < CURDATE() + INTERVAL 1 DAY;";
			break;
		case "weekly":
			query = "SELECT COUNT(*) FROM ordertable WHERE restaurant_id = ? AND order_status = ? AND order_date >= NOW()  - INTERVAL 7 DAY;";
			break;
		case "monthly":
			query = "SELECT COUNT(*) FROM ordertable WHERE restaurant_id = ? AND order_status = ? AND MONTH(order_date) = MONTH(CURRENT_DATE) AND YEAR(order_date) = YEAR(CURRENT_DATE);";
			break;
		case "yearly":
			query = "SELECT COUNT(*) FROM ordertable WHERE restaurant_id = ? AND order_status = ? AND YEAR(order_date) = YEAR(CURRENT_DATE);";
			break;
		case "allTime":
			query = "SELECT COUNT(*) FROM ordertable WHERE restaurant_id = ? AND order_status = ?;";
			break;
		default:
			throw new IllegalArgumentException("Invalid date filter: " + dateFilter);
		}

		int count = 0;

		try (Connection conn = DBConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, restaurantId);
			pstmt.setString(2, status);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle exceptions properly in production code
		}

		return count;
	}

	@Override
	public double getTotalOrderAmount(int restaurantId, String dateFilter) {
		double totalAmount = 0.0;
		String query = null;

		switch (dateFilter) {
		case "today":
			query = "SELECT SUM(totalamount) FROM ordertable WHERE restaurant_id = ? AND order_date >= CURDATE() AND order_date < CURDATE() + INTERVAL 1 DAY;";
			break;
		case "weekly":
			query = "SELECT SUM(totalamount) FROM ordertable WHERE restaurant_id = ? AND order_date >= NOW() - INTERVAL 7 DAY;";
			break;
		case "monthly":
			query = "SELECT SUM(totalamount) FROM ordertable WHERE restaurant_id = ? AND MONTH(order_date) = MONTH(CURRENT_DATE) AND YEAR(order_date) = YEAR(CURRENT_DATE);";
			break;
		case "yearly":
			query = "SELECT SUM(totalamount) FROM ordertable WHERE restaurant_id = ? AND YEAR(order_date) = YEAR(CURRENT_DATE);";
			break;
		case "allTime":
			query = "SELECT SUM(totalamount) FROM ordertable WHERE restaurant_id = ?;";
			break;
		default:
			throw new IllegalArgumentException("Invalid date filter: " + dateFilter);
		}

		try (Connection conn = DBConnectionUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, restaurantId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				totalAmount = rs.getDouble(1);
			}
		} catch (Exception e) {
			// Replace with proper logging
			e.printStackTrace();
		}

		return totalAmount;
	}

	@Override
	public List<Order> getAceptedOrdersByRestaurantId(int restaurantId) {
		List<Order> acceptedOrders = new ArrayList<>();
		String query="SELECT * FROM ordertable WHERE restaurant_id=? AND order_status IN('Pending', 'In Progress','Delivered') ORDER BY order_id DESC";
		try(Connection connection = DBConnectionUtil.getConnection();
			PreparedStatement pstmt=connection.prepareStatement(query)) {
			
			
			pstmt.setInt(1, restaurantId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int orderId=rs.getInt("order_id");
			     Integer userId=rs.getInt("user_id"); // Use Integer for nullable fields
			     LocalDateTime orderDate=rs.getTimestamp("order_date").toLocalDateTime(); // Use LocalDateTime for datetime
			     BigDecimal totalAmount=rs.getBigDecimal("totalamount");
			     OrderStatus orderStatus=getOrderStatusFromDatabase(rs.getString("order_status")); // Enum for order status
			     PaymentMethod paymentMethod=getPaymentMethodFromDatabase(rs.getString("paymentmethod"));
			     Order order = new Order(orderId, userId, userId, orderDate, totalAmount, orderStatus, paymentMethod);
			     acceptedOrders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acceptedOrders;
	}
	
	@Override
	public List<Order> getCancelledOrdersByRestaurantId(int restaurantId) {
	    List<Order> cancelledOrders = new ArrayList<>();
	    String query = "SELECT * FROM ordertable WHERE restaurant_id=? AND order_status='Cancelled' ORDER BY order_id DESC";
	    
	    try (Connection connection = DBConnectionUtil.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setInt(1, restaurantId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            int orderId = rs.getInt("order_id");
	            Integer userId = rs.getInt("user_id"); // Use Integer for nullable fields
	            LocalDateTime orderDate = rs.getTimestamp("order_date").toLocalDateTime(); // Use LocalDateTime for datetime
	            BigDecimal totalAmount = rs.getBigDecimal("totalamount");
	            OrderStatus orderStatus = getOrderStatusFromDatabase(rs.getString("order_status")); // Enum for order status
	            PaymentMethod paymentMethod = getPaymentMethodFromDatabase(rs.getString("paymentmethod"));
	            
	            Order order = new Order(orderId, userId, userId, orderDate, totalAmount, orderStatus, paymentMethod);
	            cancelledOrders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return cancelledOrders;
	}

}
