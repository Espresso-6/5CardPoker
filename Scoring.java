import java.util.ArrayList;

public class Scoring
{
    private ArrayList<Card> hand;
    private ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>(); //checks for pairs/3ok/4ok
    private int sequentialCards = 0; //checks for flushes
    private int frequency[] = new int[]{0,0,0,0}; //also checks for flushes

    public Scoring(ArrayList<Card> hand)
    {
        this.hand = hand;
    }

    //the purpose of this method is to determine obvious hands as fast as possible as opposed to checking
    //individually and running through the same arraylist multiple times
    public void checkHand()
    {
        boolean exists;
        //the hand is sorted in order of value, this makes things easier to work through as pairs are next to each other
        for (int i = 0; i < hand.size(); i++)
        {
            exists = false;
            //check for existing pairs
            for (int j = 0; j < pairs.size(); j++)
            {
                if (hand.get(i).getValue() == pairs.get(j).get(0))
                {
                    Integer temp = pairs.get(j).get(1);
                    temp++;
                    pairs.get(j).set(1, temp);
                    exists = true;
                }
            }
            //make sure the array does not go out of bounds
            //-1 because I am checking one position ahead
            if (i < hand.size()-1)
            {
                //check for new pairs
                if (!exists && hand.get(i).getValue() == hand.get(i+1).getValue())
                {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(hand.get(i).getValue());
                    temp.add(1); //this is 1 because the next card will be added in the next iteration of the i loop
                    pairs.add(temp);
                }

                //check for sequential cards
                if (hand.get(i).getValue()+1 == hand.get(i+1).getValue()) sequentialCards++;
                if (sequentialCards == 1) sequentialCards++; //there can never be 1 sequential card
            }
            //check frequency of suits
            //S,C,H,D
            if (hand.get(i).getSuit().equals("S")) frequency[0]++;
            else if (hand.get(i).getSuit().equals("C")) frequency[1]++;
            else if (hand.get(i).getSuit().equals("H")) frequency[2]++;
            else frequency[3]++;
        }
        //printData();
        fiveCardDrawRules();
    }

    private void fiveCardDrawRules()
    {
        //the idea for this method is to check all the rules that can be found in a standard 5 card poker game
        //the rules are checked in order of highest score
        //if someone wants to code different rules, they could create a function for each rule and then another function
        //that calls them in whichever order they would like.
        //they could also add rules to this function, for example a royal flush
        //I feel that this is very modular and easy to change
        if (straightFlush()) System.out.println("You have a straight flush!");
        else if (fourOfAKind()) System.out.println("You have a four of a kind!");
        else if (fullHouse()) System.out.println("You have a full house!");
        else if (flush()) System.out.println("You have a flush!");
        else if (straight()) System.out.println("You have a straight!");
        else if (threeOfAKind()) System.out.println("You have a three of a kind!");
        else if (twoPair()) System.out.println("You have two pair!");
        else if (pair()) System.out.println("You have a pair!");
        else System.out.println("You have a high card!");
    }

    private boolean straightFlush()
    {
        //many of these are just combinations of other rules
        if (straight() && flush()) return true;
        return false;
    }
    private boolean fullHouse()
    {
        //I used to think that a full house needed a high card, but the Wikipedia link in the spec says it doesn't :)
        //hence this works:
        if (threeOfAKind() && pair()) return true;
        return false;
    }
    private boolean fourOfAKind()
    {
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).get(1) == 4) return true;
        }
        return false;
    }
    private boolean flush()
    {
        //all the suits are the same
        for (int i = 0; i < frequency.length; i++)
        {
            if (frequency[i] == 5) return true;
        }
        return false;
    }
    private boolean straight()
    {
        //sequential cards
        if (sequentialCards == 5) return true;
        //according to the wikipedia link in the spec:
        //"Under high rules, an ace can rank either high (as in A♦ K♣ Q♣ J♦ 10♠, an ace-high straight) or low (as in 5♣ 4♠ 3♠ 2♣ A♠, a five-high straight),
        //but cannot simultaneously rank both high and low."
        //this if statement accounts for that
        if (sequentialCards == 4)
        {
            int aceStraight = 0;
            for (int i = 0; i < hand.size(); i++)
            {
                if (hand.get(i).getValue() > 9) aceStraight++; //making sure it cannot rank high and low simultaneously
                if (hand.get(i).getValue() == 1) aceStraight++;
            }
            if (aceStraight == 5) return true;
        }
        return false;
    }
    private boolean threeOfAKind()
    {
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).get(1) == 3) return true;
        }
        return false;
    }
    private boolean twoPair()
    {
        int count = 0;
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).get(1) == 2) count++;
        }
        if (count == 2) return true;
        else return false;
    }
    private boolean pair()
    {
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).get(1) == 2) return true;
        }
        return false;
    }

    public void printData()
    {
        //for unit testing, outputs the data that my program uses to decide the hand ranks
        System.out.println("-------\nDATA:\nSequential cards: " + sequentialCards);

        System.out.println("---\n0=S  1=C  2=H  3=D");
        for (int i = 0; i < frequency.length; i++)
        {
            System.out.println("Frequency of suit " + i + ": " + frequency[i]);
        }
        System.out.println("---");

        for (int i = 0; i < pairs.size(); i++)
        {
            System.out.println("Amount of " + pairs.get(i).get(0) + "s: " + pairs.get(i).get(1));
        }
    }



}
