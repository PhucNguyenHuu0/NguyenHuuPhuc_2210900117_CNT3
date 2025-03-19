package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.facility.dao.FacilityDAO;
import com.facility.model.Facility;

@WebServlet("/updateFacility")
public class UpdateFacilityServlet extends HttpServlet {
    private FacilityDAO facilityDAO;

    public void init() {
        facilityDAO = new FacilityDAO();
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
            Facility facility = facilityDAO.getFacilityById(id);
            request.setAttribute("facility", facility);
            request.getRequestDispatcher("/WEB-INF/views/editFacility.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy thông tin cơ sở vật chất: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        try {
            Facility facility = new Facility();
            facility.setId(Integer.parseInt(request.getParameter("id")));
            facility.setName(request.getParameter("name"));
            facility.setDescription(request.getParameter("description"));
            facility.setStatus(request.getParameter("status"));
            String roomIdStr = request.getParameter("roomId");
            if (roomIdStr != null && !roomIdStr.isEmpty()) {
                facility.setRoomId(Integer.parseInt(roomIdStr));
            }

            facilityDAO.updateFacility(facility);
            response.sendRedirect("facilities");
        } catch (SQLException e) {
            throw new ServletException("Không thể cập nhật cơ sở vật chất: " + e.getMessage());
        }
    }
}