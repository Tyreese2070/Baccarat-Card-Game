import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Shoe
{
    private int numOfCards;
    private List<BaccaratCard> shoe = new ArrayList<>();
    public Shoe(int decks)
    {
        initializeDecks(decks);
    }

    // Populate the shoe based on the given amount of decks
    private void initializeDecks(int decks)
    {
        if (decks == 6 || decks == 8)
        {
            numOfCards = decks * 52;
            Card.Rank[] ranks = Card.Rank.values();
            Card.Suit[] suits = Card.Suit.values();
            for (int i = 0; i < decks; i++)
            {
                for (Card.Suit suit: suits)
                {
                    for (Card.Rank rank: ranks)
                    {
                        shoe.add(new BaccaratCard(rank, suit));
                    }
                }
            }
        }
        else
        {
            throw new CardException("Invalid shoe size");
        }
    }

    // Return the shoe size
    public int size()
    {
        return shoe.size();
    }

    // Randomise the order of cards in the shoe
    public void shuffle(){
        Collections.shuffle(shoe);
    }

    // Deal then remove a card from the shoe
    public Card deal()
    {
        if (shoe.isEmpty())
        {
            throw new CardException("Dealing from empty shoe");
        }
        Card card = shoe.get(0);
        shoe.removeFirst();
        return card;
    }
}