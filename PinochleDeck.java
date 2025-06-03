public class PinochleDeck {
    private Card[] deck;
    public boolean isShuffled=false;
    private int cardsDealt=0;
    private int deckSize=48;
    
    /**
     * Create 48 card pinochle deck - shuffled by default
     * @param shuffled - true if the deck should be shuffled, false if not
     */
    public PinochleDeck(){
        this(true);
    }

    /**
     * Create 48 card pinochle deck - shuffled or unshuffled
     * @param shuffled - true if the deck should be shuffled, false if not
     */
    PinochleDeck(boolean shuffled){
        deck=new Card[deckSize];
        int cardIndex = 0;
        for (Suit s:Suit.values()){
            for (int v=9;v<=14;v++){
                // create 2 of each card
                deck[cardIndex]=new Card(s,v);
                deck[cardIndex+1]=new Card(s,v); 
                cardIndex+=2;
            }
        }
        if (shuffled) {
            shuffle();
            isShuffled=true;
        }
        else isShuffled=false;
    }
    

    /**
     * Shuffles the deck
     */
    public void shuffle(){  //shuffle the deck
        cardsDealt=0;
        recursiveShuffle(deckSize);
        isShuffled=true;
    }
    
    /**
     * Recursive shuffle helper method
     * @param length - the number of cards left to shuffle
     */
    private void recursiveShuffle(int length){
        Card temp;
        
        if (length<2) return;
        else{
            //swap last and random card
            int randIndex=(int)(Math.random()*(length));
            temp = deck[length-1];
            deck[length-1]=deck[randIndex];
            deck[randIndex]=temp;
            recursiveShuffle(length-1);
            return;
        }
    }
    
    /**
     * Deals a card from the deck
     * @return the next card in the deck, or null if no cards are left
     */
    public Card dealCard(){
        if (cardsDealt==deck.length) {
            System.out.println("No more cards in the deck!");
            return null;
        }

        else {
            cardsDealt++;
            //System.out.println(cardsUsed);
            return deck[cardsDealt-1];
        }
    }

    /**
     * Prints the deck to the console using the toString method
     */
    public void printDeck(){
        System.out.println(this);
    }

    /**
     * @return a string representation of the deck
     */
    public String toString(){
        String s="";
        for (Card c:deck){
            s+=c.toString()+"\n";
        }
        return s;
    }
}