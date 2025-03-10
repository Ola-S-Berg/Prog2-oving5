package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

  private DeckOfCards deck = new DeckOfCards();
  private Label handDisplay;
  private Label resultDisplay;

  @Override
  public void start(Stage primaryStage) {
    Button dealButton = new Button("Deal Hand");
    Button playButton = new Button("Check Hand");
    handDisplay = new Label("Hand: ");
    resultDisplay = new Label("Result: ");

    VBox layout = new VBox(10, handDisplay, resultDisplay, dealButton, resultDisplay);

    Scene scene = new Scene(layout, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Card Game");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
