package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.facility.dao.LogDAO;
import com.facility.model.Log;

@WebServlet("/logs")
public class LogServlet extends HttpServlet {
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
            List<Log> logList = logDAO.getAllLogs();
            request.setAttribute("logList", logList);
            request.getRequestDispatcher("/WEB-INF/views/logList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy danh sách nhật ký: " + e.getMessage());
        }
    }
}