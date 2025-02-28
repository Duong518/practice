package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=test"; // Đảm bảo đúng tên cơ sở dữ liệu
    private static final String USER = "Duong"; // Tên người dùng
    private static final String PASSWORD = "1234"; // Mật khẩu

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver không tìm thấy: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            throw e; // Ném lại ngoại lệ để xử lý ở nơi khác
        }
        return connection;
    }

}
