package com.facility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.facility.model.Room;

public class RoomDAO {
    // Lấy tất cả rooms
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM nhp_rooms";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("nhp_room_id"));
                room.setName(rs.getString("nhp_room_name"));
                room.setType(rs.getString("nhp_room_type"));
                room.setCapacity(rs.getInt("nhp_capacity"));
                room.setLocation(rs.getString("nhp_location"));
                room.setStatus(rs.getString("nhp_status"));
                room.setEquipment(rs.getString("nhp_equipment"));
                rooms.add(room);
            }
        }
        return rooms;
    }

    // Lấy room theo ID
    public Room getRoomById(int id) throws SQLException {
        Room room = null;
        String sql = "SELECT * FROM nhp_rooms WHERE nhp_room_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    room = new Room();
                    room.setId(rs.getInt("nhp_room_id"));
                    room.setName(rs.getString("nhp_room_name"));
                    room.setType(rs.getString("nhp_room_type"));
                    room.setCapacity(rs.getInt("nhp_capacity"));
                    room.setLocation(rs.getString("nhp_location"));
                    room.setStatus(rs.getString("nhp_status"));
                    room.setEquipment(rs.getString("nhp_equipment"));
                }
            }
        }
        return room;
    }

    // Thêm room
    public void addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO nhp_rooms (nhp_room_name, nhp_room_type, nhp_capacity, nhp_location, nhp_status, nhp_equipment) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setInt(3, room.getCapacity());
            ps.setString(4, room.getLocation());
            ps.setString(5, room.getStatus());
            ps.setString(6, room.getEquipment());
            ps.executeUpdate();
        }
    }

    // Cập nhật room
    public void updateRoom(Room room) throws SQLException {
        String sql = "UPDATE nhp_rooms SET nhp_room_name = ?, nhp_room_type = ?, nhp_capacity = ?, nhp_location = ?, nhp_status = ?, nhp_equipment = ? WHERE nhp_room_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, room.getName());
            ps.setString(2, room.getType());
            ps.setInt(3, room.getCapacity());
            ps.setString(4, room.getLocation());
            ps.setString(5, room.getStatus());
            ps.setString(6, room.getEquipment());
            ps.setInt(7, room.getId());
            ps.executeUpdate();
        }
    }

    // Xóa room
    public void deleteRoom(int id) throws SQLException {
        String sql = "DELETE FROM nhp_rooms WHERE nhp_room_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}