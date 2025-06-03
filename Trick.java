public class Trick {
    private PlayOnTrick[] playedCards; // array of cards played in the trick
    private int currentCardsInTrick=0;
    private int maxCardsInTrick=6; // max cards in a trick
    private PlayOnTrick winningPlay; // variable to hold the winning play

    public Trick() {
        playedCards = new PlayOnTrick[maxCardsInTrick]; // 6 cards in a trick
    }

    public void playCardOnTrick(PlayOnTrick play) {
        
        if (currentCardsInTrick < maxCardsInTrick) {
            playedCards[currentCardsInTrick] = play; // add the card to the trick
            if (currentCardsInTrick==0) winningPlay = play; // set the lead card as the winning play
            else {
                // set the winning play to the current play if it is greater than the winning play
                if (play.getCard().isGreaterThan(winningPlay.getCard())) winningPlay = play; 
            }
            currentCardsInTrick++; // increment the number of cards in the trick

        } else {
            System.out.println("Trick is full, cannot play more cards."); // trick is full
        }
    }

    public PlayOnTrick getWinningPlay() {
        return winningPlay; // return the current winning play
    }

    public boolean isTrickFull() {
        return currentCardsInTrick == maxCardsInTrick; // check if the trick is full
    }


}
