import java.util.ArrayList;
import java.util.Random;
public class Player {

    Hand hand;
    private int preference;
    private Deck drawDeck;
    private Deck discardDeck;

    Player(int preference, Deck drawDeck, Deck discardDeck,Hand hand){
        this.discardDeck = discardDeck;
        this.preference = preference;
        this.drawDeck = drawDeck;
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public Card discard() {
        ArrayList<Integer> disCards = new ArrayList<>();
        ArrayList<Card> cards = this.hand.getCards();
        for (Card card : cards){
            if (card.getValue() != getPreference()) {
                disCards.add(cards.indexOf(card));
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(disCards.size() - 1);
        Card discarded = this.hand.removeCard(disCards.get(randomIndex));
        return discarded;
    }

    public void draw(Card drawnCard){
        this.hand.addCard(drawnCard);
    }

    public Deck getDiscardDeck() {
        return discardDeck;
    }

    public int getPreference() {
        return preference;
    }

    public Deck getDrawDeck() {
        return drawDeck;
    }

    public boolean hasWon(){
        Hand currentHand = this.getHand();
        boolean won = true;
        for (Card card: currentHand.getCards()){
            if( card.getValue() != getPreference()){
                won = false;
            }
            if(won == false){
                break;
            }
        }
        return won;
    }

    public void setDiscardDeck(Deck discardDeck) {
        this.discardDeck = discardDeck;
    }

    public void setDrawDeck(Deck drawDeck) {
        this.drawDeck = drawDeck;
    }

}
