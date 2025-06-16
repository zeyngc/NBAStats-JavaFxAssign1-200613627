package com.example.nbadataviewer200613627;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TableView {
    /* Defining FXML elements */

    @FXML
    private Button returnChart;

    /* Method to close scene */
    private void closeScene(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        /* Button listener to close table view */
        returnChart.setOnAction(this::closeScene);
    }
}
