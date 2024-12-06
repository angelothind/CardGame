import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Card prefered1 = new Card(1);
    Card prefered2 = new Card(1);
    Card card3 = new Card(3);
    Card card4 = new Card(4);
    Card card5 = new Card(5);
    Card card6 = new Card(6);
    Card card7 = new Card(7);
    Card card8 = new Card(8);
    Card card9 = new Card(9);
    Card card10 = new Card(10);
    Card card11 = new Card(11);
    Card card12 = new Card(12);
    Card card13 = new Card(13);
    Card card14 = new Card(14);
    Card card15 = new Card(15);

    Deck drawDeck = new Deck(1);
    Deck discardDeck = new Deck(2);

    Hand playerHand = new Hand();

    Player testPlayer = new Player(1,drawDeck,discardDeck,playerHand);

    @Test
    void testPlayerAdd2Hand(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(card3);
        testPlayer.getHand().addCard(card4);
        assertEquals(4, testPlayer.getHand().getCards());
    }

    @Test
    void testPlayerDraw(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(card3);
        testPlayer.getHand().addCard(card4);
        testPlayer.draw(card5);
        assertTrue(testPlayer.getHand().getCards().contains(card5));
    }

    @Test
    void testPlayerWon(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        assertTrue(testPlayer.hasWon());
    }

    @Test
    void testPlayerNotWon(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(card7);
        assertFalse(testPlayer.hasWon());
    }

    @Test
    void testPlayerDiscard(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(card7);
        testPlayer.discard();
        assertFalse(testPlayer.getHand().getCards().contains(card7));
    }

    @Test
    void testPlayerDiscardNonprefered(){
        testPlayer.getHand().addCard(prefered1);
        testPlayer.getHand().addCard(card4);
        testPlayer.getHand().addCard(prefered2);
        testPlayer.getHand().addCard(card7);
        testPlayer.discard();
        assertFalse(testPlayer.discard().getValue() != prefered1.getValue());
    }





}