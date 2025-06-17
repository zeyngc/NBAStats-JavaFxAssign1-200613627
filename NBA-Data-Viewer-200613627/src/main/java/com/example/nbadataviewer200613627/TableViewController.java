package com.example.nbadataviewer200613627;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import java.util.List;

public class TableViewController {
    /* Defining FXML elements */

    @FXML
    private Button closeTable;

    @FXML
    private Button clearSelection;

    @FXML
    private TableView<Player> tableView;

    @FXML
    private ComboBox<String> cbPlayerSearch;

    @FXML
    private ImageView imageView;

    List<Player> players = Player.populatePlayerInfo();

    /* Method to close scene */
    private void closeScene(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /* Method to initialize ComboBox options and TableView */
    private void loadCbAndTable(){
        tableView.getItems().addAll(players);

        /* For every player grabbed, add their name to the combo box */
        for (Player player : players) {
            cbPlayerSearch.getItems().add(player.getPlayerName());
        }

        /* Listens for if a player is selected */
        cbPlayerSearch.getSelectionModel().selectedItemProperty().addListener((obs, value, playerToFilter) -> {
            filterTable(playerToFilter);
        });
    }

    /* Method to populate table and filter players */
    private void filterTable(String playerName){
        /* Clears tableView data */
        tableView.getItems().clear();

        /* If no player is selected, display all */
        if (playerName == null){
            tableView.getItems().addAll(players);
            imageView.setImage(null);
        } else {
            /* If a player name is selected - find the image + object matching based on name + add it to the TableView */
            for (Player player : players){
                if (player.getPlayerName().equals(playerName)) {
                    tableView.getItems().add(player);
                    loadImage(player.getPlayerName());
                    break;
                }
            }
        }
    }

    /* Method to find player image */
    private void loadImage(String playerName){
        try {
            /* Finds the image based on playerName - replaces underscores in file names with spaces */
            String imageFileName = playerName.replace(" ", "_") + ".jpg";

            /* Creates filepath with root + the playerName with proper replaced chars */
            String filePath = "imgs/" + imageFileName;
            Image image = new Image(getClass().getResourceAsStream(filePath));

            imageView.setImage(image);

            /* Prints error message and sets imageView to null if file not found */
        } catch (Exception e) {
            imageView.setImage(null);
            System.err.println("Error occurred setting player images: " + e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        /* Button listener to close table view */
        closeTable.setOnAction(this::closeScene);

        /* Clear Selection button listener */
        clearSelection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* Clears combo box value and resets the table */
                cbPlayerSearch.getSelectionModel().clearSelection();
                tableView.getItems().clear();
                tableView.getItems().addAll(players);
            }
        });

        /* Setting all TableColumn values and configuring column values */
        TableColumn<Player, String> colName = new TableColumn<>("Name");
        TableColumn<Player, Double> colPts = new TableColumn<>("Points");
        TableColumn<Player, Double> colAssists = new TableColumn<>("Assists");
        TableColumn<Player, Double> colRebounds = new TableColumn<>("Rebounds");
        TableColumn<Player, Double> colFgPercent = new TableColumn<>("Field Goal %");
        TableColumn<Player, Double> colThreePt = new TableColumn<>("3pt %");
        TableColumn<Player, Player.Teams> colTeam = new TableColumn<>("Team");

        colName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        colPts.setCellValueFactory(new PropertyValueFactory<>("pointsPerGame"));
        colAssists.setCellValueFactory(new PropertyValueFactory<>("assistsPerGame"));
        colRebounds.setCellValueFactory(new PropertyValueFactory<>("reboundsPerGame"));
        colFgPercent.setCellValueFactory(new PropertyValueFactory<>("fgPercentage"));
        colThreePt.setCellValueFactory(new PropertyValueFactory<>("threePtPercentage"));
        colTeam.setCellValueFactory(new PropertyValueFactory<>("team"));

        /* Assigns columns and calls method to pull and add player data */
        tableView.getColumns().addAll(colName, colPts, colAssists, colRebounds, colFgPercent, colThreePt, colTeam);

        /* Populates combo box and table */
        loadCbAndTable();
    }
}
