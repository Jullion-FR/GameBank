package application.gamebank;

import application.gamebank.controllers.AccueilController;
import application.gamebank.controllers.RechercheController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static AccueilController accueilController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/accueil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            accueilController = loader.getController();
            accueilController.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
