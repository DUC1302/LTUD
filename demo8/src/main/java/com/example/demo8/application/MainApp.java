package com.example.demo8.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load loginView.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo8/loginView.fxml"));
            Scene scene = new Scene(loader.load());

            // Set up the stage
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
