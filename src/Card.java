package src;

public class Card {
    private String value;
    private String suit;
    public Card(String val, String su){
        value = val;
        suit = su;
    }
    public String getValue(){
        return value;
    }
    public int getNum(){
        if (value.equals("T")||value.equals("J")||value.equals("Q")||value.equals("K")){
            return 10;
        }
        if (value.equals("A")){
            return 1;
        }
        else{
            return Integer.valueOf(value);
        }
    }
    public String getSuit(){
        return suit;
    }
    public String toString(){
        return value + " of "+suit+"s";
    }
}
