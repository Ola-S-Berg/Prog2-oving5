package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class GUI extends Application {

  private DeckOfCards deck = new DeckOfCards();
  private List<PlayingCard> hand;
  private Label handDisplay;
  private TextField sumOfFacesField, heartsField;
  private Label flushLabel, queenSpadesLabel;

  @Override
  public void start(Stage primaryStage) {
    Button dealButton = new Button("Deal Hand");
    Button checkButton = new Button("Check Hand");
    handDisplay = new Label("Hand: ");

    sumOfFacesField = new TextField();
    sumOfFacesField.setEditable(false);

    heartsField = new TextField();
    heartsField.setEditable(false);

    flushLabel = new Label("Flush: ");
    queenSpadesLabel = new Label("Queen Spades: ");

    dealButton.setOnAction(e -> dealHand());
    checkButton.setOnAction(e -> checkHand());

    VBox rightPanel = new VBox(10, dealButton, checkButton);
    VBox bottomPanel = new VBox(5,
        new HBox(10, new Label ("Sum of faces:"), sumOfFacesField),
        new HBox(10, new Label ("Cards of hearts:"), heartsField),
        flushLabel, queenSpadesLabel);

    BorderPane root = new BorderPane();
    root.setCenter(handDisplay);
    root.setRight(rightPanel);
    root.setBottom(bottomPanel);

    Scene scene = new Scene(root, 1200, 800);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Card Game");
    primaryStage.show();
  }

  private void dealHand() {
    hand = deck.dealHand(5);
    handDisplay.setText("Hand: " + hand);
  }

  private void checkHand() {
    if (hand == null || hand.isEmpty()) return;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
