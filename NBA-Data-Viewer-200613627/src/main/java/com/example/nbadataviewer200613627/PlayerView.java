package com.example.nbadataviewer200613627;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Button clearSelection;

    @FXML
    private BarChart<String,Number> barChart;

    @FXML
    public CategoryAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    /* Declare series as instance variables */
    private XYChart.Series<String, Number> pointsSeries;
    private XYChart.Series<String, Number> assistSeries;
    private XYChart.Series<String, Number> reboundsSeries;
    private XYChart.Series<String, Number> fgSeries;
    private XYChart.Series<String, Number> threePtSeries;

    /* Map of all team colours */
    private final Map<Player.Teams, String> teamColors = Map.ofEntries(
            Map.entry(Player.Teams.Hawks, "#E03A3E"),
            Map.entry(Player.Teams.Celtics, "#008348"),
            Map.entry(Player.Teams.Nets, "#000000"),
            Map.entry(Player.Teams.Hornets, "#1D1160"),
            Map.entry(Player.Teams.Bulls, "#CE1141"),
            Map.entry(Player.Teams.Cavaliers, "#6F263D"),
            Map.entry(Player.Teams.Mavericks, "#0053BC"),
            Map.entry(Player.Teams.Nuggets, "#0E2240"),
            Map.entry(Player.Teams.Pistons, "#C8102E"),
            Map.entry(Player.Teams.Warriors, "#1D428A"),
            Map.entry(Player.Teams.Rockets, "#BA0026"),
            Map.entry(Player.Teams.Pacers, "#002D62"),
            Map.entry(Player.Teams.Clippers, "#1D428A"),
            Map.entry(Player.Teams.Lakers, "#552583"),
            Map.entry(Player.Teams.Grizzlies, "#5D76A9"),
            Map.entry(Player.Teams.Heat, "#98002E"),
            Map.entry(Player.Teams.Bucks, "#00471B"),
            Map.entry(Player.Teams.Timberwolves, "#0C2340"),
            Map.entry(Player.Teams.Pelicans, "#002B5C"),
            Map.entry(Player.Teams.Knicks, "#006BB6"),
            Map.entry(Player.Teams.Thunder, "#007AC1"),
            Map.entry(Player.Teams.Magic, "#0077C0"),
            Map.entry(Player.Teams.Sixers, "#ED174C"),
            Map.entry(Player.Teams.Suns, "#E56020"),
            Map.entry(Player.Teams.Trailblazers, "#B40101"),
            Map.entry(Player.Teams.Kings, "#5A2B81"),
            Map.entry(Player.Teams.Spurs, "#C4CED4"),
            Map.entry(Player.Teams.Raptors, "#B4975A"),
            Map.entry(Player.Teams.Jazz, "#F9A01B"),
            Map.entry(Player.Teams.Wizards, "#E31837")
    );

    /* Checks if a specific player is selected, or if none (which represents all) are selected */
    private List<Player> filteredPlayers(){
        String selectedPlayer = cbPlayers.getValue();

        if (selectedPlayer == null) {
            return Player.populatePlayerInfo();
        } else {
            return Player.populatePlayerInfo().stream().filter(p -> p.getPlayerName()
                    .equals(selectedPlayer)).collect(Collectors.toList());
        }
    }

    /* Helper method to force chart layout refresh
    -- Found using: https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx */
    private void refreshChart() {
        Platform.runLater(() -> {
            barChart.layout();
            barChart.requestLayout();
        });
    }

    /* Helper method to update categories based on what data is being actively drawn */
    private void updateCategories() {
        if (hasActiveCheckbox()) {
            List<String> playerNames = filteredPlayers().stream()
                    .map(Player::getPlayerName).distinct().toList();
            xAxis.setCategories(javafx.collections.FXCollections.observableArrayList(playerNames));
        } else {
            xAxis.getCategories().clear();
        }
    }

    /* CheckBox state check; checks if CheckBox object is selected */
    private boolean hasActiveCheckbox() {
        return pointsPerGame.isSelected() || assistsPerGame.isSelected() ||
                reboundsPerGame.isSelected() || fgPercentage.isSelected() ||
                threePtPercentage.isSelected();
    }

    /* Method to clear all checkboxes and data states */
    private void checkboxClear(){
        /* Deselects all checkboxes */
        pointsPerGame.setSelected(false);
        assistsPerGame.setSelected(false);
        reboundsPerGame.setSelected(false);
        fgPercentage.setSelected(false);
        threePtPercentage.setSelected(false);

        /* Clear chart and series data */
        barChart.getData().clear();
        xAxis.getCategories().clear();

        pointsSeries.getData().clear();
        assistSeries.getData().clear();
        reboundsSeries.getData().clear();
        fgSeries.getData().clear();
        threePtSeries.getData().clear();

        /* Force refresh method */
        refreshChart();
    }

    @FXML
    private void initialize(){
        /* Creates XYChart Series for usage in data plotting */
        pointsSeries = new XYChart.Series<>();
        assistSeries = new XYChart.Series<>();
        reboundsSeries = new XYChart.Series<>();
        fgSeries = new XYChart.Series<>();
        threePtSeries = new XYChart.Series<>();

        /* Set series names */
        pointsSeries.setName("Points Per Game");
        assistSeries.setName("Assists Per Game");
        reboundsSeries.setName("Rebounds Per Game");
        fgSeries.setName("Field Goal %");
        threePtSeries.setName("3 Point Field Goal %");

        /* Clear Selection button listener */
        clearSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* Clears combo box value */
                cbPlayers.getSelectionModel().clearSelection();

                /* Method to clear states of items */
                checkboxClear();
            }
        });

        /* Button listener to switch scenes */
        viewTable.setOnAction(e->{
            try {
                /* Launches new scene */
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource("table-view.fxml"));
                TableViewController controller = new TableViewController();
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load(), 675, 500);

                /* Styling stage - not centering as table view acts as a secondary window within the flow of the app */
                scene.getStylesheets().add(getClass().getResource("table-view-style.css").toExternalForm());
                stage.setTitle("NBA Stats Table View");
                stage.setScene(scene);
                stage.getIcons().add(new Image(getClass().getResourceAsStream("imgs/basketball.png")));
                stage.setResizable(false);
                stage.show();

                /* Catches error and prints message */
            } catch (Exception error) {
                System.err.println("Error found: " + error.getMessage());
                System.err.println("Error cause: " + error.getCause());
            }
        });

        cbPlayers.setOnAction(c -> {
            checkboxClear();
        });

        /* Rotates players names on the xAxis for readability */
        xAxis.setTickLabelRotation(45);

        /* Uses populatePlayerInfo into a stream and extracts the Player object's name value,
        and sends it to a list so the combo box can display it */
        cbPlayers.getItems().addAll(Player.populatePlayerInfo().stream()
                .map(Player::getPlayerName).toList());

        /* BarChart object styling */
        barChart.setTitle("NBA Stats");
        barChart.setAnimated(false);

        /* ActionEvents for each CheckBox object */

        pointsPerGame.setOnAction(x -> {
            if (pointsPerGame.isSelected()) {
                /* Clears data and updates xAxis values */
                updateCategories();
                pointsSeries.getData().clear();

                /* For each player to plot */
                for (Player p : filteredPlayers()) {
                    /* Creating new data node */
                    XYChart.Data<String, Number> data = new XYChart.Data<>(p.getPlayerName(), p.getPointsPerGame());

                    /* Plotting data */
                    pointsSeries.getData().add(data);

                    /* Attaching a node listener to change colour of point bar inside chart */
                    data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                        if (newNode != null) {
                            /* Gets teamColor and styles new bar with it */
                            String teamColor = teamColors.get(p.getTeam());
                            /* Sets new bar color */
                            newNode.setStyle("-fx-bar-fill: " + teamColor + ";");
                        }
                    });
                }

                /* Checks for duplicate data entries */
                if (!barChart.getData().contains(pointsSeries)) {
                    barChart.getData().add(pointsSeries);
                }
            } else {
                /* Removes data and updates graph so bar width is reset */
                pointsSeries.getData().clear();
                barChart.getData().remove(pointsSeries);
                updateCategories();
            }
            refreshChart();
        });

        assistsPerGame.setOnAction(x -> {
            if (assistsPerGame.isSelected()) {
                updateCategories();
                assistSeries.getData().clear();

                /* For each player to plot */
                for (Player p : filteredPlayers()) {
                    /* Creating new data node */
                    XYChart.Data<String, Number> data = new XYChart.Data<>(p.getPlayerName(), p.getAssistsPerGame());

                    /* Plotting data */
                    assistSeries.getData().add(data);
                }

                if (!barChart.getData().contains(assistSeries)) {
                    barChart.getData().add(assistSeries);
                }
            } else {
                assistSeries.getData().clear();
                barChart.getData().remove(assistSeries);
                updateCategories();
            }
            refreshChart();
        });

        reboundsPerGame.setOnAction(x -> {
            if (reboundsPerGame.isSelected()) {
                updateCategories();
                reboundsSeries.getData().clear();

                for (Player p : filteredPlayers()) {
                    XYChart.Data<String, Number> data = new XYChart.Data<>(p.getPlayerName(), p.getReboundsPerGame());
                    reboundsSeries.getData().add(data);
                }

                if (!barChart.getData().contains(reboundsSeries)) {
                    barChart.getData().add(reboundsSeries);
                }
            } else {
                reboundsSeries.getData().clear();
                barChart.getData().remove(reboundsSeries);
                updateCategories();
            }
            refreshChart();
        });

        fgPercentage.setOnAction(x -> {
            if (fgPercentage.isSelected()) {
                updateCategories();
                fgSeries.getData().clear();

                for (Player p : filteredPlayers()) {
                    XYChart.Data<String, Number> data = new XYChart.Data<>(p.getPlayerName(), p.getFgPercentage());
                    fgSeries.getData().add(data);
                }

                if (!barChart.getData().contains(fgSeries)) {
                    barChart.getData().add(fgSeries);
                }
            } else {
                fgSeries.getData().clear();
                barChart.getData().remove(fgSeries);
                updateCategories();
            }
            refreshChart();
        });

        threePtPercentage.setOnAction(x -> {
            if (threePtPercentage.isSelected()) {
                updateCategories();
                threePtSeries.getData().clear();

                for (Player p : filteredPlayers()) {
                    XYChart.Data<String, Number> data = new XYChart.Data<>(p.getPlayerName(), p.getThreePtPercentage());
                    threePtSeries.getData().add(data);
                }

                if (!barChart.getData().contains(threePtSeries)) {
                    barChart.getData().add(threePtSeries);
                }
            } else {
                threePtSeries.getData().clear();
                barChart.getData().remove(threePtSeries);
                updateCategories();
            }
            refreshChart();
        });
    }
}