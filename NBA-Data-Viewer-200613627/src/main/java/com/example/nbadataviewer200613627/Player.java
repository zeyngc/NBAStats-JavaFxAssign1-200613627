package com.example.nbadataviewer200613627;

import java.util.ArrayList;
import java.util.List;

public class Player {

    /*
    Name : string
    Points per game : double
    Assists per game : double
    Rebounds per game : double
    Field goal percentage : double
    3p FG percentage : double
    Team : List of options
     */

    /* Enum containing all possible team options - list never changes, so enum is useful here */
    enum Teams {
        Bucks, Bulls, Cavaliers, Celtics, Clippers, Grizzlies,
        Hawks, Heat, Hornets, Jazz, Kings, Knicks,
        Lakers, Magic, Mavericks, Nets, Nuggets, Pacers,
        Pelicans, Pistons, Raptors, Rockets, Sixers, Spurs,
        Suns, Thunder, Timberwolves, Trailblazers, Warriors, Wizards
    }

    private String playerName;
    private double pointsPerGame;
    private double assistsPerGame;
    private double reboundsPerGame;
    private double fgPercentage;
    private double threePtPercentage;
    private Teams team;

    /* Getters & Setters */

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public double getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(double assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public double getReboundsPerGame() {
        return reboundsPerGame;
    }

    public void setReboundsPerGame(double reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public double getFgPercentage() {
        return fgPercentage;
    }

    public void setFgPercentage(double fgPercentage) {
        this.fgPercentage = fgPercentage;
    }

    public double getThreePtPercentage() {
        return threePtPercentage;
    }

    public void setThreePtPercentage(double threePtPercentage) {
        this.threePtPercentage = threePtPercentage;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Player(String playerName, double pointsPerGame, double assistsPerGame, double reboundsPerGame, double fgPercentage, double threePtPercentage, Teams team) {
        setPlayerName(playerName);
        setPointsPerGame(pointsPerGame);
        setAssistsPerGame(assistsPerGame);
        setReboundsPerGame(reboundsPerGame);
        setFgPercentage(fgPercentage);
        setThreePtPercentage(threePtPercentage);
        setTeam(team);
    }

    /* Method to populate player information, stores a player for each team by default */
    /* All data used is pulled from basketball-reference.com */
    public static List<Player> populatePlayerInfo(){
        List<Player> player = new ArrayList<Player>();
        player.add(new Player("Giannis Antetokounmpo", 30.4, 6.5, 11.9, 60.1, 22.2, Teams.Bucks));
        player.add(new Player("Zach Lavine", 23.3, 4.2, 4.3, 51.1, 44.6, Teams.Kings));
        player.add(new Player("Donovan Mitchell", 24.0, 5.0, 4.5, 44.3, 36.8, Teams.Cavaliers));
        player.add(new Player("Jayson Tatum", 26.8, 6.0, 8.7, 45.2, 34.3, Teams.Celtics));
        player.add(new Player("James Harden", 22.8, 8.7, 5.8, 41.0, 35.2, Teams.Clippers));
        return player;
    }
}
