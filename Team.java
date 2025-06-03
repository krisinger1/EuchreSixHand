import java.util.ArrayList;

public class Team {
    private int score = 0;
    private int maxPlayers = 3;
    private ArrayList<Player> players; // array of players in the team

    public Team(Player player1, Player player2, Player player3) {
        // Constructor for Team class
        players = new ArrayList<>(); // create array of players
        players.add(player1); // add player 1 to the team
        players.add(player2); // add player 2 to the team   
        players.add(player3); // add player 3 to the team
        player1.setTeam(this); // set player 1's team to this team
        player2.setTeam(this); // set player 2's team to this team
        player3.setTeam(this); // set player 3's team to this team
    }

    public ArrayList<Player> getPlayers() {
        return players; // return the array of players in the team
    }

    public String toString(){
        String str="";
        for (Player p : players) {
            str += p.getName() + " "; // add each player's name to the string
        }
        return str; // return the team's name
    }

    public void addScore(int score) {
        this.score += score; // add the score to the team's total score
    }

    public int getScore() {
        return score; // return the team's total score
    }
    public void resetScore() {
        score = 0; // reset the team's score to 0
    }

}
