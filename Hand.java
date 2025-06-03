import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private final double STRENGTH_RIGHT_BAUER = 1.0; // strength of the right bauer
    private final double STRENGTH_LEFT_BAUER = 0.5; // strength of the left bauer
    private final double STRENGTH_OFFSUIT_ACE = 0.5; // strength of ace in off suit
    private final double STRENGTH_PARTNERSUIT_ACE = 0.45; // strength ace in partner suit
    private final double STRENGTH_EXTRA_TRUMP = 0.35; // strength of extra trump cards
    private final double STRENGTH_VOIDS = 0.5; // strength of voids

    private ArrayList<Card> handCards; // array of cards in the hand
    private int[] handShape = new int[4];   // 3 = spades, 2 = hearts, 1 = diamonds, 0 = clubs
    private double[] handStrength = new double[4]; // strength of the hand for each suit
    
    public Hand(){
        // Constructor for Hand class
        handCards = new ArrayList<>(); // create array of cards

    }

    public void addCard(Card c){
        // Adds a card to the hand
        handCards.add(c);
        handShape[c.getSuit().ordinal()]++; // increment the hand shape for the suit of the card
    }

    public void removeCard(Card c){
        // Removes a card from the hand
        handCards.remove(c);
        handShape[c.getSuit().ordinal()]--; // increment the hand shape for the suit of the card
    }   

    public void sortHand(){
        // Sorts the hand of cards
        Collections.sort(handCards); // sort the hand of cards
        Collections.reverse(handCards); // reverse the order of the cards in the hand
    }

    public int[] getHandShape(){
        // Returns the hand shape
        return handShape; // return the hand shape
    }


    public double evaluateHand(Suit trumpSuit){
        // Evaluates the hand of cards
        // Formula:
        // Protected off suit Aces = .75 point
        // Singeton Aces = .25
        // Right bauer = 1 point
        // Left bauer = .75 points
        // Void and 2+ trump = .5 + .5 * num trump over 2

        double numLikelyTricks = 0; // number of tricks can take if trumpsuit is trump;
        Card right1 = null;
        Card left1 = null; 
        Card right2 = null;
        Card left2 = null; 
        int trumpIndex = trumpSuit.ordinal(); // index of the trump suit

        System.out.println("Trump is: " +trumpSuit); 

        // move jacks and adjust hand shape
        // for (Card c : handCards) { // for each card in the hand
        //     if (c.getSuit() == trumpSuit.partnerSuit() && c.getValue() == 11) {
        //         handShape[c.getSuit().ordinal()]--; // decrement the hand shape for the suit of the card
        //         handShape[trumpIndex]++; // increment the hand shape for the trump suit
        //         c.setLeft();
        //         if (left1 == null) left1 = c;
        //         else left2 = c;
        //     }
        //     else if (c.getSuit() == trumpSuit && c.getValue() == 11) {
        //         c.setRight(); // set the card to the left or right bauer
        //         if (right1 == null) right1 = c;
        //         else right2 = c;
        //     }
        // }

        adjustForTrump(trumpSuit);

        int numTrump = handShape[trumpIndex]; // number of trump cards in the hand
        System.out.println("num trump: " + numTrump);
        sortHand();

        // check for Aces and bauers
        for (Card c : handCards) { 
            int value = c.getValue(); 
            Suit suit = c.getSuit();
            switch (value) { // check the suit of the card
                case 14: // Ace
                    if (suit != trumpSuit) {
                        if (suit == trumpSuit.partnerSuit()) { // partner suit ace
                            numLikelyTricks += STRENGTH_PARTNERSUIT_ACE; 
                            System.out.println("partner suit ace: " + STRENGTH_PARTNERSUIT_ACE);
                        } else { // off suit ace
                            numLikelyTricks += STRENGTH_OFFSUIT_ACE; 
                            System.out.println("off suit ace: " + STRENGTH_OFFSUIT_ACE);
                        }
                        // if (handShape[suit.ordinal()] == 1) {
                        //     numLikelyTricks += .5; // singleton ace
                        //     System.out.println("singleton ace: .5");
                        // }
                        // else {
                        //     numLikelyTricks += .75; // protected ace
                        //     System.out.println("protected ace: .75");
                        // }

                    }
                    break;
                case 15: // Left bauer
                    numLikelyTricks += STRENGTH_LEFT_BAUER;
                    System.out.println("left bauer: "+STRENGTH_LEFT_BAUER);
                    //protected
                    // if (numTrump > 2) numLikelyTricks += .25; // add .25 for the protected left bauer
                    break;
                case 16: // Right bauer
                    numLikelyTricks += STRENGTH_RIGHT_BAUER;
                    System.out.println("right bauer: "+STRENGTH_RIGHT_BAUER);
                    //protected
                    // if (numTrump > 1) numLikelyTricks += .25;
                    break;
            }
            if (value <15 && suit == trumpSuit) { // trump cards
                numLikelyTricks += STRENGTH_EXTRA_TRUMP; // add .25 for each trump card
                System.out.println("trump card: "+STRENGTH_EXTRA_TRUMP);
            }
        }

        // check for voids
        int numVoids =0;
        for (Suit s : Suit.values()) { // for each suit
            if (!s.isTrump() && handShape[s.ordinal()] == 0) { // off suit void
                numVoids ++;
            }
        }
        double voidStrength = STRENGTH_VOIDS*(numVoids-1-numTrump);
        System.out.println("voids:" + voidStrength);
        if (voidStrength>0) numLikelyTricks += voidStrength;

        // if(numTrump > 3) {
        //     numLikelyTricks += .25*(numTrump-3); // add .5 for each trump over 2
        //     System.out.println("trump over 3: " + .5*(numTrump-3));
        // }
        // else if (numTrump<3) {
        //     // TODO: logic not quite right here - need to adjust for 2 trump and 1 trump
        //     numLikelyTricks = numLikelyTricks> 1.5 ? numLikelyTricks - .5*(3-numTrump) : 0;
        //     // numLikelyTricks -= .5*(3-numTrump); // subtract .5 for each trump under 2
        //     System.out.println("trump under 3: " + .5*(numTrump-3));
        // }


        //TODO: put hand back to normal after evaluation - account for hands that have 2 rights or two lefts!!!!
        // if (right1 != null) right1.demoteRight(); // demote the right bauer
        // if (left1 != null) left1.demoteLeft(); // demote the left bauer
        // if (right2 != null) right2.demoteRight(); // demote the right bauer
        // if (left2 != null) left2.demoteLeft(); // demote the left bauer

        adjustForNoTrump();

        handStrength[trumpIndex] = numLikelyTricks; // set the hand strength for the trump suit

        return numLikelyTricks;

    }

    public void adjustForTrump(Suit trumpSuit){
        int trumpIndex = trumpSuit.ordinal(); // index of the trump suit

        for (Card c : handCards) { // for each card in the hand
            if (c.getSuit() == trumpSuit.partnerSuit() && c.getValue() == 11) {
                handShape[c.getSuit().ordinal()]--; // decrement the hand shape for the suit of the card
                handShape[trumpIndex]++; // increment the hand shape for the trump suit
                c.setLeft();
                // if (left1 == null) left1 = c;
                // else left2 = c;
            }
            else if (c.getSuit() == trumpSuit && c.getValue() == 11) {
                c.setRight(); // set the card to the left or right bauer
                // if (right1 == null) right1 = c;
                // else right2 = c;
            }
        }
    }

    public void adjustForNoTrump(){
        // Adjusts the hand for no trump
        for (Card c : handCards) { // for each card in the hand
            if (c.getValue() == 16) {
                c.demoteRight(); // demote the right bauer
            }
            else if (c.getValue() == 15) {
                c.demoteLeft(); // demote the left bauer
            }
        }

    }


    public double getStrengthInSuit(Suit s){
        // Returns the strength of the hand for the given suit
        return handStrength[s.ordinal()]; // return the strength of the hand for the suit
    }

    public ArrayList<Card> getCards(){
        return handCards;
    }

    public String toString(){
        String str = ""; // create an empty string
        for (Card c : handCards) { // for each card in the hand
            if (c.getSuit().isTrump()) str+="* "; // add a * if the card is trump
            else str+=" "; // add a space if the card is not trump
            str += c.toString() + "\n"; // add the card to the string
        }
        return str; // return the string representation of the hand
    }

    public String asCSV(){
        String str = ""; // create an empty string
        for (Card c : handCards) { // for each card in the hand
            str += c.toString() + ","; // add the card to the string
        }
        return str; // return the string representation of the hand
    }
}
