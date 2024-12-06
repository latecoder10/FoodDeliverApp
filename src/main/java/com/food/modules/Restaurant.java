package com.food.modules;

import java.math.BigDecimal;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String address;
    private String phoneNumber;
    private BigDecimal rating;
    private String cuisineType;
    private boolean isActive;
    private Integer estimateTimeArrival;
    private Integer adminUserId;
    private String imagePath;

    // Default constructor
    public Restaurant() {
    }

    // Parameterized constructor
    public Restaurant(int restaurantId, String name, String address, String phoneNumber,BigDecimal rating,
                      String cuisineType, boolean isActive, Integer estimateTimeArrival, Integer adminUserId,
                      String imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.phoneNumber=phoneNumber;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.estimateTimeArrival = estimateTimeArrival;
        this.adminUserId = adminUserId;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getEstimateTimeArrival() {
        return estimateTimeArrival;
    }

    public void setEstimateTimeArrival(Integer estimateTimeArrival) {
        this.estimateTimeArrival = estimateTimeArrival;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // toString method
    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                
                ", rating=" + rating +
                ", cuisineType='" + cuisineType + '\'' +
                ", isActive=" + isActive +
                ", estimateTimeArrival=" + estimateTimeArrival +
                ", adminUserId=" + adminUserId +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
