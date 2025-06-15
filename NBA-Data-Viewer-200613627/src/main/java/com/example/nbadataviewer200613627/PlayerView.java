package com.example.nbadataviewer200613627;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.chart.*;

import java.util.function.Function;
import java.util.stream.DoubleStream;

public class PlayerView {

    /* Defining FXML elements */
    @FXML
    private CheckBox pointsPerGame;

    @FXML
    private CheckBox assistsPerGame;

    @FXML
    private CheckBox reboundsPerGame;

    @FXML
    private CheckBox fgPercentage;

    @FXML
    private CheckBox threePtPercentage;

    @FXML
    private ComboBox<String> cbPlayers;

    @FXML
    private Button viewTable;

    @FXML
    private BarChart<String,Number> barChart;

    /* Defining variables used for graphing onto BarChart object */
    public CategoryAxis xAxis = new CategoryAxis();
    public NumberAxis yAxis = new NumberAxis();

    @FXML
    private void initialize(){
        /* Uses populatePlayerInfo into a stream and extracts the Player object's name value, and sends it to a list so the combo box can display it */
        cbPlayers.getItems().addAll(Player.populatePlayerInfo().stream()
                .map(Player::getPlayerName).toList());

        barChart.setTitle("NBA Stat Viewer");
        XYChart.Series<String, Number> pointsSeries = new XYChart.Series<>();

        /* ActionEvents for each CheckBox object */

        pointsPerGame.setOnAction(x -> {
            if (pointsPerGame.isSelected()) {
                xAxis.setLabel("Points Per Game");
                pointsSeries.getData().add(
                        new XYChart.Data<>(xAxis.toString(), 4)
                );

                barChart.getData().add(pointsSeries);
            } else {
                barChart.getData().remove(pointsSeries);
            }
        });

        assistsPerGame.setOnAction(x -> {
            if (assistsPerGame.isSelected()) {
                xAxis.setLabel("Assists Per Game");
            }
        });

       reboundsPerGame.setOnAction(x -> {
            if (reboundsPerGame.isSelected()) {
                xAxis.setLabel("Rebounds Per Game");
            }
        });

        fgPercentage.setOnAction(x -> {
            if (fgPercentage.isSelected()) {
                xAxis.setLabel("Field Goal %");
            }
        });

        threePtPercentage.setOnAction(x -> {
            if (threePtPercentage.isSelected()) {
                xAxis.setLabel("3Pt Field Goal %");
            }
        });
    }

}
