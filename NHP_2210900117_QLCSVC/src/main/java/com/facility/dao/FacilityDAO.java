package com.facility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.facility.model.Facility;

public class FacilityDAO {
    // Lấy tất cả facilities
    public List<Facility> getAllFacilities() throws SQLException {
        List<Facility> facilities = new ArrayList<>();
        String sql = "SELECT * FROM nhp_facilities";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Facility facility = new Facility();
                facility.setId(rs.getInt("nhp_facility_id"));
                facility.setName(rs.getString("nhp_name"));
                facility.setDescription(rs.getString("nhp_description"));
                facility.setStatus(rs.getString("nhp_status"));
                facility.setRoomId(rs.getInt("nhp_room_id"));
                facilities.add(facility);
            }
        }
        return facilities;
    }

    // Lấy facility theo ID
    public Facility getFacilityById(int id) throws SQLException {
        Facility facility = null;
        String sql = "SELECT * FROM nhp_facilities WHERE nhp_facility_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    facility = new Facility();
                    facility.setId(rs.getInt("nhp_facility_id"));
                    facility.setName(rs.getString("nhp_name"));
                    facility.setDescription(rs.getString("nhp_description"));
                    facility.setStatus(rs.getString("nhp_status"));
                    facility.setRoomId(rs.getInt("nhp_room_id"));
                }
            }
        }
        return facility;
    }

    // Thêm facility
    public void addFacility(Facility facility) throws SQLException {
        String sql = "INSERT INTO nhp_facilities (nhp_name, nhp_description, nhp_status, nhp_room_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, facility.getName());
            ps.setString(2, facility.getDescription());
            ps.setString(3, facility.getStatus());
            ps.setObject(4, facility.getRoomId(), java.sql.Types.INTEGER);
            ps.executeUpdate();
        }
    }

    // Cập nhật facility
    public void updateFacility(Facility facility) throws SQLException {
        String sql = "UPDATE nhp_facilities SET nhp_name = ?, nhp_description = ?, nhp_status = ?, nhp_room_id = ? WHERE nhp_facility_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, facility.getName());
            ps.setString(2, facility.getDescription());
            ps.setString(3, facility.getStatus());
            ps.setObject(4, facility.getRoomId(), java.sql.Types.INTEGER);
            ps.setInt(5, facility.getId());
            ps.executeUpdate();
        }
    }

    // Xóa facility
    public void deleteFacility(int id) throws SQLException {
        String sql = "DELETE FROM nhp_facilities WHERE nhp_facility_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}