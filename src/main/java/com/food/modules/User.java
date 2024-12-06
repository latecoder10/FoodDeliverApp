package com.food.modules;

public class User {
	 	private int userId;
	 	private String name;
	    private String userName;
	    private String password;
	    private String email;
	    private String phoneNumber;
	    
	    private String address;
	    private UserType userType;  // Use the enum as the type for this field
	    private String profilePicture;

	    // Enum defined within the class
	    public enum UserType {
	        CUSTOMER, STAFF, ADMIN,WEBSITE_ADMIN
	    }
	    

	    public User() {
			super();
		}

		// Constructor
	    public User(int userId,String name, String userName, String password, String email,
	    		String phoneNumber,String address, UserType userType) {
	        this.userId = userId;
	        this.name=name;
	        this.userName = userName;
	        this.password = password;
	        this.email = email;
	        this.phoneNumber=phoneNumber;
	        this.address = address;
	        this.userType = userType;

	    }

	    public User(int userId, String name, String userName, String password, String email, String phoneNumber,
				String address, UserType userType, String profilePicture) {
	    	  this.userId = userId;
		        this.name=name;
		        this.userName = userName;
		        this.password = password;
		        this.email = email;
		        this.phoneNumber=phoneNumber;
		        this.address = address;
		        this.userType = userType;
		        this.profilePicture=profilePicture;
	    
	    }

		// Getters and Setters
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }


	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	 

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }
	    
	    
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public UserType getUserType() {
	        return userType;
	    }

	    public void setUserType(UserType userType) {
	        this.userType = userType;
	    }

	    // getters and setters for profilePicture
	    public String getProfilePicture() {
	        return profilePicture;
	    }

	    public void setProfilePicture(String profilePicture) {
	        this.profilePicture = profilePicture;
	    }

		@Override
		public String toString() {
			return "User [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password=" + password
					+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", userType="
					+ userType + ", profilePicture=" + profilePicture + "]";
		}
	    
	    
	    
	    
	   
}
