<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Quản lý Cơ sở Vật chất</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <!-- Kiểm tra đăng nhập -->
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="/login"/>
    </c:if>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">QLCSVC</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <span class="nav-link">Xin chào, ${sessionScope.user.fullName} (${sessionScope.user.role})</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Header -->
    <header class="header text-white text-center py-5">
        <h1 class="display-4 fw-bold">Hệ thống Quản lý Cơ sở Vật chất</h1>
        <p class="lead">Quản lý thông minh - Tương lai hiện đại</p>
    </header>

    <!-- Main Content -->
    <div class="container my-5">
        <div class="row g-4">
            <!-- Card cho Admin/Manager -->
            <c:if test="${sessionScope.user.role == 'Admin' || sessionScope.user.role == 'Manager'}">
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-tools fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Cơ sở vật chất</h5>
                            <p class="card-text">Quản lý toàn bộ cơ sở vật chất.</p>
                            <a href="facilities" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-door-open fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Phòng</h5>
                            <p class="card-text">Quản lý danh sách phòng.</p>
                            <a href="rooms" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-users fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Người dùng</h5>
                            <p class="card-text">Quản lý thông tin người dùng.</p>
                            <a href="users" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-wrench fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Yêu cầu bảo trì</h5>
                            <p class="card-text">Theo dõi yêu cầu bảo trì.</p>
                            <a href="maintenanceRequests" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-history fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Nhật ký</h5>
                            <p class="card-text">Xem lịch sử hoạt động.</p>
                            <a href="logs" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Card cho Teacher -->
            <c:if test="${sessionScope.user.role == 'Teacher'}">
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-tools fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Cơ sở vật chất</h5>
                            <p class="card-text">Xem danh sách cơ sở vật chất.</p>
                            <a href="facilities" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-door-open fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Phòng</h5>
                            <p class="card-text">Xem danh sách phòng.</p>
                            <a href="rooms" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-wrench fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Yêu cầu bảo trì</h5>
                            <p class="card-text">Xem trạng thái bảo trì.</p>
                            <a href="maintenanceRequests" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Card cho Staff -->
            <c:if test="${sessionScope.user.role == 'Staff'}">
                <div class="col-md-4 mx-auto">
                    <div class="card h-100 shadow-sm animate-card">
                        <div class="card-body text-center">
                            <i class="fas fa-wrench fa-2x mb-3 text-primary"></i>
                            <h5 class="card-title">Yêu cầu bảo trì</h5>
                            <p class="card-text">Xem và xử lý bảo trì.</p>
                            <a href="maintenanceRequests" class="btn btn-custom">Khám phá</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Footer -->
    <footer class="text-white text-center py-4 mt-5">
        <p> 2025 - Hệ thống Quản lý Cơ sở Vật chất - NHP_2210900117</p>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>