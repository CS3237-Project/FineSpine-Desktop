package com.example.finespinedesktop;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PostureApplication extends Application {
    private Button openButton;
    private Scene scene;
    private Label heading;
    private Label content;
    private AnchorPane mainLayout;
    private DropShadow shadow;
    private ImageView imagePreview;
    private String image;
    private Boolean isNeckAngleLow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.TRANSPARENT);
        //FXMLLoader fxmlLoader = new FXMLLoader(PostureApplication.class.getResource("posture-view.fxml"));
        //Parent root = fxmlLoader.load();
        parseArguments();
        initComponents();
        setPositions();
        setStyles();
        setEventHandlers();

        mainLayout.getChildren().addAll(openButton, heading, content, imagePreview);

        scene = new Scene(mainLayout, 600, 450);
        scene.setFill(null);
        //PostureController cont = fxmlLoader.getController();
        //cont.setInput(getParameters());
        stage.setTitle("Bad Posture Alert");
        stage.setScene(scene);
        stage.show();
    }

    private void parseArguments() {

    }

    private void initComponents() {
        openButton = new Button("View your posture trends online");

        heading = new Label("Bad Posture!");

        content = new Label("Try moving back to keep your back at the chair.\n" +
                "\n" +
                "If you are leaning forward to see your computer, try to increase the height of your desk/monitor " +
                "or get a laptop stand.");

        mainLayout = new AnchorPane();

        shadow = new DropShadow();

        imagePreview = new ImageView();
    }

    private void setStyles() {
        openButton.setPrefWidth(250.0);
        openButton.setPrefHeight(50.0);
        openButton.setStyle("-fx-background-color: rgba(54, 77, 48, 1); -fx-background-radius: 12; " +
                "-fx-text-fill: white;-fx-font-size: 14px; -fx-font-weight: 700;");

        heading.setStyle("-fx-text-fill: white;-fx-font-size: 28px; -fx-font-weight: 700;");

        content.setPrefWidth(250.0);
        content.setStyle("-fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: 400;");
        content.setWrapText(true);

        imagePreview.setFitHeight(360.0);
        imagePreview.setFitWidth(180.0);

        mainLayout.setStyle("-fx-background-color: rgba(21, 31, 35, 1); -fx-background-radius: 12; -fx-border-width: 1; " +
                "-fx-border-color: black; -fx-border-radius: 12;");
    }

    private void setPositions() {
        AnchorPane.setTopAnchor(openButton, 320.0);
        AnchorPane.setLeftAnchor(openButton, 50.0);

        AnchorPane.setTopAnchor(heading, 80.0);
        AnchorPane.setLeftAnchor(heading, 50.0);

        AnchorPane.setTopAnchor(content, 130.0);
        AnchorPane.setLeftAnchor(content, 50.0);

        AnchorPane.setTopAnchor(imagePreview, 40.0);
        AnchorPane.setLeftAnchor(imagePreview, 365.0);
    }

    private void setEventHandlers() {
        openButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        openButton.setEffect(shadow);
                    }
                });

        openButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        openButton.setEffect(null);
                    }
                });
    }
}