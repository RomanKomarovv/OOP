package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackGameTest {
    Player player;
    Player dealer;
    Card aceOfSpades;
    Card tenOfHearts;
    Card queenOfClubs;
    Card nineOfHearts;

    @BeforeEach
    void setUp() {
        player = new Player();
        dealer = new Player();
        aceOfSpades = new Card(0, 0);
        tenOfHearts = new Card(2, 9);
        queenOfClubs = new Card(1, 11);
        nineOfHearts = new Card(2, 8);
    }

    @Test
    void testAddCardToHand() {
        player.addCardToHand(aceOfSpades);
        assertEquals(1, player.hand.size());
        assertEquals(aceOfSpades, player.hand.get(0));
    }

    @Test
    void testCalculatePointsWithAce() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(tenOfHearts);
        assertEquals(21, player.calculatePoints());
    }

    @Test
    void testCalculatePointsWithMultipleAces() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(nineOfHearts);
        player.addCardToHand(aceOfSpades);
        assertEquals(21, player.calculatePoints());
    }

    @Test
    void testCalculatePointsOver21() {
        player.addCardToHand(queenOfClubs);
        player.addCardToHand(tenOfHearts);
        player.addCardToHand(queenOfClubs);
        assertEquals(30, player.calculatePoints());
    }

    @Test
    void testDisplayHand() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(tenOfHearts);
        player.displayHand(false, true);
    }

    @Test
    void testGameOutcomePlayerWins() {
        player.addCardToHand(tenOfHearts);
        player.addCardToHand(aceOfSpades);
        assertTrue(player.calculatePoints() > dealer.calculatePoints());
    }

    @Test
    void testGameOutcomeDealerWins() {
        dealer.addCardToHand(tenOfHearts);
        dealer.addCardToHand(aceOfSpades);
        assertTrue(dealer.calculatePoints() > player.calculatePoints());
    }

    @Test
    void testGameOutcomeDraw() {
        player.addCardToHand(tenOfHearts);
        player.addCardToHand(tenOfHearts);
        dealer.addCardToHand(tenOfHearts);
        dealer.addCardToHand(tenOfHearts);
        assertEquals(player.calculatePoints(), dealer.calculatePoints());
    }

    @Test
    void testCalculatePointsAceAsOne() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(tenOfHearts);
        player.addCardToHand(queenOfClubs);
        assertEquals(21, player.calculatePoints());
    }

    @Test
    void testCalculatePointsWithNoCards() {
        assertEquals(0, player.calculatePoints());
    }

    @Test
    void testCalculatePointsMaxWithoutAces() {
        player.addCardToHand(queenOfClubs);
        player.addCardToHand(tenOfHearts);
        player.addCardToHand(nineOfHearts);
        assertEquals(29, player.calculatePoints());
    }

    @Test
    void testDrawCard() {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffleDeck();
        player.addCardToHand(deck.cards.get(deck.cards.size() - 1));
        assertEquals(1, player.hand.size());
    }

    @Test
    void testDealerHandHiddenCard() {
        dealer.addCardToHand(tenOfHearts);
        dealer.addCardToHand(aceOfSpades);
        dealer.displayHand(true, false);
    }

    @Test
    void testAddCardToEmptyHand() {
        assertEquals(0, player.hand.size());
        player.addCardToHand(aceOfSpades);
        assertEquals(1, player.hand.size());
    }

    @Test
    void testGameOutcomeDealerWinsWithHighPoints() {
        dealer.addCardToHand(queenOfClubs);
        dealer.addCardToHand(queenOfClubs);

        player.addCardToHand(nineOfHearts);
        player.addCardToHand(tenOfHearts);

        assertTrue(dealer.calculatePoints() > player.calculatePoints());
    }

    @Test
    void testCalculatePointsWithMultipleAcesDifferentValues() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(nineOfHearts);
        assertEquals(21, player.calculatePoints());
    }

    @Test
    void testPlayerBusts() {
        player.addCardToHand(queenOfClubs);
        player.addCardToHand(queenOfClubs);
        player.addCardToHand(nineOfHearts);
        assertTrue(player.calculatePoints() > 21);
    }

    @Test
    void testGameOutcomeDrawWithAces() {
        player.addCardToHand(aceOfSpades);
        player.addCardToHand(tenOfHearts);

        dealer.addCardToHand(aceOfSpades);
        dealer.addCardToHand(tenOfHearts);

        assertEquals(player.calculatePoints(), dealer.calculatePoints());
    }
}
