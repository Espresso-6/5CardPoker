import java.util.ArrayList;

public class Deck
{
    private ArrayList<Card> deck = new ArrayList<Card>();

    //this constructor will generate a deck of cards
    Deck()
    {
        generateDeck();
        System.out.println("Deck created");
    }

    Deck(int numDecks)
    {
        for (int i = 0; i < numDecks; i++)
        {
            generateDeck();
        }
        System.out.println("Deck created");
    }

    public void generateDeck()
    {
        String suit = "";
        for (int i = 0; i < 4; i++)
        {
            //this ♣ ♠ ♥ ♦ is cool but my terminal can't print such characters, very unfortunate
            if (i == 0) suit = "S";
            else if (i == 1) suit = "C";
            else if (i == 2) suit = "H";
            else suit = "D";


            //we start at 1 because cards start at A then 2 etc. It's just easier to keep track of.
            for (int value = 1; value < 14; value++)
            {
                Card card = new Card(value, suit);
                deck.add(card);
            }
        }
    }

    public void shuffle()
    {
        //this is probably excessive...
        //it picks two random cards and swaps them 5200 times
        //nice.
        System.out.println("Shuffling...");
        for (int i = 0; i < 5200; i++)
        {
            int r1 = (int) (Math.random() * 52);
            int r2 = (int) (Math.random() * 52);
            while (r1 == r2) r2 = (int) (Math.random() * 52); //makes sure the numbers are never the same
            Card temp = deck.get(r1);
            deck.set(r1, deck.get(r2));
            deck.set(r2, temp);
        }
        //is this realistic???
        //maybe not so much
        //we'll deal the cards realistically, it's more important that the deck is shuffled thoroughly
        //and less important that it is shuffled realistically.
        //The spec mentions a possible extension where a realistic shuffle is implemented.
        //The new algorithm can be pasted here or a new method can be created, say realisticShuffle().
        //I think as things are, it should be easy for someone else to adjust, add to or rewrite shuffle() as necessary.
    }

    public Card getCard(int i)
    {
        return deck.get(i);
    }

    public void printDeck()
    {
        //for testing, prints the whole deck
        for (int i = 0; i < deck.size(); i++)
        {
            deck.get(i).printRepresentation();
        }
        System.out.println(" ");
    }

    public ArrayList<Card> deal(int amount)
    {
        //"realistic" as cards are dealt from the top of the deck as a dealer would deal them
        //the amount means I can choose how many cards to deal
        ArrayList<Card> myHand = new ArrayList<Card>();
        for (int i = 0; i < amount; i++)
        {
            myHand.add(deck.get(i)); //adds them to the "player's" hand
        }
        return myHand;
    }
}

