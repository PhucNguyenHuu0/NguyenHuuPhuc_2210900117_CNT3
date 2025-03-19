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
import com.facility.model.Log;

@WebServlet("/addLog")
public class AddLogServlet extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/views/addLog.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        try {
            Log log = new Log();
            String userIdStr = request.getParameter("userId");
            if (userIdStr != null && !userIdStr.isEmpty()) {
                log.setUserId(Integer.parseInt(userIdStr));
            }
            log.setAction(request.getParameter("action"));

            logDAO.addLog(log);
            response.sendRedirect("logs");
        } catch (SQLException e) {
            throw new ServletException("Không thể thêm nhật ký: " + e.getMessage());
        }
    }
}