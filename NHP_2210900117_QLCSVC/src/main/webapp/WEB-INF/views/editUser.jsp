<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa Người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="/login"/>
    </c:if>
    <c:if test="${sessionScope.user.role != 'Admin' && sessionScope.user.role != 'Manager'}">
        <c:redirect url="/index.jsp"/>
    </c:if>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">QLCSVC</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <span class="nav-link">Xin chào, ${sessionScope.user.fullName}</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="header text-white">
        <h1>Chỉnh sửa Người dùng</h1>
    </header>

    <!-- Main Content -->
    <div class="container my-5 animate">
        <c:if test="${empty user}">
            <p class="text-danger">Không tìm thấy thông tin người dùng!</p>
            <a href="users" class="btn btn-custom mt-3"><i class="fas fa-arrow-left"></i> Quay lại</a>
        </c:if>
        <c:if test="${not empty error}">
            <p class="text-danger">${error}</p>
        </c:if>
        <c:if test="${not empty user}">
            <form action="updateUser" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <label>Họ tên:</label>
                <input type="text" name="fullName" value="${user.fullName}" required>
                <label>Email:</label>
                <input type="email" name="email" value="${user.email}" required>
                <label>Username:</label>
                <input type="text" name="username" value="${user.username}" required>
                <label>Mật khẩu:</label>
                <input type="password" name="password" value="${user.password}" required>
                <label>Vai trò:</label>
                <select name="role" required>
                    <option value="Admin" ${user.role == 'Admin' ? 'selected' : ''}>Admin</option>
                    <option value="Manager" ${user.role == 'Manager' ? 'selected' : ''}>Manager</option>
                    <option value="Teacher" ${user.role == 'Teacher' ? 'selected' : ''}>Teacher</option>
                    <option value="Staff" ${user.role == 'Staff' ? 'selected' : ''}>Staff</option>
                </select>
                <label>Số điện thoại:</label>
                <input type="text" name="phone" value="${user.phone}" required>
                <input type="submit" value="Cập nhật">
            </form>
            <a href="users" class="btn btn-custom mt-3"><i class="fas fa-arrow-left"></i> Quay lại</a>
        </c:if>
    </div>

    <!-- Footer -->
    <footer>
        <p>© 2025 - Hệ thống Quản lý Cơ sở Vật chất - NHP_2210900117</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>