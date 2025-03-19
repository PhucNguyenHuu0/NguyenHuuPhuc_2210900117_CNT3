package com.facility.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.facility.dao.RoomDAO;
import com.facility.model.Room;

@WebServlet("/addRoom")
public class AddRoomServlet extends HttpServlet {
    private RoomDAO roomDAO;

    public void init() {
        roomDAO = new RoomDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị form thêm mới
        request.getRequestDispatcher("/WEB-INF/views/addRoom.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String capacityStr = request.getParameter("capacity");
            String location = request.getParameter("location");
            String status = request.getParameter("status");
            String equipment = request.getParameter("equipment");

            // Kiểm tra dữ liệu
            if (name == null || type == null || capacityStr == null || status == null) {
                request.setAttribute("error", "Vui lòng điền đầy đủ các trường bắt buộc!");
                request.getRequestDispatcher("/WEB-INF/views/addRoom.jsp").forward(request, response);
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Sức chứa phải là một số!");
                request.getRequestDispatcher("/WEB-INF/views/addRoom.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Room
            Room room = new Room(0, name, type, capacity, location, status, equipment);
            roomDAO.addRoom(room);

            // Chuyển hướng về danh sách phòng
            response.sendRedirect("rooms");
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi thêm phòng: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/addRoom.jsp").forward(request, response);
        }
    }
}