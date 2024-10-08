package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main game of Blackjack
 */
public class BlackjackGame {
    Player player;
    Player dealer;

    DeckOfCards deck;

    int playerScore;
    int dealerScore;

    int totalRounds;

    Scanner scanner = new Scanner(System.in);

    /**
     * Initializws the Blackjack game instance, creating players and shuffling the deck.
     */
    public BlackjackGame() {
        player = new Player();
        dealer = new Player();

        deck = new DeckOfCards();
        deck.shuffleDeck();

        playerScore = 0;
        dealerScore = 0;

        totalRounds = 1;
    }

    /**
     * Draws a card from the deck and adds it to the participant's hand.
     *
     * @param participant The player or dealer drawing the card.
     * @param deck       The deck of cards from which to draw.
     * @return The drawn card, or null if the deck is empty.
     */
    public Card drawCard(Player participant, DeckOfCards deck) {
        Card currentCard;
        try {
            currentCard = deck.cards.remove(deck.cards.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Deck is empty.");
            return null;
        }
        participant.addCardToHand(currentCard);
        return currentCard;
    }

    /**
     * Gets an integer input from the user with a given prompt.
     *
     * @param promptText The text to display for input prompt.
     * @param sc        The scanner used to read user input.
     * @return The integer input from the user.
     */
    public int getInputInt(String promptText, Scanner sc) {
        System.out.print(promptText);
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. " + promptText);
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * Plays a round of Blackjack.
     *
     * @param testMode Indicates if the game is in test mode.
     * @param autoPlay Indicates if the game should automatically play.
     */
    public void playRound(boolean testMode, boolean autoPlay) {
        if (totalRounds <= 1) {
            System.out.println("Welcome to Blackjack!");
        } else {
            System.out.println("\n");
        }
        System.out.println("Round " + totalRounds);

        // Инициализируем игроков только если не в тестовом режиме и не в автоплей
        if (!testMode && !autoPlay) {
            // Удаляем инициализацию игроков здесь,
            // поскольку они уже инициализированы в конструкторе

            if (deck.cards.size() < 52 / 2) {
                deck = new DeckOfCards();
                deck.shuffleDeck();
            }

            drawCard(player, deck);
            drawCard(player, deck);
            drawCard(dealer, deck);
        }

        System.out.println("Dealer's cards: ");
        player.displayHand(false, true);
        dealer.displayHand(true, false);

        System.out.println("Your turn:");
        System.out.println("----------");

        while (true) {
            if (player.calculatePoints() >= 21) {
                break;
            }

            System.out.print("Press '1' to draw a card, '0' to stand: ");

            int input;
            if (testMode) {
                input = 0; // В тестовом режиме игрок сразу заканчивает ход
            } else {
                input = getInputInt("Press '1' to draw a card, '0' to stand: ", scanner);
            }

            if (input != 1) {
                break;
            } else {
                Card newCard = drawCard(player, deck);
                System.out.print("You drew: ");
                int currentPoints = player.calculatePoints() + newCard.getCardPoints(false);
                newCard.printCardDetails(currentPoints > 21);
                System.out.println();
            }

            player.displayHand(false, true);
            dealer.displayHand(true, false);
        }

        if (player.calculatePoints() > 21) {
            dealerScore++;
            System.out.print("You lost! ");
        } else if (player.calculatePoints() == 21) {
            playerScore++;
            System.out.print("You won! ");
        } else if (player.calculatePoints() < 21) {
            System.out.println("\nDealer's turn:");
            System.out.println("----------");

            // Дилер берет карты только если не в тестовом режиме
            if (!testMode) {
                boolean first = true;
                while (first || dealer.calculatePoints() < 17) {
                    Card dealerCard = drawCard(dealer, deck);
                    boolean overLimit = ((player.calculatePoints()
                            + dealerCard.getCardPoints(false)) > 21);

                    if (first) {
                        System.out.print("Dealer reveals the hidden card ");
                    } else {
                        System.out.print("Dealer draws: ");
                    }

                    dealerCard.printCardDetails(overLimit);
                    System.out.println();

                    player.displayHand(false, true);
                    dealer.displayHand(false, false);

                    first = false;
                }
            }

            // Условия для определения победителя
            if (dealer.calculatePoints() > 21
                    || (player.calculatePoints() > dealer.calculatePoints())) {
                playerScore++;
                System.out.print("You won! ");
            } else if (player.calculatePoints() == dealer.calculatePoints()) {
                playerScore++;
                dealerScore++;
                System.out.print("It's a draw! ");
            } else if (player.calculatePoints() < dealer.calculatePoints()) {
                dealerScore++;
                System.out.print("You lost! ");
            }
        }

        System.out.println("\nScore player/dealer: " + playerScore + "/" + dealerScore);
        totalRounds++;
    }

    /**
     * Starts the game loop, allowing the player to play multiple rounds.
     *
     * @param testMode Indicates if the game is in test mode.
     * @param autoPlay Indicates if the game should automatically play.
     */
    public void startGame(boolean testMode, boolean autoPlay) {
        while (true) {
            playRound(testMode, autoPlay);

            System.out.print("Play again? Press '1' to continue, '0' to stop: ");
            int input;

            if (testMode) {
                input = 0;
            } else {
                input = getInputInt("\nPress '1' to continue, '0' to stop: ", scanner);
            }

            if (input != 1) {
                break;
            }
        }
    }

    /**
     * The entry point of the Blackjack game.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        BlackjackGame blackjack = new BlackjackGame();
        blackjack.startGame(false, false);
    }
}

/**
 * Represents a deck of cards used in the Blackjack game.
 */
class DeckOfCards {
    List<Card> cards;

    public DeckOfCards() {
        cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            Card card = new Card(i / 13, i % 13);
            cards.add(card);
        }
    }

    public void swapCards(int i, int j, int size) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            return;
        }

        Card temp = this.cards.get(i);
        this.cards.set(i, this.cards.get(j));
        this.cards.set(j, temp);
    }

