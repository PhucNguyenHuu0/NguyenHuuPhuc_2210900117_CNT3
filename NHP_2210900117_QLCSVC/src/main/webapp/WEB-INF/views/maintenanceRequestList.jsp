<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Yêu cầu Bảo trì</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="/login"/>
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
        <h1>Danh sách Yêu cầu Bảo trì</h1>
    </header>

    <!-- Main Content -->
    <div class="container my-5 animate">
        <c:if test="${not empty error}">
            <p class="text-danger">${error}</p>
        </c:if>
        <c:if test="${sessionScope.user.role == 'Admin' || sessionScope.user.role == 'Manager' || sessionScope.user.role == 'Staff'}">
            <a href="addMaintenanceRequest" class="btn btn-custom mb-3"><i class="fas fa-plus"></i> Thêm yêu cầu mới</a>
        </c:if>
        <table>
            <tr>
                <th>ID</th>
                <th>ID Cơ sở vật chất</th>
                <th>ID Người yêu cầu</th>
                <th>Mô tả</th>
                <th>Trạng thái</th>
                <th>Ngày yêu cầu</th>
                <th>Ngày hoàn thành</th>
                <th>ID Kỹ thuật viên</th>
                <th>Tiến độ</th>
                <th>Hành động</th>
            </tr>
            <c:if test="${empty requestList}">
                <tr>
                    <td colspan="10">Không có yêu cầu bảo trì nào!</td>
                </tr>
            </c:if>
            <c:forEach var="request" items="${requestList}">
                <tr>
                    <td>${request.id}</td>
                    <td>${request.facilityId}</td>
                    <td>${request.userId}</td>
                    <td>${request.description}</td>
                    <td>${request.status}</td>
                    <td>${request.requestDate}</td>
                    <td>${request.completionDate}</td>
                    <td>${request.technicianId}</td>
                    <td>${request.progress}</td>
                    <td>
                        <c:if test="${sessionScope.user.role == 'Admin' || sessionScope.user.role == 'Manager' || sessionScope.user.role == 'Staff'}">
                            <a href="updateMaintenanceRequest?id=${request.id}"><i class="fas fa-edit"></i> Sửa</a> |
                            <a href="deleteMaintenanceRequest?id=${request.id}" 
                               onclick="return confirm('Bạn có chắc muốn xóa?')"><i class="fas fa-trash"></i> Xóa</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp" class="btn btn-custom mt-3"><i class="fas fa-arrow-left"></i> Quay lại</a>
    </div>

    <!-- Footer -->
    <footer>
        <p>© 2025 - Hệ thống Quản lý Cơ sở Vật chất - NHP_2210900117</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>