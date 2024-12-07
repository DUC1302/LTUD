module demo8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demo8.controller to javafx.fxml;
    exports com.example.demo8.application;
    exports com.example.demo8.controller;
    exports com.example.demo8.database;
    exports com.example.demo8.model;
}

