import java.util.Objects;
import java.util.Scanner;

public class Baccarat
{
    static Player banker = new Player();
    static Player user = new Player();

    static Shoe deck = new Shoe(6);

    static int tieCount = 0;
    static int playerWins = 0;
    static int bankerWins = 0;

    static boolean nextRound = true;

    public static void main(String[] args)
    {
        boolean interactive = false;
        int roundNum = 1;

        // Non-Interactive mode
        if (args.length == 0) {
            interactive = false;
        }

        // Interactive mode
        else if (Objects.equals(args[0], "-i") || Objects.equals(args[0], "--interactive")) {
            interactive = true;
        }

        // Main gameplay loop
        while (nextRound)
        {
            System.out.println(" ");
            deck.shuffle();
            round(roundNum, interactive);
            user = new Player();
            banker = new Player();
            roundNum += 1;
        }
    }

    static void round(int roundNum, boolean interactive)
    {
        deal();
        System.out.println("Round " + roundNum);
        System.out.println("Player: " + user.toString() + " = " + user.value());
        System.out.println("Banker: " + banker.toString() + " = " + banker.value());

        // Dealing third card logic
        // Player third card
        if (user.value() >= 0 || user.value() <= 5)
        {
            Card playerThird = deck.deal();
            user.add(playerThird);
            System.out.println("Dealing third card to Player");

            // Banker third card - Bankers Rule
            if (banker.value() < 2)
            {
                banker.add(deck.deal());
            }
            else if(banker.value() == 3)
            {
                if (playerThird.value() != 8)
                {
                    banker.add(deck.deal());
                }
            }
            else if(banker.value() == 4)
            {
                if (playerThird.value() == 2 || playerThird.value() ==  3 || playerThird.value() == 4 || playerThird.value() == 5 || playerThird.value() == 6 || playerThird.value() == 7)
                {
                    banker.add(deck.deal());
                }
            }
            else if(banker.value() == 5)
            {
                if(playerThird.value() == 4 || playerThird.value() == 5 || playerThird.value() == 6 || playerThird.value() == 7)
                {
                    banker.add(deck.deal());
                }
            }

            else if(banker.value() == 6)
            {
                if(playerThird.value() == 6 || playerThird.value() == 7)
                {
                    banker.add(deck.deal());
                }
            }

            if(banker.size() == 3)
            {
                System.out.println("Dealing third card to Banker");
            }

            System.out.println("Player: " + user.toString() + " = " + user.value());
            System.out.println("Banker: " + banker.toString() + " = " + banker.value());
        }

        // Adjusting total wins / ties
        if(getWinner(user, banker) == 1)
        {
            System.out.println("Player is the winner");
            playerWins += 1;
        }
        else if(getWinner(user, banker) == 2)
        {
            System.out.println("Banker is the winner");
            bankerWins += 1;
        }
        else if(getWinner(user, banker) == 3)
        {
            System.out.println("Tie");
            tieCount += 1;
        }

        //
        if (deck.size() > 6)
        {
            if (interactive)
            {
                while(true) {
                    Scanner roundScanner = new Scanner(System.in);
                    System.out.println("Another round? (y/n): ");
                    String option = roundScanner.nextLine();

                    if (option.equalsIgnoreCase("y"))
                    {
                        nextRound = true;
                        break;
                    }
                    else if (option.equalsIgnoreCase("n"))
                    {
                        nextRound = false;
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid input");
                    }
                }
            }
            else nextRound = true;
        }
        if(deck.size() < 6)
        {
            nextRound = false;
        }

        if (nextRound == false)
        {
            System.out.println(" ");
            System.out.println(roundNum + " rounds played");
            System.out.println(playerWins + " player wins");
            System.out.println(bankerWins + " banker wins");
            System.out.println(tieCount + " ties");
        }


    }

    // Scoring. 1 = player1 win. 2 = Player 2 win. 3 = Tie
    static int getWinner(Player player1, Player player2)
    {
        if (player1.isNatural() && player2.isNatural())
        {
            System.out.println("Player and banker has a natural");
            if (player1.value() > player2.value())
            {
                System.out.println("Player has a higher natural");
                return 1;
            }
            else if (player2.value() > player1.value())
            {
                System.out.println("Banker has a higher natural");
                return 2;
            }
            return 3;

        }

        else if(player1.isNatural())
        {
            System.out.println("Player has a natural");
            return 1;
        }
        else if(player2.isNatural())
        {
            System.out.println("Banker has a natural");
            return 2;
        }

        if (player1.value() > player2.value())
        {
            return 1;
        }
        else if (player2.value() > player1.value())
        {
            return 2;
        }
        return 3;
    }

    // Deal start of round cards
    static void deal(){
        user.add(deck.deal());
        user.add(deck.deal());

        banker.add(deck.deal());
        banker.add(deck.deal());
    }
}
