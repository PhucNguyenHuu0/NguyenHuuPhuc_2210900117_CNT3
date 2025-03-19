package com.facility.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.MaintenanceRequestDAO;
import com.facility.model.MaintenanceRequest;

@WebServlet("/updateMaintenanceRequest")
public class UpdateMaintenanceRequestServlet extends HttpServlet {
    private MaintenanceRequestDAO requestDAO;

    public void init() {
        requestDAO = new MaintenanceRequestDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                request.setAttribute("error", "ID yêu cầu bảo trì không hợp lệ!");
                request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
                return;
            }

            int id = Integer.parseInt(idStr);
            MaintenanceRequest maintenanceRequest = requestDAO.getMaintenanceRequestById(id);
            if (maintenanceRequest == null) {
                request.setAttribute("error", "Không tìm thấy yêu cầu bảo trì với ID: " + id);
                request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
                return;
            }

            request.setAttribute("request", maintenanceRequest);
            request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi lấy thông tin yêu cầu bảo trì: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            String facilityIdStr = request.getParameter("facilityId");
            String userIdStr = request.getParameter("userId");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String requestDateStr = request.getParameter("requestDate");
            String completionDateStr = request.getParameter("completionDate");
            String technicianIdStr = request.getParameter("technicianId");
            String progress = request.getParameter("progress");

            if (idStr == null || facilityIdStr == null || userIdStr == null || description == null || status == null || requestDateStr == null) {
                request.setAttribute("error", "Vui lòng điền đầy đủ các trường bắt buộc!");
                request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
                return;
            }

            int id = Integer.parseInt(idStr);
            int facilityId = Integer.parseInt(facilityIdStr);
            int userId = Integer.parseInt(userIdStr);
            Timestamp requestDate = Timestamp.valueOf(requestDateStr.replace("T", " ") + ":00");
            Timestamp completionDate = (completionDateStr != null && !completionDateStr.isEmpty()) 
                ? Timestamp.valueOf(completionDateStr.replace("T", " ") + ":00") : null;
            Integer technicianId = (technicianIdStr != null && !technicianIdStr.isEmpty()) 
                ? Integer.parseInt(technicianIdStr) : null;

            MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
            maintenanceRequest.setId(id);
            maintenanceRequest.setFacilityId(facilityId);
            maintenanceRequest.setUserId(userId);
            maintenanceRequest.setDescription(description);
            maintenanceRequest.setStatus(status);
            maintenanceRequest.setRequestDate(requestDate);
            maintenanceRequest.setCompletionDate(completionDate);
            maintenanceRequest.setTechnicianId(technicianId);
            maintenanceRequest.setProgress(progress);

            requestDAO.updateMaintenanceRequest(maintenanceRequest);
            response.sendRedirect("maintenanceRequests");
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi cập nhật yêu cầu bảo trì: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/editMaintenanceRequest.jsp").forward(request, response);
        }
    }
}