package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import dal.DBHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            // Kiểm tra thông tin đăng nhập
            if (isValidUser(email, password)) {
                // Đăng nhập thành công
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email); // Lưu email vào session
                response.sendRedirect("home_page.jsp"); // Chuyển hướng đến trang chính
            } else {
                // Đăng nhập thất bại
                request.setAttribute("errorMessage", "Email hoặc mật khẩu không chính xác.");
                request.getRequestDispatcher("login.jsp").forward(request, response); // Quay lại trang đăng nhập với thông báo lỗi
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi
            request.setAttribute("errorMessage", "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidUser(String email, String password) throws Exception {
        boolean isValid = false;
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?"; // Kiểm tra thông tin đăng nhập

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password); // So sánh mật khẩu dạng plaintext
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isValid = true; // Nếu tìm thấy bản ghi, người dùng hợp lệ
                }
            }
        }
        return isValid; // Trả về kết quả
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}
