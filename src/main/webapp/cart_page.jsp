<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map,java.util.HashMap, com.food.modules.Menu,com.food.modules.User" %>
<%@ page import="com.food.DAO.MenuDAO, com.food.DAOImpl.MenuDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <!-- Include CSS and Bootstrap here -->
    <link rel="stylesheet" href="CSS_Properties/test.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .payment-options .payment-icon {
            width: 24px;
            height: 24px;
        }

        .payment-options .form-check-label {
            font-size: 1rem;
        }

        .payment-options .payment-methods {
            display: flex;
            align-items: center;
        }
        
        .update-btn {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="Header/navbar.jsp" %>

    <section class="container my-4">
        <%

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
		
        // Retrieve the cart map from the session
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        double totalBill = 0.0;
        %>

        <h1 class="text-center mb-4">Your Cart</h1>
        <form id="cart-form">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    if (!cart.isEmpty()) {
                        MenuDAO menuDAO = new MenuDAOImpl();
                        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                            String menuId = entry.getKey();
                            int quantity = entry.getValue();
                            Menu menu = menuDAO.getMenu(Integer.parseInt(menuId));
                            BigDecimal price = menu.getPrice();
                            double total = price.doubleValue() * quantity;
                            totalBill += total;
                    %>
                    <tr id="item-<%= menuId %>">
                        <td><%= menu.getItemName() %></td>
                        <td><%= menu.getDescription() %></td>
                        <td><%= price %></td>
                        <td><input type="number" name="quantity_<%= menuId %>" value="<%= quantity %>" min="0" class="form-control quantity-input"></td>
                        <td><%= total %></td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm" onclick="removeItem('<%= menuId %>')">Remove</button>
                        </td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">Your cart is empty.</td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>

            <div class="update-btn">
                <button type="button" id="updateCartButton" class="btn btn-primary">Update Cart</button>
            </div>
        </form>

        <div class="text-end">
            <h3>Total Bill: <%= totalBill %></h3>
        </div>

        <div class="payment-options text-center mt-4">
            <h3>Select Payment Method:</h3>
            <form id="payment-form">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="paymentCash" value="cash" checked>
                            <label class="form-check-label" for="paymentCash">
                                <div class="payment-methods">
                                    <img src="images/cash-icon.png" alt="Cash" class="payment-icon" onerror="this.src='images/err.png'; this.onerror=null;">
                                    <span class="ms-2">Cash</span>
                                </div>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="paymentUpi" value="upi">
                            <label class="form-check-label" for="paymentUpi">
                                <div class="payment-methods">
                                    <img src="images/upi-icon.png" alt="UPI" class="payment-icon" onerror="this.src='images/err.png'; this.onerror=null;">
                                    <span class="ms-2">UPI</span>
                                </div>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="paymentDebitCard" value="debit_card">
                            <label class="form-check-label" for="paymentDebitCard">
                                <div class="payment-methods">
                                    <img src="images/debit-card-icon.png" alt="Debit Card" class="payment-icon" onerror="this.src='images/err.png'; this.onerror=null;">
                                    <span class="ms-2">Debit Card</span>
                                </div>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="paymentCreditCard" value="credit_card">
                            <label class="form-check-label" for="paymentCreditCard">
                                <div class="payment-methods">
                                    <img src="images/credit-card-icon.png" alt="Credit Card" class="payment-icon" onerror="this.src='images/err.png'; this.onerror=null;">
                                    <span class="ms-2">Credit Card</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
            </form>
            
            <!-- Proceed to Payment Button outside the form -->
          <button type="button" class="btn btn-primary mt-3" onclick="proceedToPayment()">Proceed to Payment</button>
        </div>
    </section>
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="script/cart.js"></script>
</body>
</html>
