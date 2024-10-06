package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


public class BlackjackGameTest {

    BlackjackGame game;
    DeckOfCards deck;
    Player player;
    Player dealer;

    @BeforeEach
    public void setup() {
        game = new BlackjackGame();
        deck = new DeckOfCards();
        player = new Player();
        dealer = new Player();
    }

    // --- Тесты для Player ---

    @Test
    public void testPlayerPointsWithMultipleAces() {
        // Проверяем корректный подсчет очков при наличии нескольких тузов
        player.addCardToHand(new Card(0, 0));  // Ace
        player.addCardToHand(new Card(0, 0));  // Ace
        player.addCardToHand(new Card(0, 0));  // Ace
        assertEquals(13, player.calculatePoints());  // Один туз равен 11, остальные - по 1
    }

    @Test
    public void testPlayerPointsWithAceAndFaceCard() {
        // Проверка комбинации туза и лицевой карты (например, короля)
        player.addCardToHand(new Card(0, 0));  // Ace
        player.addCardToHand(new Card(0, 12)); // King
        assertEquals(21, player.calculatePoints());  // Ace = 11, King = 10
    }

    @Test
    public void testPlayerPointsWithAceOver21() {
        // Проверка, что туз считается как 1, если сумма больше 21
        player.addCardToHand(new Card(0, 0));  // Ace
        player.addCardToHand(new Card(0, 12)); // King
        player.addCardToHand(new Card(0, 10)); // Ten
        assertEquals(21, player.calculatePoints());  // Ace = 1, King = 10, Ten = 10
    }

    @Test
    public void testPlayerPointsWithTwoFaceCards() {
        // Проверка, что две лицевые карты дают 20 очков
        player.addCardToHand(new Card(0, 12));  // King
        player.addCardToHand(new Card(0, 11));  // Queen
        assertEquals(20, player.calculatePoints());
    }

    @Test
    public void testPlayerBustCondition() {
        // Проверка, что игрок набирает больше 21
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 10));  // Ten
        assertTrue(player.calculatePoints() > 21);
    }

    @Test
    public void testPlayerBlackjackCondition() {
        // Проверка, что игрок получает 21 очко
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 11));  // Queen
        player.addCardToHand(new Card(0, 0));   // Ace
        assertEquals(21, player.calculatePoints());
    }

    // --- Тесты для BlackjackGame ---

    @Test
    public void testPlayerWinsWithBlackjack() {
        // Проверка, что игрок выигрывает с Blackjack
        player.addCardToHand(new Card(0, 0));  // Ace
        player.addCardToHand(new Card(0, 12)); // King

        dealer.addCardToHand(new Card(0, 10)); // Ten
        dealer.addCardToHand(new Card(0, 8));  // Eight

        game.player = player;
        game.dealer = dealer;

        game.playRound(true, true);  // Тестовый режим
        assertEquals(1, game.playerScore);
        assertEquals(0, game.dealerScore);
    }

    @Test
    public void testDealerWinsWithHigherPoints() {
        // Проверка, что дилер выигрывает с большим количеством очков
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 8));   // Nine

        dealer.addCardToHand(new Card(0, 10));  // Ten
        dealer.addCardToHand(new Card(0, 11));  // King

        game.player = player;
        game.dealer = dealer;

        game.playRound(true, true);  // Тестовый режим
        assertEquals(0, game.playerScore);
        assertEquals(1, game.dealerScore);
    }

    @Test
    public void testGetInputIntWithInvalidThenValidInput() {
        // Проверка ввода с некорректными и корректными значениями
        assertEquals(1, game.getInputInt("Enter 1: ", new Scanner("a\n1")));
    }

    @Test
    public void testDealerBustsAndPlayerWins() {
        // Проверка, что дилер "перебирает" и игрок выигрывает
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 9));   // Nine

        dealer.addCardToHand(new Card(0, 10));  // Ten
        dealer.addCardToHand(new Card(0, 10));  // Ten
        dealer.addCardToHand(new Card(0, 5));   // Five

        game.player = player;
        game.dealer = dealer;

        game.playRound(true, true);  // Тестовый режим
        assertEquals(1, game.playerScore);
        assertEquals(0, game.dealerScore);
    }

    @Test
    public void testPlayerAndDealerTie() {
        // Игрок и дилер получают одинаковые карты
        player.addCardToHand(new Card(0, 9));  // Ten
        player.addCardToHand(new Card(0, 9));  // Ten

        dealer.addCardToHand(new Card(0, 9));  // Ten
        dealer.addCardToHand(new Card(0, 9));  // Ten

        game.player = player;
        game.dealer = dealer;

        // Запускаем тестовую партию
        game.playRound(true, true);  // Тестовый режим без взаимодействия

        // Проверяем, что у игрока и дилера равное количество очков
        assertEquals(1, game.playerScore);
        assertEquals(1, game.dealerScore);  // Ничья
    }

    @Test
    public void testPlayerBustsWithHighCards() {
        // Игрок перебирает, получая слишком много очков
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 10));  // Ten (перебор)

        dealer.addCardToHand(new Card(0, 10)); // Ten
        dealer.addCardToHand(new Card(0, 11)); // Ace

        game.player = player;
        game.dealer = dealer;

        game.playRound(true, true);  // Тестовый режим
        assertEquals(0, game.playerScore);  // Игрок не получает очков
        assertEquals(1, game.dealerScore);  // Дилер выигрывает
    }

    @Test
    public void testPlayerWinsWithHigherPoints() {
        // Игрок получает больше очков, чем дилер
        player.addCardToHand(new Card(0, 10));  // Ten
        player.addCardToHand(new Card(0, 9));   // Nine

        dealer.addCardToHand(new Card(0, 10));  // Ten
        dealer.addCardToHand(new Card(0, 8));   // Eight

        game.player = player;
        game.dealer = dealer;

        game.playRound(true, true);  // Тестовый режим
        assertEquals(1, game.playerScore);  // Игрок выигрывает
        assertEquals(0, game.dealerScore);  // Дилер не получает очков
    }

    @Test
    void testCardPointsWhenOverLimit() {
        Card aceCard = new Card(0, 0); // Туз

        // Проверяем, что при переборе туз возвращает 1
        assertEquals(1, aceCard.getCardPoints(true));
    }

    @Test
    void testInvalidSuit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card(4, 10); // Некорректная масть
        });
        assertEquals("Invalid suit value", exception.getMessage());
    }

    @Test
    void testInvalidRank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card(0, 13); // Некорректный ранг
        });
        assertEquals("Invalid rank value", exception.getMessage());
    }

    @Test
    public void testEmptyDeck() {
        // Проверяем, что происходит, когда колода пуста
        deck.cards.clear(); // Удаляем все карты из колоды

        Card drawnCard = game.drawCard(player, deck);
        assertNull(drawnCard, "Expected null when drawing from an empty deck");
    }
}
