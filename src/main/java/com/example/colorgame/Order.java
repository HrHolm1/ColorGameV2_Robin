package com.example.colorgame;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Order {

    // Create MainApplication variable so we have a reference to the MainApplication Class
    MainApplication mainApp;

    // Create Array of Cube Order
    ArrayList<Integer> orderArray = new ArrayList<>();

    // Create a temporary array, so it's easy to keep track of which cubes have been pressed.
    ArrayList<Integer> tempOrderArray = new ArrayList<>();

    // Create Array To Keep Track of Cubes
    ArrayList<Cube> cubesArray = new ArrayList<>();

    // create instance of Random class
    Random randomCube = new Random();

    // Constructor
    public Order(MainApplication mainApplication,Cube[] cubes) {
        mainApp = mainApplication;
        for (Cube cube : cubes) {
            cubesArray.add(cube);
        }
    }

    // Reset the temporary order, so we can refill "tempOrderArray" with "orderArray"
    public void resetTempOrder() {
        tempOrderArray.clear();
        tempOrderArray.addAll(orderArray);
    }

    // Add a New Cube To The Order
    public void addNewCubeToOrder() {
        int randomNumber = randomCube.nextInt(9);
        orderArray.add(randomNumber);
        tempOrderArray.add(randomNumber);
    }

    // Play Animations For All Cubes In Order
    public void playAllCubes() {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (int x : orderArray) {
            Cube cubeToAnimate = cubesArray.get(x);
            // Tilføj ScaleTransition fra cube til sequentialTransition
            sequentialTransition.getChildren().add(cubeToAnimate.scaleCube(cubeToAnimate, false));
        }

        // Play Animations For All Cubes In The Correct order
        sequentialTransition.play();

        // Disable touching the cubes, while the order is being played
        mainApp.disableAllCubes();

        // Enable touching the cubes again, after the order is done playing
        sequentialTransition.setOnFinished(event -> mainApp.enableAllCubes());
    }
}
