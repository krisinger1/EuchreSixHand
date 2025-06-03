public class Player {
    private String name; // name of the player
    private Hand hand; // player's hand of cards
    private Team team; // team the player belongs to
    private Bid[] possibleBids = new Bid[4]; // array of bids made by the player

    public Player(String name) {
        this.name = name; // set the player's name
        this.hand = new Hand(); // create a new hand for the player
    }

    public String getName() {
        return name; // return the player's name
    }
    public Hand getHand() {
        return hand; // return the player's hand
    }
    public Team getTeam() {
        return team; // return the player's team
    }
    public void setTeam(Team team) {
        this.team = team; // set the player's team
    }

    private double evaluateHand(Suit trumpSuit) {
        return hand.evaluateHand(trumpSuit);
    }

    public Bid determineBid(){
        int tricks = 0; // number of tricks the player can take for this trump suit
        Bid strongestBid = null; // strongest bid made by the player
        double strength = 0;    // strength of the player's hand
        double maxStrength = 0; // maximum strength of the player's hand

        for (Suit s: Suit.values()){
            strength=evaluateHand(s);
            tricks = (int)Math.round(strength);
            Bid currentBid = new Bid(tricks, s); 

            if (strength > maxStrength) { 
                maxStrength = strength; 
                strongestBid = currentBid; // set the strongest bid to the current bid
            }

            possibleBids[s.ordinal()] = currentBid; // create a new bid for the player
            System.out.println("*************************");
            System.out.println("Strngth: " + strength);
            System.out.println("Bid: " + possibleBids[s.ordinal()]);
            System.out.println("strongest bid: " + strongestBid);

            System.out.println("*************************");
        }

        // for (Bid bid:possibleBids){
        //     System.out.println("looking at bid: " + bid);
        //     if (strongestBid == null || bid.isGreaterThan(strongestBid)){
        //         strongestBid = bid; // set the strongest bid to the current bid
        //     }
        //     System.out.println("strongest bid: " + strongestBid);
        // }
         return strongestBid; // return the strongest bid made by the player


    }

    public void sortHand(){
        hand.sortHand();
    }


}
