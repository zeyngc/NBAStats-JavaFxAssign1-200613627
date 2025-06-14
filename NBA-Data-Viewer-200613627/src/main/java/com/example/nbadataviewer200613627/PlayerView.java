package com.example.nbadataviewer200613627;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.chart.*;

public class PlayerView {

    /* Defining FXML elements */
    @FXML
    private RadioButton pointsPerGame;

    @FXML
    private RadioButton assistsPerGame;

    @FXML
    private RadioButton reboundsPerGame;

    @FXML
    private RadioButton fgPercentage;

    @FXML
    private RadioButton threePtPercentage;

    @FXML
    private ComboBox<Player> cbPlayers;

    @FXML
    private Button viewTable;

    @FXML
    private void initialize(){
        cbPlayers.getItems().addAll(Player.populatePlayerInfo());
    }
}
