
package com.example.demo8.controller;

import com.example.demo8.database.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Button englishButton;

    @FXML
    private Button vietnameseButton;

    // Phương thức xử lý đăng nhập
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Kiểm tra thông tin đăng nhập
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both username and password.");
        } else {
            // Kiểm tra đăng nhập từ cơ sở dữ liệu
            if (DatabaseHelper.validateUserCredentials(username, password)) {
                // Đăng nhập thành công, chuyển hướng đến màn hình Dashboard
                showAlert("Success", "Login successful.");
                openDashboard();
            } else {
                showAlert("Error", "Invalid username or password.");
            }
        }
    }

    // Phương thức xử lý quên mật khẩu
    @FXML
    private void handleForgotPassword(ActionEvent event) {
        // Chuyển đến màn hình khôi phục mật khẩu
        showAlert("Forgot Password", "Redirecting to password recovery.");
        // Bạn có thể hiển thị giao diện khôi phục mật khẩu ở đây
    }

    // Phương thức xử lý thay đổi ngôn ngữ
    @FXML
    private void handleLanguageChange(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String language = clickedButton.getText();

        if (language.equals("EN")) {
            // Thay đổi ngôn ngữ sang tiếng Anh
            showAlert("Language Change", "Language changed to English.");
        } else if (language.equals("VN")) {
            // Thay đổi ngôn ngữ sang tiếng Việt
            showAlert("Language Change", "Language changed to Vietnamese.");
        }
    }

    // Phương thức hiển thị hộp thoại cảnh báo
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Phương thức mở Dashboard
    private void openDashboard() {
        try {
            // Tải màn hình Dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo8/dashboardView.fxml"));
            VBox dashboardRoot = loader.load();  // Đảm bảo sử dụng đúng kiểu VBox nếu FXML là VBox

            // Tạo scene mới cho dashboard
            Scene dashboardScene = new Scene(dashboardRoot);

            // Lấy Stage hiện tại từ loginButton
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            if (currentStage != null) {
                currentStage.setScene(dashboardScene); // Đặt lại scene cho dashboard
                currentStage.setTitle("Dashboard");
            } else {
                System.out.println("Không thể lấy Stage hiện tại.");
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi tải FXML: " + e.getMessage());
            e.printStackTrace(); // In ra lỗi nếu có vấn đề với việc tải FXML
        }
    }

}
