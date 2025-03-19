package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.RoomDAO;

@WebServlet("/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
    private RoomDAO roomDAO;

    public void init() {
        roomDAO = new RoomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            roomDAO.deleteRoom(id);
            response.sendRedirect("rooms");
        } catch (SQLException e) {
            throw new ServletException("Không thể xóa phòng: " + e.getMessage());
        }
    }
}