public enum Suit {
    CLUBS(false),DIAMONDS(false),HEARTS(false),SPADES(false);
    private boolean trumpSuit;
    
    Suit (boolean t){trumpSuit=t;}
    
    boolean isTrump(){
        return trumpSuit;
        // if (trumpSuit==true) return true;
        // else return false;
    }
    
    private Suit partner;
    static {
        CLUBS.partner = SPADES;
        DIAMONDS.partner=HEARTS;
        HEARTS.partner=DIAMONDS;
        SPADES.partner=CLUBS;
    }
    public Suit partnerSuit(){
        return partner;
    }
    
    void declareTrump(){
        clearTrump();
        trumpSuit=true;
    }
    
    void setTrumpStatus(boolean t){
        trumpSuit=t;
    }
    
    static void clearTrump(){
        SPADES.setTrumpStatus(false);
        HEARTS.setTrumpStatus(false);
        DIAMONDS.setTrumpStatus(false);
        CLUBS.setTrumpStatus(false);
    }
    
    public String toString(){
        if (this==SPADES) return "Spades"; 
        else if (this==CLUBS) return "Clubs";
        else if (this==HEARTS) return "Hearts";
        else if (this==DIAMONDS) return "Diamonds";
        else return "not a suit";
    }
}