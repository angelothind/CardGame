import java.util.ArrayList;

public class Pack implements CardCollections{
    ArrayList<Card> pack = new ArrayList<>();
    @Override
    public void addCard(Card card) {
        pack.add(card);
    }

    @Override
    public Card removeCard() {
        Card dealtCard = pack.getLast();
        pack.removeLast();
        return dealtCard;
    }

    public ArrayList<Card> getPack() {
        return pack;
    }
}
