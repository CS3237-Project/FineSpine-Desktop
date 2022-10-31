package com.example.finespinedesktop;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostureApplication extends Application {
    private Button openButton;
    private Scene scene;
    private Label heading;
    private Label content;
    private AnchorPane mainLayout;
    private DropShadow shadow;
    private ImageView imagePreview;
    private String anglePath = "../CS3237/DesktopPackage/angle.txt";
    private String imagePath = "../CS3237/DesktopPackage/bad-posture.jpg";
    private Boolean isNeckAngleLow;
    private Image postureImage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.TRANSPARENT);

        getAssets();
        initComponents();
        setPositions();
        setStyles();
        setEventHandlers();
        setContent(); // every 5 seconds

        mainLayout.getChildren().addAll(openButton, heading, content, imagePreview);

        scene = new Scene(mainLayout, 600, 450);
        scene.setFill(null);

        stage.setTitle("Bad Posture Alert");
        stage.setScene(scene);
        stage.show();
    }

    private void getAssets() {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(anglePath)));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        isNeckAngleLow = data.equals("LO");

        File imageFile = new File(imagePath);
        String imageLocation = imageFile.toURI().toString();
        postureImage = new Image(imageLocation);
    }

    private void initComponents() {
        openButton = new Button("View your posture trends online");

        heading = new Label("Bad Posture!");

        content = new Label();

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
        imagePreview.setStyle("-fx-background-radius: 12; -fx-border-radius: 12;");

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

    private void setContent() {
        if (isNeckAngleLow) {
            content.setText("Try moving back to keep your back at the chair.\n" +
                    "\n" +
                    "If you are leaning forward to see your computer, try to increase the height of your " +
                    "desk/monitor or get a laptop stand.");
        } else {
            content.setText("Try moving your back forward and your torso behind to keep your back at the chair.\n" +
                    "\n" +
                    "If you are leaning behind due to the shape of your chair, try using a pillow or getting an " +
                    "adjustable chair for your needs.");
        }

        imagePreview.setImage(postureImage);
    }
}