package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class GUI extends Application {

  private DeckOfCards deck = new DeckOfCards();
  private List<PlayingCard> hand;
  private Label handDisplay;
  private TextField sumOfFacesField, heartsField;
  private Label flushLabel, queenSpadesLabel;
  private HBox cardDisplay; // For showing cards as images

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

    cardDisplay = new HBox(10);

    VBox rightPanel = new VBox(10, dealButton, checkButton);

    VBox bottomPanel = new VBox(5,
        new HBox(10, new Label("Sum of faces:"), sumOfFacesField),
        new HBox(10, new Label("Cards of hearts:"), heartsField),
        flushLabel, queenSpadesLabel);

    BorderPane root = new BorderPane();
    root.setCenter(cardDisplay);
    root.setRight(rightPanel);
    root.setBottom(bottomPanel);

    Scene scene = new Scene(root, 1200, 800);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Card Game");
    primaryStage.show();
  }

  private void dealHand() {
    hand = deck.dealHand(5);
    cardDisplay.getChildren().clear();

    for (PlayingCard card : hand) {
      VBox cardBox = createCardDisplay(card);
      cardDisplay.getChildren().add(cardBox);
    }

    checkHand();
  }

  private VBox createCardDisplay(PlayingCard card) {
    Image img = new Image(getCardImagePath(card.getSuit()));
    ImageView imageView = new ImageView(img);
    imageView.setFitWidth(120);
    imageView.setFitHeight(150);

    Label label = new Label(card.getAsString());

    VBox box = new VBox(imageView, label);
    box.setStyle("-fx-border-color: black; -fx-padding: 10;");
    return box;
  }

  private String getCardImagePath(char suit) {
    switch (suit) {
      case 'H':
        return "file:src/main/java/edu/ntnu/idatt2003/cardgame/images/Hearts.png";
      case 'D':
        return "file:src/main/java/edu/ntnu/idatt2003/cardgame/images/Diamonds.png";
      case 'S':
        return "file:src/main/java/edu/ntnu/idatt2003/cardgame/images/Spades.png";
      case 'C':
        return "file:src/main/java/edu/ntnu/idatt2003/cardgame/images/Clubs.png";
      default:
        return "file:src/main/java/edu/ntnu/idatt2003/cardgame/images/default.png"; // Fallback image
    }
  }

  private void checkHand() {
    if (hand == null || hand.isEmpty()) return;

    int sum = hand.stream().mapToInt(PlayingCard::getFace).sum();
    sumOfFacesField.setText(String.valueOf(sum));

    String hearts = hand.stream()
        .filter(card -> card.getSuit() == 'H')
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(", "));
    heartsField.setText(hearts.isEmpty() ? "No Hearts" : hearts);

    boolean hasQueenOfSpades = hand.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
    queenSpadesLabel.setText("Queen of Spades: " + (hasQueenOfSpades ? "Yes" : "No"));

    Set<Character> suits = hand.stream().map(PlayingCard::getSuit).collect(Collectors.toSet());
    flushLabel.setText("Flush: " + (suits.size() == 1 ? "Yes" : "No"));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
