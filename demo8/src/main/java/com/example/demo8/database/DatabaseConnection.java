package com.example.demo8.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/shop_management"; // Thay thế với tên cơ sở dữ liệu của bạn
    private static final String USER = "root"; // Thay thế với tên người dùng cơ sở dữ liệu của bạn
    private static final String PASSWORD = "123456"; // Thay thế với mật khẩu cơ sở dữ liệu của bạn

    // Phương thức để tạo kết nối cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu", e);
        }
    }
}
