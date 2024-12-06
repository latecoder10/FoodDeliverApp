package com.food.servlets;

import java.io.IOException;

import com.food.DAO.RestaurantDAO;
import com.food.DAO.UserDAO;
import com.food.DAOImpl.RestaurantDAOImpl;
import com.food.DAOImpl.UserDAOImpl;
import com.food.modules.Restaurant;
import com.food.modules.User;
import com.food.util.DBConnectionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAOImpl();
	private RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user_name");
		String password = request.getParameter("password");

		// Retrieve user from the database
		User user = userDAO.getUserByUserName(userName);

		if (user != null && password.equals(user.getPassword())) {
			// Create a session for the logged-in user
			HttpSession session = request.getSession(true); // Create a new session if none exists
			session.setAttribute("user", user);

			// Update last login date
			updateLastLogin(userName);

			// Redirect based on user type
			String redirectUrl;
			switch (user.getUserType()) {
			case ADMIN:
				Restaurant restaurant = restaurantDAO.getRestaurantByAdminUserId(user.getUserId());
				if (restaurant == null) {
					redirectUrl = "error.jsp";
				} else {
					session.setAttribute("restaurant", restaurant);
					redirectUrl = "viewPendingOrders";
				}
				break;
			case CUSTOMER:
				redirectUrl = "index.jsp";
				break;
			case STAFF:
				redirectUrl = "staffDashboard.jsp";
				break;
			case WEBSITE_ADMIN:
				redirectUrl = "websiteAdminDashboard.jsp";
				break;
			default:
				redirectUrl = "index.jsp";
			}
			response.sendRedirect(redirectUrl);
		} else {
			request.setAttribute("error", "Invalid username or password.");
			request.getRequestDispatcher("/index.jsp").forward(request, response); // Forward to index.jsp to show error
		}
	}

	private void updateLastLogin(String userName) {
		try (var con = DBConnectionUtil.getConnection()) {
			String sql = "UPDATE users SET last_login_date = CURRENT_TIMESTAMP WHERE user_name = ?";
			try (var pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, userName);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
