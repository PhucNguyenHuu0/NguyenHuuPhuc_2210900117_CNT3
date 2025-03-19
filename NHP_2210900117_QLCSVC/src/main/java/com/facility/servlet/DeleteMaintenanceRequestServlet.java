package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.MaintenanceRequestDAO;

@WebServlet("/deleteMaintenanceRequest")
public class DeleteMaintenanceRequestServlet extends HttpServlet {
    private MaintenanceRequestDAO maintenanceRequestDAO;

    public void init() {
        maintenanceRequestDAO = new MaintenanceRequestDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            maintenanceRequestDAO.deleteMaintenanceRequest(id);
            response.sendRedirect("maintenanceRequests");
        } catch (SQLException e) {
            throw new ServletException("Không thể xóa yêu cầu bảo trì: " + e.getMessage());
        }
    }
}