    Random random = new Random();

    public void shuffleDeck() {
        for (int i = 0; i < 52; i++) {
            int index = random.nextInt(cards.size() - i);
            swapCards(index, cards.size() - i, cards.size());
        }
    }
}

class Card {
    enum Suit {
        Spades, Clubs, Diamonds, Hearts
    }

    enum Rank {
        Ace, Two, Three, Four, Five, Six, Seven,
        Eight, Nine, Ten, Jack, Queen, King
    }

    Suit suit;
    Rank rank;

    public Card(int suit, int rank) throws IllegalArgumentException {
        this.suit = Suit.values()[0];
        this.rank = Rank.values()[0];

        if (0 <= suit && suit < 4) {
            this.suit = Suit.values()[suit];
        } else {
            throw new IllegalArgumentException("Invalid suit value");
        }

        if (0 <= rank && rank < 13) {
            this.rank = Rank.values()[rank];
        } else {
            throw new IllegalArgumentException("Invalid rank value");
        }
    }

    public int getCardPoints(boolean overLimit) {
        int rankValue = this.rank.ordinal() + 1;

        if (rankValue == 1) {
            return overLimit ? 1 : 11;
        } else if (rankValue >= 2 && rankValue <= 10) {
            return rankValue;
        } else {
            return 10;
        }
    }

    public void printCardDetails(boolean overLimit) {
        System.out.print(this.rank + " of " + this.suit
                + " (" + this.getCardPoints(overLimit) + ")");
    }
}

class Player {
    List<Card> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void displayHand(boolean hideCard, boolean isPlayer) {
        if (hand.size() <= 0) {
            System.out.println("No cards in hand");
        } else {
            if (isPlayer) {
                System.out.print("\t Your cards: ");
            } else {
                System.out.print("\t Dealer's cards: ");
            }
            System.out.print("[");

            boolean overLimit = false;
            int totalPoints = 0;
            for (Card card : hand) {
                totalPoints += card.getCardPoints(false);
            }
            if (totalPoints > 21) {
                overLimit = true;
            }

            if (!hideCard) {
                for (int i = 0; i < hand.size(); i++) {
                    hand.get(i).printCardDetails(overLimit);

                    if (i < hand.size() - 1) {
                        System.out.print(", ");
                    }
                }
            } else if (!hand.isEmpty()) {
                hand.get(0).printCardDetails(overLimit);
                System.out.print(", <hidden card>");
            }

            System.out.print("]");

            if (!hideCard) {
                System.out.println(" => " + this.calculatePoints());
            } else {
                System.out.println();
            }

            if (!isPlayer) {
                System.out.print("\n");
            }
        }
    }


    public int calculatePoints() {
        int points = 0;
        int aceCount = 0;

        for (Card card : hand) {
            int cardPoints = card.getCardPoints(false);
            if (cardPoints == 11) {
                aceCount++;
            }
            points += cardPoints;
        }

        while (points > 21 && aceCount > 0) {
            points -= 10;
            aceCount--;
        }

        return points;
    }
}
