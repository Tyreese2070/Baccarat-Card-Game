import java.util.ArrayList;
import java.util.List;

public class BaccaratHand
{
    private List<BaccaratCard> hand = new ArrayList<>();
    public BaccaratHand(){}

    // Return hand size
    public int size()
    {
        return hand.size();
    }

    // Add card to hand
    public void add(Card card){
        hand.add(new BaccaratCard(card.getRank(), card.getSuit()));
    }

    // Value of the card
    public int value()
    {
        int total = 0;
        for (int i = 0; i < size(); i++)
        {
            total += hand.get(i).value();
        }
        return (total % 10);
    }

    // Check if they have a natural
    public boolean isNatural()
    {
        if (size() == 2 && (value() == 9 || value() == 8))
        {
            return true;
        }
        return false;
    }

    // Output the hand as a string
    public String toString()
    {
        String string = "";
        for (int i = 0; i < size(); i++)
        {
            if (i != size() - 1)
            {
                string += hand.get(i).toString() + " ";
            }
            else
            {
                string += hand.get(i).toString();
            }
        }
        return string;
    }

    public void remove()
    {
        hand.removeFirst();
    }
}