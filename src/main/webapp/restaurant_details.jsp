<%@ page import="java.util.List, com.food.modules.Menu, com.food.DAO.MenuDAO, com.food.DAOImpl.MenuDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.food.modules.Restaurant, com.food.modules.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant Details</title>
    <link rel="stylesheet" href="CSS_Properties/test.css">
    <link rel="stylesheet" href="CSS_Properties/restaurant_detail.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Existing CSS */
        .quantity-selector {
            display: flex;
            align-items: center;
        }

        .quantity-selector input {
            width: 60px;
            text-align: center;
        }

        .modal-body a {
            text-decoration: none;
        }

        /* New CSS for restaurant image with overlay text */
        .restaurant-image-container {
            position: relative;
            width: 100%;
            max-width: 800px;
            height: 300px;
            margin: 0 auto;
            overflow: hidden;
        }

        .restaurant-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .restaurant-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            font-size: 2rem;
            font-weight: bold;
            background-color: rgba(0, 0, 0, 0.5);
            padding: 10px;
            border-radius: 8px;
        }

        .card-text {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body data-logged-in="<%= session.getAttribute("user") != null ? "true" : "false" %>">
    <%@ include file="Header/navbar.jsp" %>

    <%
        // Obtain the session object implicitly (no need to declare it)
       
        Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

        if (restaurant == null) {
            throw new NullPointerException("Restaurant object is null.");
        }
        

        // Set the current restaurant ID in the session
        session.setAttribute("currentRestaurantId", restaurant.getRestaurantId());

        MenuDAO menuDAO = new MenuDAOImpl();
        List<Menu> menus = menuDAO.getAllMenusByRestaurant(restaurant.getRestaurantId());
    %>

    <section class="container my-4">
        <!-- Restaurant Image with Overlay Text -->
        <div class="restaurant-image-container">
            <img src="path-to-restaurant-image.jpg" alt="Restaurant Image" class="restaurant-image" onerror="this.src='images/err.png'; this.onerror=null;">
            <div class="restaurant-text"><%= restaurant.getName() %></div>
        </div>

        <!-- Content Section -->
        <div class="container mt-5">
            <!-- Restaurant Name and Underline -->
            <h1 class="text-center mb-4">Our Menu</h1>
            <div class="text-center my-4">
                <div class="underline mt-3 mx-auto"></div>
            </div>
            <!-- Search Form -->
            <div class="text-center mb-4">
                <form class="d-flex justify-content-center" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>

            <!-- Modal for Login -->
            <%@ include file="Modals/loginModal.jsp" %>

            <div class="row">
                <% for (Menu menu : menus) { %>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="path-to-image.jpg" class="card-img-top" alt="Food Item" onerror="this.src='images/err.png'; this.onerror=null;">
                        <div class="card-body">
                            <h5 class="card-title"><%= menu.getItemName() %></h5>
                            <p class="card-text"><%= menu.getDescription() %></p>
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <span><strong>Price:</strong> <%= menu.getPrice() %></span>
                                <div class="quantity-selector">
                                    <button class="btn btn-secondary btn-sm" onclick="changeQuantity('<%= menu.getMenuId() %>', -1)">-</button>
                                    <input type="text" id="<%= menu.getMenuId() %>-quantity" value="0" readonly>
                                    <button class="btn btn-secondary btn-sm" onclick="changeQuantity('<%= menu.getMenuId() %>', 1)">+</button>
                                </div>
                            </div>
                            <form id="cart-form-<%= menu.getMenuId() %>" class="d-flex justify-content-center" style="display: none;" action="/addToCart" method="post">
                                <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
                                <input type="hidden" name="quantity" id="<%= menu.getMenuId() %>-quantity-form" value="0">
                            </form>
                            <button class="btn btn-primary mx-auto" onclick="checkLoginAndAddToCart('<%= menu.getMenuId() %>')">Add to Cart</button>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </section>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    <script src="script/cartValidation.js"></script>
</body>
</html>
