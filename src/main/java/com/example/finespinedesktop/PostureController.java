package com.example.finespinedesktop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PostureController {
    private Application.Parameters input;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Built by Team FineSpine");
    }

    void setInput(Application.Parameters input) {
        this.input = input;
    }
}