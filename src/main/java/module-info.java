module application.gamebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens application.gamebank to javafx.fxml, com.fasterxml.jackson.databind;
    exports application.gamebank;
}