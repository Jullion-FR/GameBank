package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.games.Game;
import application.gamebank.games.MyGames;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class CanOpenGameDetails {
    protected MyGames games;

    public JeuController openGameDetails(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/jeu.fxml"));
            Scene scene = new Scene(loader.load());
            Game game = games.getAllGames().get(Integer.parseInt(((Node) event.getSource()).getId()));
            JeuController control = loader.getController();
            Scene thisScene = ((Pane) event.getSource()).getScene();
            control.setLastScene(thisScene);
            control.chargerJeu(game);
            ((Stage) thisScene.getWindow()).setScene(scene);
            return control;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}