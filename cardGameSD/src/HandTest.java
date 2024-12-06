import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    Hand hand1 = new Hand();
    Card card1 = new Card(1);
    @Test
    void checkCardAdd(){
        hand1.addCard(card1);
        assertTrue(hand1.getCards().contains(card1));
    }

    @Test
    void checkCardRemove(){
        hand1.addCard(card1);
        hand1.removeCard(0);
        assertFalse(hand1.getCards().contains(card1));
    }

}