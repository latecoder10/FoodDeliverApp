package com.food.modules;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private Integer userId; // Use Integer for nullable fields
    private Integer restaurantId; // Use Integer for nullable fields
    private LocalDateTime orderDate; // Use LocalDateTime for datetime
    private BigDecimal totalAmount;
    private OrderStatus orderStatus; // Enum for order status
    private PaymentMethod paymentMethod; // Enum for payment method
    
    // Enum for order status
    public enum OrderStatus {
        PENDING,
        DELIVERED,
        CANCELLED,
        IN_PROGRESS
    }

    // Enum for payment method
    public enum PaymentMethod {
        UPI,
        CASH,
        DEBIT_CARD,
        CREDIT_CARD
    }

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(int orderId, Integer userId, Integer restaurantId, LocalDateTime orderDate, BigDecimal totalAmount, OrderStatus orderStatus, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}

