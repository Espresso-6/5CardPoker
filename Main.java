import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static int handSize = 5;
    public static boolean printDeck = false, printData = false;
    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("\n\nWelcome to the Poker Game! What do you want to do?");
            System.out.println("==================================================");
            System.out.println("1. Deal a 5 Card Hand (Runs the program on default settings and quits)");
            System.out.println("2. Start a custom game (Uses custom settings and deals as many hands as wanted)");
            System.out.println("3. Change the settings");
            System.out.println("4. Exit");
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Please input the number of your choice: ");
            String choice = myScanner.nextLine();

            if (choice.equals("1")) {
                Deal();
            } else if (choice.equals("2")) {
                System.out.println("===========\nYou can deal as many times as you want but the deck will always be reshuffled. (ie: It will not grow smaller)");
                while (true) {
                    System.out.println("\n==================================================");
                    System.out.println("1. Deal");
                    System.out.println("2. Simulate hands");
                    System.out.println("3. Back");
                    System.out.println("Please input the number of your choice: ");
                    choice = myScanner.nextLine();

                    if (choice.equals("1")) DealCustom();
                    else if (choice.equals("2")) SimulateHands();
                    else if (choice.equals("3")) break;
                    else System.out.println("Invalid input");
                }

            } else if (choice.equals("3"))
            {
                System.out.println("\n===========\nSettings");
                System.out.println("1. Change the number of cards in a hand");
                System.out.println("2. Change the print options");
                System.out.println("3. Back");
                choice = myScanner.nextLine();
                if (choice.equals("1")) changeHandSize();
                else if (choice.equals("2")) changePrint();
                else if (choice.equals("3"));
                else System.out.println("Invalid input");
            } else if (choice.equals("4")) {
                System.exit(0);
            } else {
                System.out.println("Invalid input, please try again");
                main(args);
            }
        }
    }

    private static void changePrint()
    {
        System.out.println("===========\nPrint Options");
        System.out.println("1. Print the deck every time it is created and shuffled ("+printDeck+")");
        System.out.println("2. Print the pairs, sequential cards, and frequency of suits ("+printData+")");
        System.out.println("3. Back");
        Scanner myScanner = new Scanner(System.in);
        String choice = myScanner.nextLine();
        if (choice.equals("1"))
        {
            System.out.println("changing deck printing option to "+!printDeck);
            printDeck = !printDeck;
        }
        else if (choice.equals("2"))
        {
            System.out.println("changing pairs, sequential and frequency printing option to "+!printData);
            printData = !printData;
        }
        else if (choice.equals("3")) return;
        else System.out.println("Invalid input");
        System.out.println("Settings will only take effect in custom games...");
    }

    private static void changeHandSize()
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Pick a new hand size that is between 1 and 52");
        System.out.println("Current hand size: " + handSize);
        String choice = myScanner.nextLine();
        try
        {
            int newHandSize = Integer.parseInt(choice);
            if (newHandSize > 0 && newHandSize < 53)
            {
                handSize = newHandSize;
                System.out.println("Hand size changed to: " + handSize);
            } else System.out.println("Please pick a suitable number.");
        } catch (Exception e)
        {
            System.out.println("Invalid input");
        }
        return;
    }

    private static void SimulateHands()
    {
        System.out.println("==================================================");
        System.out.println("Pick a hand to simulate:");
        System.out.println("1. Straight Flush");
        System.out.println("2. Four of a Kind");
        System.out.println("3. Full House");
        System.out.println("4. Flush");
        System.out.println("5. Straight");
        System.out.println("6. Three of a Kind");
        System.out.println("7. Two Pair");
        System.out.println("8. One Pair");
        System.out.println("9. High Card");
        System.out.println("10. Aces High Straight");
        System.out.println("11. Aces High Straight Flush");
        System.out.println("12. Back");

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please input the number of your choice: ");
        String choice = myScanner.nextLine();

        Deck deck = new Deck();
        deck.shuffle();
        Hand myHand = new Hand();

        //could have maybe done a switch statement, but it doesn't work with .equals()
        if (choice.equals("1")) myHand.testStraightFlush();
        else if (choice.equals("2")) myHand.testFourOfAKind();
        else if (choice.equals("3")) myHand.testFullHouse();
        else if (choice.equals("4")) myHand.testFlush();
        else if (choice.equals("5")) myHand.testStraight();
        else if (choice.equals("6")) myHand.testThreeOfAKind();
        else if (choice.equals("7")) myHand.testTwoPair();
        else if (choice.equals("8")) myHand.testOnePair();
        else if (choice.equals("9")) myHand.testHighCard();
        else if (choice.equals("10")) myHand.testAcesHigh();
        else if (choice.equals("11")) myHand.testAcesHighFlush();
        else if (choice.equals("12")) return;
        else
        {
            System.out.println("Invalid input, please try again");
            SimulateHands();
        }
        myHand.printHand();
        Scoring score = new Scoring(myHand.getHand());
        score.checkHand();
        if (printData) score.printData();
    }

    public static void Deal()
    {
        Deck deck = new Deck();
        deck.shuffle();
        Hand myHand = new Hand(deck.deal(5));
        myHand.printHand();
        Scoring score = new Scoring(myHand.getHand());
        score.checkHand();
    }

    public static void DealCustom()
    {
        Deck deck = new Deck();
        if (printDeck) deck.printDeck();
        deck.shuffle();
        if (printDeck) deck.printDeck();
        Hand myHand = new Hand(deck.deal(handSize));
        myHand.printHand();
        Scoring score = new Scoring(myHand.getHand());
        score.checkHand();
        if (printData) score.printData();
    }
}
