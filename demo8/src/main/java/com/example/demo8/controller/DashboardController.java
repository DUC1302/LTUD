package com.example.demo8.controller;

import com.example.demo8.database.DatabaseHelper;
import com.example.demo8.model.Order;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {

    @FXML
    private Label revenueLabel;
    @FXML
    private Label orderCountLabel;
    @FXML
    private Label topProductLabel;
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private TableColumn<Order, String> orderStatusColumn;
    @FXML
    private ListView<String> notificationList;

    private DatabaseHelper databaseHelper;

    public DashboardController() {
        databaseHelper = new DatabaseHelper();
    }

    public void initialize() {
        loadDashboardData();
        loadRecentOrders();
        loadNotifications();
    }

    private void loadDashboardData() {
        // Ensure database query is wrapped in try-catch block
        try {
            // Get total revenue
            String revenueQuery = "SELECT SUM(price) FROM orders";
            try (ResultSet resultSet = databaseHelper.executeQuery(revenueQuery)) {
                if (resultSet.next()) {
                    double revenue = resultSet.getDouble(1);
                    // Update UI thread to reflect changes
                    Platform.runLater(() -> revenueLabel.setText("Doanh thu: " + revenue + " VNĐ"));
                }
            }

            // Get order count
            String orderCountQuery = "SELECT COUNT(*) FROM orders";
            try (ResultSet resultSet = databaseHelper.executeQuery(orderCountQuery)) {
                if (resultSet.next()) {
                    int orderCount = resultSet.getInt(1);
                    Platform.runLater(() -> orderCountLabel.setText("Số Đơn Hàng: " + orderCount));
                }
            }

            // Get top-selling product
            String topProductQuery = "SELECT product_name FROM order_details GROUP BY product_name ORDER BY COUNT(*) DESC LIMIT 1";
            try (ResultSet resultSet = databaseHelper.executeQuery(topProductQuery)) {
                if (resultSet.next()) {
                    String topProduct = resultSet.getString(1);
                    Platform.runLater(() -> topProductLabel.setText("Sản phẩm bán chạy: " + topProduct));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadRecentOrders() {
        try {
            String query = "SELECT order_id, order_date, status FROM orders ORDER BY order_date DESC LIMIT 5";
            try (ResultSet resultSet = databaseHelper.executeQuery(query)) {

                // Clear previous data from TableView
                Platform.runLater(() -> orderTable.getItems().clear());

                while (resultSet.next()) {
                    Order order = new Order(
                            resultSet.getString("order_id"),
                            resultSet.getString("order_date"),
                            resultSet.getString("status")
                    );

                    // Add each order to the TableView
                    Platform.runLater(() -> orderTable.getItems().add(order));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadNotifications() {
        try {
            String query = "SELECT message FROM notifications ORDER BY date DESC LIMIT 5";
            try (ResultSet resultSet = databaseHelper.executeQuery(query)) {
                // Clear previous notifications
                Platform.runLater(() -> notificationList.getItems().clear());

                while (resultSet.next()) {
                    String message = resultSet.getString("message");
                    // Add notifications to ListView
                    Platform.runLater(() -> notificationList.getItems().add(message));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Handle quick navigation button clicks
    @FXML
    private void goToProductManagement() {
        // Handle scene transition to product management view
    }

    @FXML
    private void goToOrderManagement() {
        // Handle scene transition to order management view
    }

    @FXML
    private void goToCustomerManagement() {
        // Handle scene transition to customer management view
    }

    @FXML
    private void goToReports() {
        // Handle scene transition to reports view
    }
}
