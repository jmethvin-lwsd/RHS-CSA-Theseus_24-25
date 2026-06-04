package src;
import java.util.*;

public class Blackjack {
    private int money = 1000;
    private int handCount = 1;
    Scanner sc = new Scanner(System.in);
    public Blackjack(){
        
    }
    public void startGame(){
        boolean go = true;
        while (go){
            Deck deck = new Deck();
            System.out.println("Balance: " + money);
            System.out.print("Place your bet, or type END to end the game: ");
            String in = sc.next();
            int bet = 0;

            if (in.toLowerCase().equals("end")){
                go = false;
                break;
            }
            try {
                bet = Integer.valueOf(in);
            } catch (java.lang.NumberFormatException e) {
                System.out.println("Invalid input. Type an integer.");
                continue;
            }
            if (bet>money||bet<0){
                System.out.println("Invalid bet amount");
                continue;
            }

            money-=bet;
            handCount = 1;

            Hand playerHand1 = new Hand(deck.draw(), deck.draw());
            Hand dealerHand = new Hand(deck.draw(), deck.draw());
            Card c1 = playerHand1.getC1();
            Card c2 = playerHand1.getC2();
            Card d = dealerHand.getC1();

            System.out.println("Your cards: " + c1.getValue() + " and " + c2.getValue());
            System.out.println("Dealer is showing " + d.getValue());

            if (playerHand1.isBlackjack()) {
                if (dealerHand.isBlackjack()) {
                    System.out.println("Dealer and Player both have blackjack. Push.");
                    money+=bet;
                    continue;
                } else {
                    System.out.println("Player has a blackjack!");
                    money+=(5*bet)/2;
                    continue;
                }
            } else if (dealerHand.isBlackjack()) {
                System.out.println("Dealer has blackjack. You lost.");
                continue;
            }

            String action = promptUser();
            evaluateAction(playerHand1, dealerHand, action, deck, bet);
        }
    }

    private void evaluateAction(Hand h, Hand d, String action, Deck deck, int bet) {
        Card c1 = h.getC1();
        Card c2 = h.getC2();

        while (true) {
            switch (action) {
                case "split":
                    if (!(c1.getNum() == c2.getNum())) {
                        System.out.println("You can't split two different cards.");
                        action = promptUser();
                        continue;
                    } else {
                        if (money < bet) {
                            System.out.println("You don't have enough money to split.");
                            action = promptUser();
                            continue;
                        }
                        handCount++;
                        money -= bet;

                        Hand h1 = new Hand(c1, deck.draw());
                        System.out.println("New hand: " + h1.getC1().getValue() + " and " + h1.getC2().getValue());
                        action = promptUser();
                        evaluateAction(h1, d, action, deck, bet);
                        
                        Hand h2 = new Hand(c2, deck.draw());
                        System.out.println("New hand: " + h2.getC1().getValue() + " and " + h2.getC2().getValue());
                        action = promptUser();
                        evaluateAction(h2, d, action, deck, bet);

                        return;
                    }

                case "h":
                    h.addCard(deck.draw());
                    System.out.println("Your hand is now " + h.printCards() + ".");
                    if (h.total() > 21) {
                        System.out.println("Bust! Dealer wins.");
                        handCount--;
                        return;
                    } else {
                        action = promptUser();
                        evaluateAction(h, d, action, deck, bet);
                        return;
                    }

                case "s":
                    handCount--;
                    if (handCount == 0) {
                        dealerTurn(h, d, deck, bet);
                    }
                    return;

                case "d":
                    if (money < bet) {
                        System.out.println("You don't have enough money to double.");
                        action = promptUser();
                        continue;
                    }
                    handCount--;
                    money -= bet;
                    bet *= 2;
                    System.out.println("Bet doubled!");
                    h.addCard(deck.draw());
                    System.out.println("Your hand is now " + h.printCards());
                    if (h.total() > 21) {
                        System.out.println("Bust! Dealer wins.");
                        return;
                    }
                    dealerTurn(h, d, deck, bet);
                    return;

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    private String promptUser() {
        System.out.print("\nOptions: Type SPLIT for split, H for hit, S for stand, and D for double: ");
        return sc.next().toLowerCase();
    }

    private void dealerTurn(Hand h, Hand d, Deck deck, int bet) {
        System.out.println("Dealer has " + d.printCards());
        while (d.total() < 17) {
            d.addCard(deck.draw());
            System.out.print("Dealer hits. Dealer now has " + d.total() + ". ");
            if (d.total() > 21) {
                System.out.println("Dealer busts!\nYou won!");
                money += bet * 2;
                return;
            }
        }
        
        System.out.println("Dealer " + d.total() + " vs your " + h.total() + ".");
        
        if (h.total() > d.total()) {
            System.out.println("You won!");
            money += bet * 2;
        } else if (h.total() == d.total()) {
            System.out.println("Push");
            money += bet;
        } else {
            System.out.println("You lost!");
        }
        System.out.println();
    }
}
