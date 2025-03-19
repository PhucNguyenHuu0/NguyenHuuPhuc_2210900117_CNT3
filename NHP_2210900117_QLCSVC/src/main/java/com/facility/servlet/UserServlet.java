package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.UserDAO;
import com.facility.model.User;

@WebServlet("/users") // Đảm bảo annotation này tồn tại
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> userList = userDAO.getAllUsers();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy danh sách người dùng: " + e.getMessage());
        }
    }
}