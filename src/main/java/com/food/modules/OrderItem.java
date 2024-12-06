package com.food.modules;

public class OrderItem {
    private int orderItemId;
    private Integer orderId; // Use Integer for nullable fields
    private Integer menuId; // Use Integer for nullable fields
    private int quantity;
    private double itemTotal;
    // Default constructor
    public OrderItem() {
    }

    // Parameterized constructor
    public OrderItem(int orderItemId, Integer orderId, Integer menuId, int quantity,double itemTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.itemTotal=itemTotal;
        		
    }

    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

    public double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}

	@Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", menuId=" + menuId +
                ", quantity=" + quantity +
                ", itemTotal=" + itemTotal +
                
                '}';
    }
}

