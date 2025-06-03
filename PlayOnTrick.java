public class PlayOnTrick{
    private Player player; // player who is playing the card
    private Card card; // card being played 

    public PlayOnTrick(Player player, Card card) {
        this.player = player; // set the player who is playing the card
        this.card = card; // set the card being played
    }

    public Player getPlayer() {
        return player; // return the player who is playing the card
    }
    public Card getCard() {
        return card; // return the card being played
    }
}