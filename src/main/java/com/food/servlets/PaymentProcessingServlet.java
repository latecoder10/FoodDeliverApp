package com.food.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import com.food.DAO.MenuDAO;
import com.food.DAO.OrderDAO;
import com.food.DAO.OrderHistoryDAO;
import com.food.DAO.OrderItemDAO;
import com.food.DAOImpl.MenuDAOImpl;
import com.food.DAOImpl.OrderDAOImpl;
import com.food.DAOImpl.OrderHistoryDAOImpl;
import com.food.DAOImpl.OrderItemDAOImpl;
import com.food.modules.Menu;
import com.food.modules.Order;
import com.food.modules.OrderHistory;
import com.food.modules.OrderItem;
import com.food.modules.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/process_payment")
public class PaymentProcessingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("error: Your cart is empty.");
            return;
        }

        String paymentMethodString = request.getParameter("paymentMethod");
        if (paymentMethodString == null || paymentMethodString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("error: Please select a payment method.");
            return;
        }

        Order.PaymentMethod paymentMethod;
        try {
            paymentMethod = Order.PaymentMethod.valueOf(paymentMethodString.toUpperCase());
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("error: Invalid payment method.");
            return;
        }

        double totalBill = 0.0;
        MenuDAO menuDAO = new MenuDAOImpl();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String menuId = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = menuDAO.getMenu(Integer.parseInt(menuId));
            if (menu != null) {
                BigDecimal price = menu.getPrice();
                totalBill += price.doubleValue() * quantity;
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.getWriter().write("error: Menu item not found.");
                return;
            }
        }

        boolean paymentSuccessful = true; // Simulate payment processing result
        if (paymentSuccessful) {
            OrderDAO orderDAO = new OrderDAOImpl();
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId((Integer)session.getAttribute("currentRestaurantId")); // Ensure this value is set correctly
            order.setOrderDate(LocalDateTime.now());
            order.setTotalAmount(BigDecimal.valueOf(totalBill));
            order.setOrderStatus(Order.OrderStatus.PENDING);
            order.setPaymentMethod(paymentMethod);
            
            int orderId=orderDAO.addOrder(order);
            
            // Add order items
            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String menuId = entry.getKey();
                int quantity = entry.getValue();
                
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setMenuId(Integer.parseInt(menuId));
                orderItem.setQuantity(quantity);
                
                orderItemDAO.addOrderItem(orderItem);
            }
            
            
            
            // Add entry to orderhistory
//          -------------------------------------------------
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setUserId(user.getUserId());
            orderHistory.setOrderId(orderId);
            orderHistory.setOrderDate(LocalDateTime.now());
            orderHistory.setTotalAmount(BigDecimal.valueOf(totalBill));
            orderHistory.setStatus(OrderHistory.OrderStatus.PENDING);
            orderHistoryDAO.addOrderHistory(orderHistory);

            
            // Clear the cart
            session.removeAttribute("cart");

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("success: Payment Successful! Total bill: $" + totalBill);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().write("error: Payment Failed");
        }
    }
}
