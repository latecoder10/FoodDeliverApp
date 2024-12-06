package com.food.modules;

import java.math.BigDecimal;

public class Menu {
    private int menuId;
    private Integer restaurantId; // Use Integer for nullable fields
    private String itemName;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable; // Assuming this is a boolean field
    
    // Default constructor
    public Menu() {
    }

    // Parameterized constructor
    public Menu(int menuId, Integer restaurantId, String itemName, String description, BigDecimal price, Boolean isAvailable) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
