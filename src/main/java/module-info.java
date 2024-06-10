module application.gamebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens application.gamebank to javafx.fxml, com.fasterxml.jackson.databind;
    opens application.gamebank.controllers to com.fasterxml.jackson.databind, javafx.fxml;
    opens application.gamebank.api to com.fasterxml.jackson.databind, javafx.fxml;
    opens application.gamebank.results to com.fasterxml.jackson.databind, javafx.fxml;

    exports application.gamebank;
    exports application.gamebank.controllers;
    exports application.gamebank.api;
    exports application.gamebank.results;
    exports application.gamebank.games;
    exports application.gamebank.vue;
    exports application.gamebank.tri;
}