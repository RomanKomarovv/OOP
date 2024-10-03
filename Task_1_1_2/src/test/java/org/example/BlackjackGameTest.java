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
}
