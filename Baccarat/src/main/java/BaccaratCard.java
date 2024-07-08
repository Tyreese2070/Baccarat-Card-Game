public class BaccaratCard extends Card {
    private Card card;
    private char rankSymbol;
    private char suitSymbol;

    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
        card = new Card(r, s);
        rankSymbol = card.getRank().getSymbol();
        suitSymbol = card.getSuit().getSymbol();
    }

    public Rank getRank() {
        return card.getRank();
    }

    public Suit getSuit() {
        return card.getSuit();
    }

    // Return string value of a card
    public String toString() {
        return Character.toString(rankSymbol) + Character.toString(suitSymbol);
    }

    // Return if a card is equal to another
    public boolean equals(Object other) {
        return other.equals(card);
    }

    public int compareTo(Card other) {
        return other.compareTo(card);
    }

    // Return the value of a card
    public int value() {
        int value = 0;
        if (getRank() == Rank.JACK || getRank() == Rank.QUEEN || getRank() == Rank.KING || getRank() == Rank.TEN) {
            return 0;
        }

        else if (getRank() == Rank.ACE) {
            return 1;
        }

        else {
            /*
            value = (int) rankSymbol;
            return value
             */

            return Character.getNumericValue(rankSymbol);
            }
        }
}
