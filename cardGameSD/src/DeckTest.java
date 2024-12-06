import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck testDeck = new Deck(1);
    Card card1 = new Card(1);
    Card card2 = new Card(2);
    Card card3 = new Card(3);
    Card card4 = new Card(4);
    Card card5 = new Card(5);

    @Test
    void testAddCard2Deck(){
        testDeck.addCard(card1);
        testDeck.addCard(card2);
        testDeck.addCard(card3);
        testDeck.addCard(card4);
        testDeck.addCard(card5);
        assertEquals(card5,testDeck.getDeck().get(0));
        assertEquals(card4,testDeck.getDeck().get(1));
        assertEquals(card3,testDeck.getDeck().get(2));
        assertEquals(card2,testDeck.getDeck().get(3));
        assertEquals(card1,testDeck.getDeck().get(4));
    }

    @Test
    void testRemoveCard(){
        testDeck.addCard(card1);
        testDeck.addCard(card2);
        testDeck.addCard(card3);
        testDeck.addCard(card4);
        testDeck.addCard(card5);
        assertEquals(card1, testDeck.removeCard());
        assertEquals(card2, testDeck.removeCard());
        assertEquals(card3, testDeck.removeCard());
        assertEquals(card4, testDeck.removeCard());
        assertEquals(card5, testDeck.removeCard());
    }


}