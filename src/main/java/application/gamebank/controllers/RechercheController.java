package application.gamebank.controllers;

import application.gamebank.Main;
import application.gamebank.api.APIManager;
import application.gamebank.api.GameNotFoundException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RechercheController extends gameViewer {

    @FXML
    private TextField entry;

    private APIManager apiManager;

    @FXML
    public void initialize() {
        apiManager = new APIManager();
    }

    @FXML
    public void onAction() {
        String searchedText = entry.getText();

        if (searchedText.isBlank())
            return;

        System.out.println("Recherche en cours...");

        entry.clear();

        Task<Void> searchTask = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    games = apiManager.setInformations(searchedText, vueActuelle.getMaxGridLength() * 4);
                } catch (GameNotFoundException e) {
                    updateMessage("Aucun résultat");
                    System.out.println("Rien trouvé");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void succeeded() {
                fillView();
                setCursorLoading(false);
                System.out.println("Done.");
            }
            @Override
            protected void failed() {
                setCursorLoading(false);
                scrollPane.setContent(new Label("Erreur lors de la recherche"));
            }

            @Override
            protected void cancelled() {
                setCursorLoading(false);
            }
        };

        setCursorLoading(true);

        new Thread(searchTask).start();
    }

    private void setCursorLoading(boolean isLoading) {
        Scene scene = entry.getScene();
        if (isLoading) {
            scene.setCursor(Cursor.WAIT);
        } else {
            scene.setCursor(Cursor.DEFAULT);
        }
    }


    @FXML
    void closeWindow(MouseEvent event) {
        ((Stage) thisScene.getWindow()).close();
    }
}
