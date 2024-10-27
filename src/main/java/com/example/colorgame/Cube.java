package com.example.colorgame;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Cube extends Rectangle {

    // Variables
    public int cubeID;
    public int initialWidth = 100;
    public int initialHeight = 100;

    // Constructor
    public Cube(Pane rod, int x, int y, Color color, int cubeID) {

        // Set up the cube using the variables from the constructor
        super(x, y, 100, 100);
        this.setWidth(initialWidth);
        this.setHeight(initialHeight);
        this.setFill(color);
        this.cubeID = cubeID;
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(5);

        // Add Cube To Scene
        rod.getChildren().add(this);
    }

    // Get the ID of this cube
    public int getCubeID(Cube cubePressed) {
        return cubeID;
    }

    // Method for scaling the cube and reversing to the original size
    public ScaleTransition scaleCube(Node node, boolean mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.setDuration(Duration.seconds(0.3));
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setNode(node);

        if (mouseEvent) {
            disablePress();
            scaleTransition.setOnFinished(event -> {
                enablePress();
            });
            scaleTransition.play();
        }
        return scaleTransition;
    }

    // Enable being able to press the cubes with the mouse
    public void enablePress() {
        this.setDisable(false);
    }

    // Disable being able to press the cubes with the mouse
    public void disablePress() {
        this.setDisable(true);
    }
}
