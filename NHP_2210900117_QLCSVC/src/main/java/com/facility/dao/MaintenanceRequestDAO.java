package com.facility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.facility.model.MaintenanceRequest;

public class MaintenanceRequestDAO {
    // Lấy tất cả maintenance requests
    public List<MaintenanceRequest> getAllMaintenanceRequests() throws SQLException {
        List<MaintenanceRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM nhp_maintenance_requests";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MaintenanceRequest request = new MaintenanceRequest();
                request.setId(rs.getInt("nhp_request_id"));
                request.setFacilityId(rs.getInt("nhp_facility_id"));
                request.setUserId(rs.getInt("nhp_user_id"));
                request.setDescription(rs.getString("nhp_description"));
                request.setStatus(rs.getString("nhp_status"));
                request.setRequestDate(rs.getTimestamp("nhp_request_date"));
                request.setCompletionDate(rs.getTimestamp("nhp_completion_date"));
                request.setTechnicianId(rs.getInt("nhp_technician_id"));
                request.setProgress(rs.getString("nhp_progress"));
                requests.add(request);
            }
        }
        return requests;
    }

    // Lấy maintenance request theo ID
    public MaintenanceRequest getMaintenanceRequestById(int id) throws SQLException {
        MaintenanceRequest request = null;
        String sql = "SELECT * FROM nhp_maintenance_requests WHERE nhp_request_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    request = new MaintenanceRequest();
                    request.setId(rs.getInt("nhp_request_id"));
                    request.setFacilityId(rs.getInt("nhp_facility_id"));
                    request.setUserId(rs.getInt("nhp_user_id"));
                    request.setDescription(rs.getString("nhp_description"));
                    request.setStatus(rs.getString("nhp_status"));
                    request.setRequestDate(rs.getTimestamp("nhp_request_date"));
                    request.setCompletionDate(rs.getTimestamp("nhp_completion_date"));
                    request.setTechnicianId(rs.getInt("nhp_technician_id"));
                    request.setProgress(rs.getString("nhp_progress"));
                }
            }
        }
        return request;
    }

    // Thêm maintenance request
    public void addMaintenanceRequest(MaintenanceRequest request) throws SQLException {
        String sql = "INSERT INTO nhp_maintenance_requests (nhp_facility_id, nhp_user_id, nhp_description, nhp_status, nhp_technician_id, nhp_progress) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, request.getFacilityId(), java.sql.Types.INTEGER);
            ps.setObject(2, request.getUserId(), java.sql.Types.INTEGER);
            ps.setString(3, request.getDescription());
            ps.setString(4, request.getStatus());
            ps.setObject(5, request.getTechnicianId(), java.sql.Types.INTEGER);
            ps.setString(6, request.getProgress());
            ps.executeUpdate();
        }
    }

    // Cập nhật maintenance request
    public void updateMaintenanceRequest(MaintenanceRequest request) throws SQLException {
        String sql = "UPDATE nhp_maintenance_requests SET nhp_facility_id = ?, nhp_user_id = ?, nhp_description = ?, nhp_status = ?, nhp_completion_date = ?, nhp_technician_id = ?, nhp_progress = ? WHERE nhp_request_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, request.getFacilityId(), java.sql.Types.INTEGER);
            ps.setObject(2, request.getUserId(), java.sql.Types.INTEGER);
            ps.setString(3, request.getDescription());
            ps.setString(4, request.getStatus());
            ps.setTimestamp(5, request.getCompletionDate() != null ? new java.sql.Timestamp(request.getCompletionDate().getTime()) : null);
            ps.setObject(6, request.getTechnicianId(), java.sql.Types.INTEGER);
            ps.setString(7, request.getProgress());
            ps.setInt(8, request.getId());
            ps.executeUpdate();
        }
    }

    // Xóa maintenance request
    public void deleteMaintenanceRequest(int id) throws SQLException {
        String sql = "DELETE FROM nhp_maintenance_requests WHERE nhp_request_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}