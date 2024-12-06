package com.food.servlets;

import java.io.IOException;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAOImpl();
		String userIdParam=req.getParameter("user_id");
		if(userIdParam!=null && !userIdParam.isEmpty() ) {
			try {
				int userId=Integer.parseInt(userIdParam);
				int rowsDeleted=userDAO.deleteUser(userId);
				if(rowsDeleted==0) {
					resp.sendRedirect("error.jsp");
				}else {
					resp.sendRedirect("viewUsers.jsp");
				}
				
				
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resp.sendRedirect("error.jsp");
			}
		}else {
			resp.sendRedirect("error.jsp");
		}
	}

}
