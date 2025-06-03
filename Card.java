public class Card implements Comparable<Card>{
		
    private Suit cardSuit;
    private int cardValue;
    
    
    Card(Suit s, int v){
        if (s!=Suit.SPADES && s!=Suit.CLUBS && s!=Suit.HEARTS && s!=Suit.DIAMONDS) throw new IllegalArgumentException("Illegal card suit");
        else cardSuit=s;
        if (v>=9||v<=16) cardValue=v;
        else throw new IllegalArgumentException("Illegal playing card value");
    }
    
    public int getValue(){
        return cardValue;
    }
    
    public Suit getSuit(){
        return cardSuit;
    }

    public void setRight(){
        if (cardValue==11) cardValue=16;
    }

    public void setLeft(){
        if (cardValue==11) {
            cardValue=15;
            cardSuit=cardSuit.partnerSuit();
        }
    }

    public void demoteRight(){
        if (cardValue==16) cardValue=11;
    }
     public void demoteLeft(){
        if (cardValue==15) {
            cardValue=11;
            cardSuit=cardSuit.partnerSuit();
        }
     }

    /**
     * 
     * @return whether this card is the left bauer (Jack of the trump suit's partner)
     */
    public boolean isLeft(){
        return ((cardValue==11 && cardSuit.partnerSuit().isTrump())||(cardValue==15));
    }
    
    public String getValueAsString(){
        switch (cardValue) {
        case 9: return "9";
        case 10: return "10";
        case 11: return "Jack";
        case 12: return "Queen";
        case 13: return "King";
        case 14: return "Ace";
        case 15: return "Left Bauer";
        case 16: return "Right Bauer";
        default: return "Not a Valid Value";
        }
    }


    
    /**
     * Compare this card to another card
     * @param c - the card to compare to
     * @return 1 if this card is higher, 0 if equal, -1 if lower
     */
    public int compareTo(Card c){
        if (c==null) return 1;
        //my card is trump
        if (cardSuit.isTrump()){
            if (!c.cardSuit.isTrump()) return 1;  //compared card is not trump - mine wins
            else if (cardValue>c.cardValue) return 1;  //compared card is Trump - mine higher and wins
            else if (cardValue==c.cardValue) return 0; // compared card is trump and equal - tie
            else return -1;								// compared card is trump and higher than mine - it wins
        }

        //my card is not trump and compared card is trump - compared card wins
        else if (c.cardSuit.isTrump()) return -1;

        // else cards are same suit, but neither is trump
        else if (cardSuit==c.cardSuit){
            if (cardValue>c.cardValue) return 1; //my card is higher - I win
            else if (cardValue==c.cardValue) return 0; //equal - tie
            else return -1; // onther card is higher - it wins
        }

        else if (cardSuit.ordinal() > c.cardSuit.ordinal()) return 1; //my card is higher suit - I win
        else if (cardSuit.ordinal() < c.cardSuit.ordinal()) return -1; //other card is higher suit - it wins    

        //my card is not trump and neither is compared card - just compare value
        else if (cardValue>c.cardValue) return 1;

        //else if (cardValue==c.cardValue) return 0; not actually equal if suits different - messes up findCard()
        else return -1;
    }

    /**
     * Determine if this card is higher than the compared card
     * @param c the card to compare to
     * @return true if this card is higher than the compared card, false if not
     */
    public boolean isGreaterThan(Card c){
        return (compareTo(c)>0);
    }


    /**
     * Determine if this car is the same as the compared card
     * @param c the card to compare to
     * @return true if the two cards are the same suit and value, false if not
     */
    public boolean isSameAs(Card c){
        return (compareTo(c)==0);
    }
    
    /**
     * print this card to the console using the toString method
     */
    public void printCard(){
        System.out.println(this);
    }

        /**
     * @return string representation of this card
     */
    public String toString(){
        return getValueAsString() + " of "+cardSuit;
    }
    
}