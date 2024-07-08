public class Player extends BaccaratHand {
    private int score;

    public Player() {}

    public void increaseScore()
    {
        score+=1;
    }

    public int getScore()
    {
        return score;
    }
}
