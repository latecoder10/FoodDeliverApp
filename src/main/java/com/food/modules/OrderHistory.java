package com.food.modules;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private int orderHistoryId;
    private Integer userId; // Use Integer for nullable fields
    private Integer orderId; // Use Integer for nullable fields
    private LocalDateTime orderDate; // Use LocalDateTime for datetime
    private BigDecimal totalAmount;
    private OrderStatus status; // Enum for status

    // Enum for order status
    public enum OrderStatus {
    	PENDING,
        DELIVERED,
        CANCELLED,
        RETURNED
    }

    // Default constructor
    public OrderHistory() {
    }

    // Parameterized constructor
    public OrderHistory(int orderHistoryId, Integer userId, Integer orderId, LocalDateTime orderDate, BigDecimal totalAmount, OrderStatus status) {
        this.orderHistoryId = orderHistoryId;
        this.userId = userId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
    	
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderHistoryId=" + orderHistoryId +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}

