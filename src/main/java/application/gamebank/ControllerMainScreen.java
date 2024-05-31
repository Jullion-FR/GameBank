package application.gamebank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class ControllerMainScreen {

    @FXML
    private TextField entry;

    @FXML
    private Button validateButton;

    @FXML
    private ImageView pictureImageView;

    private APIManager apiManager;

    public ControllerMainScreen() {
        super();
        apiManager = new APIManager();
    }
    @FXML
    public void onAction() {

        String searchedText = entry.getText();

        if (searchedText.isBlank())
            return;

        try {
            apiManager.setInformations(searchedText);
        } catch (Exception e) {
            entry.clear();
            return;
        }

    }

    @FXML
    public void keyPressed(KeyEvent event) {
        boolean value = (((TextField) event.getSource()).getText().equals(""));
        validateButton.setDisable(value);
    }


}
