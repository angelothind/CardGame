import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hand implements CardCollections{
    private ArrayList<Card> cardList = new ArrayList<>();
    @Override
    public void addCard(Card card) {
        cardList.add(card);
    }

    @Override
    public Card removeCard() {
        Card discardCard = cardList.getLast();
        int length = cardList.size();
        cardList.remove(length - 1);
        return discardCard;
    }

    public Card removeCard(int index){
        Card card = cardList.get(index);
        cardList.remove(index);
        return card;
    }

    public ArrayList<Card> getCards() {
        return cardList;
    }


}
