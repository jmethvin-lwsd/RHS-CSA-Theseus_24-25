package src;
import java.util.*;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    public Deck(){
        for (int i = 2; i<=9;i++){
            deck.add(new Card(Integer.toString(i),"Heart"));
        }
        for (int i = 2; i<=9;i++){
            deck.add(new Card(Integer.toString(i),"Spade"));
        }
        for (int i = 2; i<=9;i++){
            deck.add(new Card(Integer.toString(i),"Club"));
        }
        for (int i = 2; i<=9;i++){
            deck.add(new Card(Integer.toString(i),"Diamond"));
        }
        deck.add(new Card("T","Heart"));
        deck.add(new Card("J","Heart"));
        deck.add(new Card("Q","Heart"));
        deck.add(new Card("K","Heart"));
        deck.add(new Card("A","Heart"));
        deck.add(new Card("T","Spade"));
        deck.add(new Card("J","Spade"));
        deck.add(new Card("Q","Spade"));
        deck.add(new Card("K","Spade"));
        deck.add(new Card("A","Spade"));
        deck.add(new Card("T","Club"));
        deck.add(new Card("J","Club"));
        deck.add(new Card("Q","Club"));
        deck.add(new Card("K","Club"));
        deck.add(new Card("A","Club"));
        deck.add(new Card("T","Diamond"));
        deck.add(new Card("J","Diamond"));
        deck.add(new Card("Q","Diamond"));
        deck.add(new Card("K","Diamond"));
        deck.add(new Card("A","Diamond"));
        Collections.shuffle(deck);
    }
    public Card draw(){
        return deck.remove(0);
    }
}
