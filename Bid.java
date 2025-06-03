public class Bid {
    private Suit bidSuit;
    private int bidTricks;
    
    Bid(int tricks, Suit suit) {
        bidSuit=suit;
        bidTricks=tricks;
    }
    
    /**
     * Get the suit for this bid
     * @return the suit for this bid
     */
    Suit getSuit(){
        return bidSuit;
    }
    
    /**
     * Get the number of tricks bid
     * @return the number of tricks bid
     */
    int getBidTricks(){
        return bidTricks;
    }

    /**
     * Set the suit for this bid
     * @param s - the suit to set
     */
    void setSuit(Suit s){
        bidSuit=s;
    }
    
    /**
     * Set the number of tricks for this bid
     * @param t - the number of tricks to set
     */
    void setTricks(int t){
        bidTricks=t;
    }

    /**
     * Compare this bid to another bid
     * @param b - the bid to compare to
     * @return true if this bid is higher, false if not
     */
    public boolean isGreaterThan(Bid b){
        if (b==null) return true; // if the other bid is null, this bid is greater
        return bidTricks>b.getBidTricks();
    }

    /**
     * @return string representation of this bid
     */
    public String toString(){
        return bidTricks+" "+bidSuit;
    }
    
    /**
     * Print this bid to the console
     */
    void printBid(){
        System.out.println(this);
    }
    
}

