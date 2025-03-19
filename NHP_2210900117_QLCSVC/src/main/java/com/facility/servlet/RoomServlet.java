package com.facility.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.RoomDAO;
import com.facility.model.Room;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {
    private RoomDAO roomDAO;

    public void init() {
        roomDAO = new RoomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Room> roomList = roomDAO.getAllRooms();
            request.setAttribute("roomList", roomList);
            request.getRequestDispatcher("/WEB-INF/views/roomList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Không thể lấy danh sách phòng: " + e.getMessage());
        }
    }
}