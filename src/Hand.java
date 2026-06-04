package src;
import java.util.*;

public class Hand {
	private Card c1, c2;
	private ArrayList<Card> cards = new ArrayList<Card>();
	public Hand(Card c1, Card c2) {
		cards.add(c1);
		cards.add(c2);
		this.c1 = c1;
		this.c2 = c2;
	}

	public Card getC1() {
		return c1;
	}
	public Card getC2() {
		return c2;
	}

	public void addCard(Card c) {
		cards.add(c);
	}

	public boolean isBlackjack() {
		return (total() == 21) ? true : false;
	}

	public int total() {
		int total = 0;
		boolean aceFlag = false;
		for(Card c : cards) {
			total += c.getNum();
			if (c.getNum() == 1) {
				aceFlag = true;
			}
		}
		if (aceFlag && total <= 11) {
			total += 10;
		}
		return total;
	}

	public String printCards() {
		String out = "";
		for(Card c : cards) {
			out += c.getValue() + " ";
		}
		return out;
	}
}
