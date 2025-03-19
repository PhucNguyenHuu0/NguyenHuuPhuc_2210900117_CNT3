package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.facility.dao.LogDAO;

@WebServlet("/deleteLog")
public class DeleteLogServlet extends HttpServlet {
    private LogDAO logDAO;

    public void init() {
        logDAO = new LogDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logDAO.deleteLog(id);
            response.sendRedirect("logs");
        } catch (SQLException e) {
            throw new ServletException("Không thể xóa nhật ký: " + e.getMessage());
        }
    }
}