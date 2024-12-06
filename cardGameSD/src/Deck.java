import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deck implements CardCollections {
    ArrayList<Card> deck = new ArrayList<>();
    int deckId;
    Deck(int deckId){
        this.deckId = deckId;
    }
    @Override
    public void addCard(Card card) {
        deck.add(card);
    }

    @Override
    public Card removeCard() {
        Card drawnCard = deck.getLast();
        int length = deck.size();
        deck.remove(length - 1);
        return drawnCard;
    }

    ArrayList<Card> returnAll(){
        return deck;
    }

    public int getDeckId() {
        return deckId;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
