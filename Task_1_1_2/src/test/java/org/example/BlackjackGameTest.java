package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackGameTest {

    BlackjackGame game;
    Player player;
    Player dealer;
    DeckOfCards deck;

    @BeforeEach
    void setUp() {
        game = new BlackjackGame();
        player = new Player();
        dealer = new Player();
        deck = new DeckOfCards();
    }

    @Test
    void testDrawCard() {
        Card card = game.drawCard(player, deck);
        assertNotNull(card);
        assertEquals(1, player.hand.size());
    }

    @Test
    void testDrawCardFromEmptyDeck() {
        deck.cards.clear();
        Card card = game.drawCard(player, deck);
        assertNull(card);
        assertEquals(0, player.hand.size());
    }

    @Test
    void testGetInputInt() {
        String input = "1\n";
        Scanner sc = new Scanner(input);
        int result = game.getInputInt("Enter 1 or 0: ", sc);
        assertEquals(1, result);
    }

    @Test
    void testGetInputIntInvalid() {
        String input = "abc\n1\n";
        Scanner sc = new Scanner(input);
        int result = game.getInputInt("Enter 1 or 0: ", sc);
        assertEquals(1, result);
    }

    @Test
    void testShuffleDeck() {
        List<Card> originalDeck = new ArrayList<>(deck.cards);
        deck.shuffleDeck();
        assertNotEquals(originalDeck, deck.cards);
    }

    @Test
    void testSwapCardsValid() {
        Card card1 = deck.cards.get(0);
        Card card2 = deck.cards.get(1);
        deck.swapCards(0, 1, deck.cards.size());
        assertEquals(card1, deck.cards.get(1));
        assertEquals(card2, deck.cards.get(0));
    }

    @Test
    void testSwapCardsInvalid() {
        List<Card> originalDeck = new ArrayList<>(deck.cards);
        deck.swapCards(-1, 53, deck.cards.size());
        assertEquals(originalDeck, deck.cards);
    }

    @Test
    void testPlayerWins() {
        player.addCardToHand(new Card(0, 0));  // Ace of Spades
        player.addCardToHand(new Card(2, 9));  // Ten of Hearts
        dealer.addCardToHand(new Card(1, 5));  // Six of Clubs

        assertTrue(player.calculatePoints() > dealer.calculatePoints());
        game.playRound(false, true);
    }

    @Test
    void testPlayerLoses() {
        player.addCardToHand(new Card(1, 11)); // Queen of Clubs
        player.addCardToHand(new Card(1, 9));  // Ten of Clubs
        dealer.addCardToHand(new Card(0, 9));  // Ten of Spades
        dealer.addCardToHand(new Card(1, 11)); // Queen of Clubs

        assertTrue(dealer.calculatePoints() > player.calculatePoints());
        game.playRound(false, true);
    }

    @Test
    void testGameDraw() {
        player.addCardToHand(new Card(1, 9)); // Ten of Clubs
        player.addCardToHand(new Card(2, 9)); // Ten of Hearts
        dealer.addCardToHand(new Card(0, 9)); // Ten of Spades
        dealer.addCardToHand(new Card(3, 9)); // Ten of Diamonds

        assertEquals(player.calculatePoints(), dealer.calculatePoints());
        game.playRound(false, true);
    }

    @Test
    void testPlayerBusts() {
        player.addCardToHand(new Card(1, 11)); // Queen of Clubs
        player.addCardToHand(new Card(2, 11)); // Queen of Hearts
        player.addCardToHand(new Card(3, 11)); // Queen of Diamonds

        assertTrue(player.calculatePoints() > 21);
        game.playRound(false, true);
    }

    @Test
    void testDealerBusts() {
        dealer.addCardToHand(new Card(1, 11)); // Queen of Clubs
        dealer.addCardToHand(new Card(2, 11)); // Queen of Hearts
        dealer.addCardToHand(new Card(3, 11)); // Queen of Diamonds

        assertTrue(dealer.calculatePoints() > 21);
        game.playRound(false, true);
    }

    @Test
    void testCardPointAceAsEleven() {
        Card ace = new Card(0, 0); // Ace of Spades
        assertEquals(11, ace.getCardPoints(false));
    }

    @Test
    void testCardPointAceAsOne() {
        Card ace = new Card(0, 0); // Ace of Spades
        assertEquals(1, ace.getCardPoints(true));
    }

    @Test
    void testCardPointKing() {
        Card king = new Card(1, 12); // King of Clubs
        assertEquals(10, king.getCardPoints(false));
    }

    @Test
    void testDisplayPlayerHand() {
        player.addCardToHand(new Card(0, 0));  // Ace of Spades
        player.addCardToHand(new Card(2, 9));  // Ten of Hearts
        player.displayHand(false, true);
        assertEquals(21, player.calculatePoints());
    }

    @Test
    void testDisplayDealerHand() {
        dealer.addCardToHand(new Card(0, 0));  // Ace of Spades
        dealer.addCardToHand(new Card(2, 9));  // Ten of Hearts
        dealer.displayHand(true, false);
    }
}
