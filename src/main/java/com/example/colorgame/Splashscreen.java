package com.example.colorgame;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Splashscreen extends MainApplication {

    @Override
    public void start(Stage primaryStage) {
        // Create the splash screen
        Stage splashStage = new Stage();

        // Create a rectangle as background
        Rectangle background = new Rectangle(500, 300, Color.GREEN);

        // Create a label for the title
        Label title = new Label("Color Memory Game");
        title.setTextFill(Color.GHOSTWHITE);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create a layout and add elements
        StackPane splashLayout = new StackPane();
        splashLayout.getChildren().addAll(background, title);

        // Create the splash scene
        Scene splashScene = new Scene(splashLayout);
        splashStage.setScene(splashScene);
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.show();

        // Create a FadeTransition for fade-in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), splashLayout);
        fadeIn.setFromValue(0); // Start fully transparent
        fadeIn.setToValue(1); // End fully opaque

        // Create a FadeTransition for fade-out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splashLayout);
        fadeOut.setFromValue(1); // Start fully opaque
        fadeOut.setToValue(0); // End fully transparent

        // Set up the transition sequence
        fadeIn.setOnFinished(e -> {
            // Start fade-out after fade-in is done
            new Thread(() -> {
                try {
                    Thread.sleep(2000); // Show splash for 2 seconds
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // Start fade-out
                fadeOut.play();
            }).start();
        });

        fadeOut.setOnFinished(e -> {
            // Close the splash stage
            splashStage.close();
            // Launch the main application
            try {
                MainApplication mainApp = new MainApplication();
                mainApp.start(primaryStage); // Use the same primaryStage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Start the fade-in effect
        fadeIn.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
