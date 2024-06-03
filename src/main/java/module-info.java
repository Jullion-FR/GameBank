module application.gamebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens application.gamebank to javafx.fxml, com.fasterxml.jackson.databind;
    exports application.gamebank;
    exports application.gamebank.controllers;
    opens application.gamebank.images to com.fasterxml.jackson.databind, javafx.fxml;
    exports application.gamebank.api;
    opens application.gamebank.api to com.fasterxml.jackson.databind, javafx.fxml;
    exports application.gamebank.results;
    opens application.gamebank.results to com.fasterxml.jackson.databind, javafx.fxml;
}