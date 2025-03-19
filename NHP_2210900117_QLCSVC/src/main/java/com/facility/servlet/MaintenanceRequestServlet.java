package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.MaintenanceRequestDAO;
import com.facility.model.MaintenanceRequest;

@WebServlet("/maintenanceRequests")
public class MaintenanceRequestServlet extends HttpServlet {
    private MaintenanceRequestDAO maintenanceRequestDAO;

    public void init() {
        maintenanceRequestDAO = new MaintenanceRequestDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<MaintenanceRequest> requestList = maintenanceRequestDAO.getAllMaintenanceRequests();
            request.setAttribute("requestList", requestList);
            request.getRequestDispatcher("/WEB-INF/views/maintenanceRequestList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy danh sách yêu cầu bảo trì: " + e.getMessage());
        }
    }
}