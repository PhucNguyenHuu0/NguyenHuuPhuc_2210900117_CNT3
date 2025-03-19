package com.facility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.facility.model.Log;

public class LogDAO {
    // Lấy tất cả logs
    public List<Log> getAllLogs() throws SQLException {
        List<Log> logs = new ArrayList<>();
        String sql = "SELECT nhp_log_id, nhp_user_id, nhp_action FROM nhp_logs";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("nhp_log_id"));
                log.setUserId(rs.getInt("nhp_user_id"));
                log.setAction(rs.getString("nhp_action"));
                logs.add(log);
            }
        }
        return logs;
    }

    // Lấy log theo ID
    public Log getLogById(int id) throws SQLException {
        Log log = null;
        String sql = "SELECT nhp_log_id, nhp_user_id, nhp_action FROM nhp_logs WHERE nhp_log_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    log = new Log();
                    log.setId(rs.getInt("nhp_log_id"));
                    log.setUserId(rs.getInt("nhp_user_id"));
                    log.setAction(rs.getString("nhp_action"));
                }
            }
        }
        return log;
    }

    // Thêm log
    public void addLog(Log log) throws SQLException {
        String sql = "INSERT INTO nhp_logs (nhp_user_id, nhp_action) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, log.getUserId(), java.sql.Types.INTEGER);
            ps.setString(2, log.getAction());
            ps.executeUpdate();
        }
    }

    // Cập nhật log
    public void updateLog(Log log) throws SQLException {
        String sql = "UPDATE nhp_logs SET nhp_user_id = ?, nhp_action = ? WHERE nhp_log_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, log.getUserId(), java.sql.Types.INTEGER);
            ps.setString(2, log.getAction());
            ps.setInt(3, log.getId());
            ps.executeUpdate();
        }
    }

    // Xóa log
    public void deleteLog(int id) throws SQLException {
        String sql = "DELETE FROM nhp_logs WHERE nhp_log_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}