import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class EuchreSixHand {
    static ArrayList<String> lines = new ArrayList<>(); // Array list to store lines of text

        // Main method
    public static void main(String[] args) {
        // Create a new instance of the class
        String filename = args[0]; // Get the filename from the command line arguments


        lines.add("player,card1,card2,card3,card4,card5,card6,card7,card8,spades,hearts, diamonds,clubs,best bid\n");


        PinochleDeck deck = new PinochleDeck(); // Create a new deck of cards - shuffled

        // Create players
        ArrayList<Player> players = new ArrayList<>(); // Create an array list of players
        players.add(new Player("Player 1")); // Add player 1 to the array list
        players.add(new Player("Player 2")); // Add player 2 to the array list
        players.add(new Player("Player 3")); // Add player 3 to the array list
        players.add(new Player("Player 4")); // Add player 4 to the array list
        players.add(new Player("Player 5")); // Add player 5 to the array list
        players.add(new Player("Player 6")); // Add player 6 to the array list

        // create teams
        Team team1 = new Team(players.get(0), players.get(2), players.get(4)); // Create team 1
        Team team2 = new Team(players.get(1), players.get(3), players.get(5)); // Create team 2

        // System.out.println("Team 1: " + team1); // Print team 1
        // System.out.println("Team 2: " + team2); // Print team 2

        // System.out.println("Shuffled deck: ");
        // deck.printDeck(); // Print the shuffled deck

        // System.out.println("player 1 hand:"+players.get(0).getHand()); // Print player 1's hand

        // Suit.SPADES.declareTrump();

        // Deal cards to players
        for (int i=0; i<8; i++) { 
            for (Player p:players) { 
                p.getHand().addCard(deck.dealCard());
            }
        }

        // Print each player's hand
        for (Player p:players) { 
            System.out.println("------------------"+p.getName()+"---------------------------\n");

            p.sortHand();
            // System.out.println(p.getName() + " sorted hand:\n"+p.getHand()); 
            // double spadeTricks = p.evaluateHand(Suit.SPADES); // Evaluate the hand for each player
            // System.out.println("evaluate hand: ");
            System.out.println(p.getName() + " sorted hand after eval:\n"+p.getHand()); 


            System.out.println("new hand shape: " + 
                p.getHand().getHandShape()[0] + " " + 
                p.getHand().getHandShape()[1] + " " + 
                p.getHand().getHandShape()[2] + " " + 
                p.getHand().getHandShape()[3] +"\n"); // Print the hand shape
            // System.out.println("*** Tricks in spades: " + spadeTricks +"\n"); 

            // double heartTricks = p.evaluateHand(Suit.HEARTS);
            // System.out.println("*** Tricks in hearts: " + heartTricks +"\n"); 

            // double diamondTricks = p.evaluateHand(Suit.DIAMONDS);
            // System.out.println("*** Tricks in diamonds: " + diamondTricks +"\n");

            // double clubTricks = p.evaluateHand(Suit.CLUBS);
            // System.out.println("*** Tricks in clubs: " + clubTricks +"\n");
            // System.out.println("---------------------------------------------\n");

            Bid bestBid = p.determineBid(); // Determine the best bid for each player
            System.out.println("Best bid is: "+bestBid);

            p.getHand().adjustForTrump(bestBid.getSuit());
            p.sortHand();

            lines.add(p.getName()+","+
                p.getHand().asCSV()+
                new BigDecimal(p.getHand().getStrengthInSuit(Suit.SPADES)).setScale(2, RoundingMode.HALF_UP)+","+
                new BigDecimal(p.getHand().getStrengthInSuit(Suit.HEARTS)).setScale(2, RoundingMode.HALF_UP)+","+
                new BigDecimal(p.getHand().getStrengthInSuit(Suit.DIAMONDS)).setScale(2, RoundingMode.HALF_UP)+","+
                new BigDecimal(p.getHand().getStrengthInSuit(Suit.CLUBS)).setScale(2, RoundingMode.HALF_UP)+","+
                bestBid+"\n");



        }
        lines.add("\n"); // Add the empty line to the array list
        writeToFile(filename, lines);


    }


    public static void writeToFile(String filename, ArrayList<String> lines) {

        try (FileWriter writer = new FileWriter(filename,true)) {
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }

    }
}