package edu.ntnu.idatt2003.cardgame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

  private final List<PlayingCard> deck;

  private final char [] suit = { 'S', 'H', 'D', 'C' };
  private final Random random = new Random();

  /**
   * Creates a deck of 52 cards. s = suit, f = faces.
   */
  public DeckOfCards() {
    deck = new ArrayList<>();

    for (char s : suit) {
      for (int f = 1; f <= 13; f++) {
        deck.add(new PlayingCard(s, f));
      }
    }
  }

  public List<PlayingCard> dealHand(int n) {
    List <PlayingCard> collection = new ArrayList<>(deck);
    Collections.shuffle(collection, random);

    return collection.subList(0, n);
  }
}
