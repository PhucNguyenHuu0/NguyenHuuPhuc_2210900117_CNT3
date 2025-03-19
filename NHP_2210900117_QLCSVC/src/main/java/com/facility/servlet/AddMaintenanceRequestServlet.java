package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.MaintenanceRequestDAO;
import com.facility.model.MaintenanceRequest;

@WebServlet("/addMaintenanceRequest")
public class AddMaintenanceRequestServlet extends HttpServlet {
    private MaintenanceRequestDAO maintenanceRequestDAO;

    public void init() {
        maintenanceRequestDAO = new MaintenanceRequestDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/addMaintenanceRequest.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
            String facilityIdStr = request.getParameter("facilityId");
            if (facilityIdStr != null && !facilityIdStr.isEmpty()) {
                maintenanceRequest.setFacilityId(Integer.parseInt(facilityIdStr));
            }
            String userIdStr = request.getParameter("userId");
            if (userIdStr != null && !userIdStr.isEmpty()) {
                maintenanceRequest.setUserId(Integer.parseInt(userIdStr));
            }
            maintenanceRequest.setDescription(request.getParameter("description"));
            maintenanceRequest.setStatus(request.getParameter("status"));
            String technicianIdStr = request.getParameter("technicianId");
            if (technicianIdStr != null && !technicianIdStr.isEmpty()) {
                maintenanceRequest.setTechnicianId(Integer.parseInt(technicianIdStr));
            }
            maintenanceRequest.setProgress(request.getParameter("progress"));

            maintenanceRequestDAO.addMaintenanceRequest(maintenanceRequest);
            response.sendRedirect("maintenanceRequests");
        } catch (SQLException e) {
            throw new ServletException("Không thể thêm yêu cầu bảo trì: " + e.getMessage());
        }
    }
}