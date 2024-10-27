package com.example.colorgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {

    // Set up cube variables
    Cube cube1;
    Cube cube2;
    Cube cube3;
    Cube cube4;
    Cube cube5; // Cube I added
    Cube cube6; // Cube I added
    Cube cube7; // Cube I added
    Cube cube8; // Cube I added
    Cube cube9; // Cube I added

    // Set up Order Class Variable
    Order order;

    // Boolean for checking if you pressed all the correct cubes
    boolean allCubesCorrect;

    // Start button
    Button startButton = new Button("Start");

    // Text for showing the players score
    PointCounter pointCounter;

    // Pane
    Pane rod;

    // Colors
    CustomColors customColors = new CustomColors();

    // Label for Game Over message
    Text gameOverLabel;

    // Main Start
    @Override
    public void start(Stage stage) throws IOException {

        // Set up our scene
        rod = new Pane();
        rod.setBackground(new Background(new BackgroundFill(customColors.bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(rod, 500, 600);
        stage.setTitle("Color Memory Game");
        scene.setFill(Color.TRANSPARENT); // Hvis du ikke ønsker baggrundsfarve på scenen selv
        stage.setScene(scene);
        stage.show();

        // Set up the start button
        startButton.setLayoutX(215);
        startButton.setLayoutY(525);
        rod.getChildren().add(startButton);
        startButton.setOnAction(event -> {startGame();});

        // Set up the point counter class
        pointCounter = new PointCounter(rod);

        // Setup 4 cubes
        cube1 = new Cube(rod, 80, 100, customColors.cubeRed, 0);
        cube2 = new Cube(rod, 200, 100, customColors.cubeYellow, 1);
        cube3 = new Cube(rod, 80, 250, customColors.cubeGreen, 2);
        cube4 = new Cube(rod, 200, 250, customColors.cubeBlue, 3);
        cube5 = new Cube(rod, 320, 100, customColors.cubeOrange, 4); // Cube I added
        cube6 = new Cube(rod, 320, 250, customColors.cubePurple, 5);// Cube I added
        cube7 = new Cube(rod,80, 400, customColors.cubeMagenta, 6);// Cube I added
        cube8 = new Cube(rod, 200, 400, customColors.cubeCyan, 7); // Cube I added
        cube9 = new Cube(rod, 320, 400, customColors.cubeNeongreen, 8); // Cube I added

        /*
        Create mouse events for pressing a cube. Mouse events are created in the main class, so it's easier to
          do different methods in other classes.
        */
        cube1.setOnMouseClicked(mouseEvent -> pressedCube(cube1));
        cube2.setOnMouseClicked(mouseEvent -> pressedCube(cube2));
        cube3.setOnMouseClicked(mouseEvent -> pressedCube(cube3));
        cube4.setOnMouseClicked(mouseEvent -> pressedCube(cube4));
        cube5.setOnMouseClicked(mouseEvent -> pressedCube(cube5)); // Cube I added
        cube6.setOnMouseClicked(mouseEvent -> pressedCube(cube6)); // Cube I added
        cube7.setOnMouseClicked(mouseEvent -> pressedCube(cube7)); // Cube I added
        cube8.setOnMouseClicked(mouseEvent -> pressedCube(cube8)); // Cube I added
        cube9.setOnMouseClicked(mouseEvent -> pressedCube(cube9)); // Cube I added

        // Set up the order variable with our new cubes
        Cube[] cubes = {cube1, cube2, cube3, cube4, cube5, cube6, cube7, cube8, cube9};
        order = new Order(this, cubes);

        // Set up Game Over label
        gameOverLabel = new Text("GAME OVER!");
        gameOverLabel.setFont(new Font("Arial", 56));
        gameOverLabel.setFill(Color.WHITE);
        gameOverLabel.setStroke(Color.BLACK);
        gameOverLabel.setStrokeWidth(2.5);
        gameOverLabel.setLayoutX(75);
        gameOverLabel.setLayoutY(200);
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);
        gameOverLabel.setVisible(false);
        rod.getChildren().add(gameOverLabel);
    }

    // Start game (or restart after game over)
    public void startGame() {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Hide Game Over message if it's visible
        gameOverLabel.setVisible(false);

        // Hide button
        startButton.setVisible(false);

        // Reset cubes and points
        cube1.setVisible(true);
        cube2.setVisible(true);
        cube3.setVisible(true);
        cube4.setVisible(true);
        cube5.setVisible(true); // Cube I added
        cube6.setVisible(true); // Cube I added
        cube7.setVisible(true); // Cube I added
        cube8.setVisible(true); // Cube I added
        cube9.setVisible(true); // Cube I added

        pointCounter.setPoints(0);

        scheduler.schedule(() -> {
            // Start the game again
            order.addNewCubeToOrder();
            order.playAllCubes();
        }, 1, TimeUnit.SECONDS);
        
        // Reset button text
        startButton.setText("Start");
    }

    // Disable pressing the cubes
    public void disableAllCubes() {
        cube1.setDisable(true);
        cube2.setDisable(true);
        cube3.setDisable(true);
        cube4.setDisable(true);
        cube5.setDisable(true); // Cube I added
        cube6.setDisable(true); // Cube I added
        cube7.setDisable(true); // Cube I added
        cube8.setDisable(true); // Cube I added
        cube9.setDisable(true); // Cube I added
    }

    // Enable pressing the cubes
    public void enableAllCubes() {
        cube1.setDisable(false);
        cube2.setDisable(false);
        cube3.setDisable(false);
        cube4.setDisable(false);
        cube5.setDisable(false); // Cube I added
        cube6.setDisable(false); // Cube I added
        cube7.setDisable(false); // Cube I added
        cube8.setDisable(false); // Cube I added
        cube9.setDisable(false); // Cube I added
    }

    // Method for what happens when pressing a cube
    public void pressedCube(Cube cubePressed) {
        cubePressed.scaleCube(cubePressed, true);

        // Make new scheduler to make an animation delay after you have pressed all the right cubes
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Check if the correct cube is pressed
        if (cubePressed.getCubeID(cubePressed) == order.tempOrderArray.getFirst()) {
            //System.out.println("Correct Cube Pressed");
            order.tempOrderArray.removeFirst();
            if (order.tempOrderArray.isEmpty()) {
                allCubesCorrect = true;
                cubePressed.disablePress();
                order.addNewCubeToOrder();
                order.resetTempOrder();
                pointCounter.addPoint();

                // When all methods are executed, wait 2 second and run the method "playAllCubes"
                scheduler.schedule(() -> {
                    order.playAllCubes();
                }, 2, TimeUnit.SECONDS);
            }
        }
        else {
            lostGame();
        }
    }

    // Lost Game method
    public void lostGame() {
        allCubesCorrect = false;

        // Show Game Over message
        gameOverLabel.setVisible(true);

        // Update start button text to "Restart Game"
        startButton.setText("Restart Game");
        startButton.setVisible(true);

        // Hide cubes and reset game state
        cube1.setVisible(false);
        cube2.setVisible(false);
        cube3.setVisible(false);
        cube4.setVisible(false);
        cube5.setVisible(false); // Cube I added
        cube6.setVisible(false); // Cube I added
        cube7.setVisible(false); // Cube I added
        cube8.setVisible(false); // Cube I added
        cube9.setVisible(false); // Cube I added
        //pointCounter.setPoints(0);

        // Reset order arrays
        order.orderArray.clear();
        order.tempOrderArray.clear();
    }

    // Main
public static void main(String[] args) {
    launch();
    }
}
