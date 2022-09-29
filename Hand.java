import java.util.ArrayList;

public class Hand
{

    private ArrayList<Card> hand;


    public Hand()
    {
        hand = new ArrayList<Card>();
        sortHand();
    }
    public Hand(ArrayList<Card> hand)
    {
        this.hand = hand;
        sortHand();
    }

    public void addCard(Card card)
    {
        if (hand == null) hand = new ArrayList<Card>();
        hand.add(card);
    }

    public void discardHand()
    {
        hand = null;
    }

    public void printHand()
    {
        sortHand(); //just make sure they are sorted no matter what!!!!!
        //the scoring system will not work unless the hand is sorted
        //I initially had it in the scoring class for this reason, but I realised that the printing the cards in order
        //looked very neat so I do it here instead. Theoretically, the hand should always be printed, so it will always
        //be sorted, but I call sortHand() in other places just in case.
        System.out.println("Your hand is: ");
        for (int i = 0; i < hand.size(); i++)
        {
            hand.get(i).printRepresentation();
        }
        System.out.println(" ");
    }

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    private void sortHand()
    {
        //this entire function is separate mostly just for neatness, it's a bubble sort that orders by value
        //I feel the bubble sort is appropriate as the hand is small so the for loops will never run extremely long
        for (int i = 0; i < hand.size(); i++)
        {
            for (int j = 0; j < hand.size() - 1; j++)
            {
                if (hand.get(j).getValue() > hand.get(j + 1).getValue())
                {
                    Card temp = hand.get(j);
                    hand.set(j, hand.get(j + 1));
                    hand.set(j + 1, temp);
                }
            }
        }
    }


    //all the below are test cases for unit testing. I give the program a pre-made hand and it should say what it is
    public void testStraightFlush()
    {
        discardHand();
        addCard(new Card(1, "S"));
        addCard(new Card(2, "S"));
        addCard(new Card(3, "S"));
        addCard(new Card(4, "S"));
        addCard(new Card(5, "S"));
        sortHand();
    }

    public void test()
    {
        discardHand();
        //4[H] 4[S] 9[D] 10[D] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(9, "D"));
        addCard(new Card(10, "D"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testFourOfAKind()
    {
        discardHand();
        //4[H] 4[S] 4[C] 4[D] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(4, "C"));
        addCard(new Card(4, "D"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testFullHouse()
    {
        discardHand();
        //4[H] 4[S] 4[D] K[H] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(4, "D"));
        addCard(new Card(13, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testFlush()
    {
        discardHand();
        //4[H] 5[H] 7[H] J[H] K[H]
        addCard(new Card(4, "H"));
        addCard(new Card(5, "H"));
        addCard(new Card(7, "H"));
        addCard(new Card(11, "H"));
        addCard(new Card(13, "H"));
        sortHand();
    }

    public void testStraight()
    {
        discardHand();
        //1[H] 2[S] 3[D] 4[H] 5[D]
        addCard(new Card(1, "H"));
        addCard(new Card(2, "S"));
        addCard(new Card(3, "D"));
        addCard(new Card(4, "H"));
        addCard(new Card(5, "D"));
        sortHand();
    }

    public void testThreeOfAKind()
    {
        discardHand();
        //4[H] 4[S] 4[D] 10[H] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(4, "D"));
        addCard(new Card(10, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testTwoPair()
    {
        discardHand();
        //4[H] 4[S] 5[D] K[H] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(5, "D"));
        addCard(new Card(13, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testOnePair()
    {
        discardHand();
        //4[H] 4[S] 5[D] J[H] K[D]
        addCard(new Card(4, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(5, "D"));
        addCard(new Card(11, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testHighCard()
    {
        discardHand();
        //1[H] 4[S] 5[D] J[H] K[D]
        addCard(new Card(1, "H"));
        addCard(new Card(4, "S"));
        addCard(new Card(5, "D"));
        addCard(new Card(11, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testAcesHigh()
    {
        discardHand();
        //1[H] 10[S] J[D] Q[H] K[D]
        addCard(new Card(1, "H"));
        addCard(new Card(10, "S"));
        addCard(new Card(11, "D"));
        addCard(new Card(12, "H"));
        addCard(new Card(13, "D"));
        sortHand();
    }

    public void testAcesHighFlush()
    {
        discardHand();
        //1[H] 10[H] J[H] Q[H] K[H]
        addCard(new Card(1, "H"));
        addCard(new Card(10, "H"));
        addCard(new Card(11, "H"));
        addCard(new Card(12, "H"));
        addCard(new Card(13, "H"));
        sortHand();
    }
}
