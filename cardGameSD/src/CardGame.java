import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CardGame extends Thread {
    static ArrayList<Deck> decks = new ArrayList<>();
    static ArrayList<Player> players = new ArrayList<>();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter an integer: ");
            if (scanner.hasNextInt()) {
                numberOfPlayers = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("That's not a valid integer. Please try again.");
                scanner.nextLine();
            }
        }

        // Create decks
        for (int i = 1; i <= numberOfPlayers; i++) {
            decks.add(new Deck(i));
        }

// Create players
        for (int i = 1; i <= numberOfPlayers; i++) {
            int disCardDeck = (i + 1 > numberOfPlayers) ? 1 : i + 1;
            players.add(new Player(i, decks.get(i - 1), decks.get(disCardDeck - 1), new Hand()));
            System.out.println(players.get(i -1).getPreference() + players.get(i-1).getDiscardDeck().getDeckId() + players.get(i-1).getDrawDeck().getDeckId());
        }

        String filePath = "src/pack.txt"; // Replace with the actual path of your file
        Pack pack = createPack(filePath);

        // deal initial hand
        for(int x=1; x <= 4; x++){
            for(int y=0; y<numberOfPlayers; y++){
                players.get(y).getHand().addCard(pack.removeCard());
            }
        }
        // deal decks
        for(int x=1; x <= 4; x++){
            for(int y=0; y<numberOfPlayers; y++){
                decks.get(y).addCard(pack.removeCard());
            }
        }

        CyclicBarrier barrier = new CyclicBarrier(players.size(), () -> {
            // This task is executed when all threads reach the barrier
            System.out.println("All players have finished their turn. Proceeding to next turn.");
        });

        for (Player player : players) {
            Thread playerThread = new Thread(() -> {
                // Task for each player
                while(!hasPlayerWon()) {
                    playerDraws(player);
                    playerDiscards(player);
                    System.out.println(player.getPreference());
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            playerThread.start();
        }
    }

    // Method to read a file and process each line
    public static Pack createPack(String filePath) {
        Pack pack = new Pack();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
        
            while ((line = br.readLine()) != null) {
             
                int value = Integer.parseInt(line);
                pack.addCard(new Card(value));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return pack;
    }

    public static Card playerDiscards(Player player){
        Card discardedCard = player.discard();
        player.getDiscardDeck().addCard(discardedCard);
        return discardedCard;
    }

    public static Card playerDraws(Player player){
        Card drawnCard = player.getDrawDeck().removeCard();
        player.draw(drawnCard);
        return drawnCard;
    }

    public static boolean hasPlayerWon(){
        boolean hasown = false;
        for(Player player: players){
            if(player.hasWon()){
                return true;
            }
        }
        return hasown;
    }



}