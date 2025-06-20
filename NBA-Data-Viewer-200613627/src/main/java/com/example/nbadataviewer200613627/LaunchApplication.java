package com.example.nbadataviewer200613627;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource("player-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        scene.getStylesheets().add(getClass().getResource("player-view-style.css").toExternalForm());
        stage.setTitle("NBA Stat Chart View");
        stage.setScene(scene);
        /* Sets icon */
        stage.getIcons().add(new Image(getClass().getResourceAsStream("imgs/basketball.png")));
        /* Centers app */
        stage.centerOnScreen();
        /* Disallows user from resizing */
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}