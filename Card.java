public class Card
{
    private int value;
    //I toyed with making this a string for A, J, Q, K but decided it would be easier to convert for the outputs.
    //instead of having to convert all over the place
    private String suit;
    //this has to be a string haha
    private String representation ="";
    //this is what will be output for the user to see and make sense of (ie the conversion)
    public Card(int value, String suit)
    {
        this.value = value;
        this.suit = suit;

        if (value == 1) representation = "A";
        else if (value == 11) representation = "J";
        else if (value == 12) representation = "Q";
        else if (value == 13) representation = "K";
        else representation = Integer.toString(value);
        representation += "[" + suit + "]";
    }

    public String getRepresentation()
    {
        return representation;
    }

    public void printRepresentation()
    {
        System.out.print(representation + " ");
    }

    public int getValue()
    {
        return value;
    }

    public Object getSuit()
    {
        return suit;
    }
}
