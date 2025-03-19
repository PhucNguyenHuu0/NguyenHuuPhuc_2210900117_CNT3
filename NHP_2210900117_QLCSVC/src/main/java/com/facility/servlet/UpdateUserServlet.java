package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.UserDAO;
import com.facility.model.User;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userDAO.getUserById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy thông tin người dùng: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setFullName(request.getParameter("fullName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));
            user.setUsername(request.getParameter("username"));
            user.setPhone(request.getParameter("phone"));

            userDAO.updateUser(user);
            response.sendRedirect("users");
        } catch (SQLException e) {
            throw new ServletException("Không thể cập nhật người dùng: " + e.getMessage());
        }
    }
}