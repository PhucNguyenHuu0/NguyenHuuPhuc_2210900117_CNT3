<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        .login-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .login-form .form-label {
            font-weight: 600;
        }

        .login-form .input-group-text {
            background-color: #f8f9fa;
            border: 1px solid #ced4da;
            cursor: pointer;
        }

        .login-form .form-control {
            border-radius: 0.375rem;
        }

        .login-form .btn-custom {
            width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header class="header text-white">
        <h1>Đăng nhập Hệ thống</h1>
        <p>Quản lý Cơ sở Vật chất</p>
    </header>

    <!-- Main Content -->
    <div class="container my-5 animate">
        <form action="login" method="post" class="login-form">
            <div class="mb-3">
                <label for="username" class="form-label"><i class="fas fa-user me-2"></i> Tên đăng nhập:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><i class="fas fa-lock me-2"></i> Mật khẩu:</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password" required>
                    <span class="input-group-text" onclick="togglePassword()">
                        <i class="fas fa-eye" id="togglePasswordIcon"></i>
                    </span>
                </div>
            </div>
            <button type="submit" class="btn btn-custom"><i class="fas fa-sign-in-alt me-2"></i> Đăng nhập</button>
        </form>
    </div>

    <!-- Footer -->
    <footer>
        <p>© 2025 - Hệ thống Quản lý Cơ sở Vật chất - NHP_2210900117</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById("password");
            const toggleIcon = document.getElementById("togglePasswordIcon");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordInput.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }
    </script>
</body>
</html>