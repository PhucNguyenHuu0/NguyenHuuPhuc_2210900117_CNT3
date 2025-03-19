package com.facility.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.facility.model.User;

public class UserDAO {
    // Lấy tất cả users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM nhp_users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("nhp_user_id"));
                user.setFullName(rs.getString("nhp_full_name"));
                user.setEmail(rs.getString("nhp_email"));
                user.setPassword(rs.getString("nhp_password"));
                user.setRole(rs.getString("nhp_role"));
                user.setCreatedAt(rs.getTimestamp("nhp_created_at"));
                user.setUsername(rs.getString("nhp_username"));
                user.setPhone(rs.getString("nhp_phone"));
                users.add(user);
            }
        }
        return users;
    }

    // Lấy user theo ID
    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM nhp_users WHERE nhp_user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("nhp_user_id"));
                    user.setFullName(rs.getString("nhp_full_name"));
                    user.setEmail(rs.getString("nhp_email"));
                    user.setPassword(rs.getString("nhp_password"));
                    user.setRole(rs.getString("nhp_role"));
                    user.setCreatedAt(rs.getTimestamp("nhp_created_at"));
                    user.setUsername(rs.getString("nhp_username"));
                    user.setPhone(rs.getString("nhp_phone"));
                }
            }
        }
        return user;
    }

    // Thêm user
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO nhp_users (nhp_full_name, nhp_email, nhp_password, nhp_role, nhp_username, nhp_phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPhone());
            ps.executeUpdate();
        }
    }

    // Cập nhật user
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE nhp_users SET nhp_full_name = ?, nhp_email = ?, nhp_password = ?, nhp_role = ?, nhp_username = ?, nhp_phone = ? WHERE nhp_user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        }
    }

    // Xóa user
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM nhp_users WHERE nhp_user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Xác thực người dùng
    public User authenticate(String username, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM nhp_users WHERE nhp_username = ? AND nhp_password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // Lưu ý: Nếu dùng mã hóa, cần thay đổi logic
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("nhp_user_id"));
                    user.setFullName(rs.getString("nhp_full_name"));
                    user.setEmail(rs.getString("nhp_email"));
                    user.setPassword(rs.getString("nhp_password"));
                    user.setRole(rs.getString("nhp_role"));
                    user.setCreatedAt(rs.getTimestamp("nhp_created_at"));
                    user.setUsername(rs.getString("nhp_username"));
                    user.setPhone(rs.getString("nhp_phone"));
                }
            }
        }
        return user;
    }
}