import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

public class GameManagerSystem {
    private static Scanner scanner = new Scanner(System.in);
    private List<Game> gamesList;
    private List<Game> installedList;
    private double totalMemory; // Total storage available for games in MB
    private double totalMoney;

    public GameManagerSystem(double totalMemory, double totalMoney) {
        this.gamesList = new ArrayList<>();
        this.installedList = new ArrayList<>();
        this.totalMemory = totalMemory;
        this.totalMoney = totalMoney;
    }

    // create predefined game list
    public void addGame(Game game) {
        gamesList.add(game);
    }

    public void buyGame(Game game) {
        totalMoney -= game.getPrice();
        game.setPurchase(true);
    }

    // buy and/or install a game

    public void buyAndInstallGame(String name) {
        Game gameToInstall = gamesList.stream()
                .filter(game -> game.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (gameToInstall == null) {
            System.out.println("Game not found.");
            return;  // Exit the method if no game is found
        }
        else {
            // not purchased
            if(gameToInstall.getPurchase() == false && gameToInstall.getPrice() < totalMoney) {
                buyGame(gameToInstall);
            }
        }

        // Check if the game is already installed
        if (gameToInstall.getInstalled()) {
            System.out.println("Game is already installed.");
            return;  // Exit the method if the game is already installed
        }

        //System.out.println(gameToInstall.getName());
        if (totalMemory >= gameToInstall.getSize()) {
            gameToInstall.setInstalled(true);
            installedList.add(gameToInstall);
            totalMemory -= gameToInstall.getSize();
            System.out.println("Game installed successfully.");
        } else {
            System.out.println("Not enough memory to install the game.");
        }
    }

    // uninstall a game
    public void uninstallGame(String name) {
        Game gameToRemove = installedList.stream()
                .filter(game -> game.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst()
                .orElse(null);

        if (gameToRemove != null) {
            gameToRemove.setInstalled(false);
            installedList.remove(gameToRemove);
            totalMemory += gameToRemove.getSize();
            System.out.println("Game uninstalled successfully.");
        }
        else {
            System.out.println("Game cannot been uninstlled since it is not installed");
        }
    }

    public double rateGame(String name) {
        Game gameToRate = gamesList.stream()
                .filter(game -> game.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (gameToRate != null) {
            System.out.print("Rate the game (up to 5): ");
            double rating = scanner.nextDouble();
            if(rating > 5.0){
                System.out.println("Invalid rating. The rating should be up to 5.");
                return -1;
            }
            // Calculate the new average rating
            double newRating = (gameToRate.getRating() * gameToRate.getRatingNum() + rating) / (gameToRate.getRatingNum() + 1);

            // Update the game with the new rating and increment the rating count
            gameToRate.setRating(newRating);
            gameToRate.setRatingNum(gameToRate.getRatingNum() + 1);
            System.out.printf("New rating of %s is %.2f%n", name, newRating);

            return newRating;
        } else {
            // Handle the case where the game is not found
            System.out.println("Game not found.");
            return -1;
        }
    }


    public List<Game> searchGames(String query) {
        return gamesList.stream()
                .filter(game -> game.getName().toLowerCase().contains(query.toLowerCase()) ||
                        game.getCategory().toLowerCase().contains(query.toLowerCase()) ||
                        game.getDeveloperTeam().toLowerCase().contains(query.toLowerCase()) ||
                        (game.getInstalled() && "installed".equals(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void showAllGames() {
        if (gamesList.isEmpty()) {
            System.out.println("No games available.");
            return;
        }

        System.out.printf("%-20s %-12s %-15s %-20s %-16s %-10s %n",
                "Name", "Size (MB)", "Category", "Developer Team", "Price (USD)", "Rating");

        // Print each game's details
        for (Game game : gamesList) {
            System.out.printf("%-20s %-12.2f %-15s %-20s %-16.2f %-10.2f %n",
                    game.getName(),
                    game.getSize(),
                    game.getCategory(),
                    game.getDeveloperTeam(),
                    game.getPrice(),
                    game.getRating(),
                    game.getRatingNum());
        }
    }

    public double getRemainingMemory() {
        return totalMemory;
    }
    public double getRemainingMoney() {
        return totalMoney;
    }
}
