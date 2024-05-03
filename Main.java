import java.util.List;
import java.util.Scanner;

public class Main {
    private static GameManagerSystem manager;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeGameManagerSystem();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllGames();
                    break;
                case 2:
                    installGame();
                    break;
                case 3:
                    uninstallGame();
                    break;
                case 4:
                    searchGames();
                    break;
                case 5:
                    showRemainingMemory();
                    break;
                case 6:
                	rateGame();
                	break;
                case 7:
                    showRemainingMoney();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private static void initializeGameManagerSystem() {
        // Initialize GameManagerSystem with a total memory of 1024 MB
        manager = new GameManagerSystem(1024,200);

        // Predefined games to add to the system
        Game game1 = new Game("Elden Ring", 50, "RPG", "FromSoftware", 59.99, 0, 0, false, false);
        Game game2 = new Game("Stardew Valley", 150, "Simulation", "ConcernedApe", 14.99, 0, 0, false, false);
        Game game3 = new Game("Cyberpunk 2077", 200, "RPG", "CD Projekt Red", 49.99, 0, 0, false, false);
        manager.addGame(game1);
        manager.addGame(game2);
        manager.addGame(game3);
        
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Show All Games");
        System.out.println("2 - Install a Game");
        System.out.println("3 - Uninstall a Game");
        System.out.println("4 - Search Games");
        System.out.println("5 - Show Remaining Memory");
        System.out.println("6 - Rate a Game");
        System.out.println("7 - Show Remaining Money");
        System.out.println("0 - Exit");
    }

    private static void showAllGames() {
        manager.showAllGames();
    }

    // install a game
    private static void installGame() {
        System.out.print("Enter game name: ");
        String name = scanner.nextLine();
        manager.installGame(name);
        //System.out.println("Game installed successfully.");
    }

    // uninstall a game
    private static void uninstallGame() {
        System.out.print("Enter the name of the game you want to uninstall: ");
        String name = scanner.nextLine();
        manager.uninstallGame(name);
        //System.out.println("Game uninstalled successfully.");
    }

    private static void searchGames() {
        System.out.print("Enter search query (name/category/developer team) or \'installed\' for installed games: ");
        String query = scanner.nextLine();
        List<Game> results = manager.searchGames(query);
        System.out.println("Search Results:");
        if (results.isEmpty()) {
            System.out.println("No games found.");
        } else {
            results.forEach(game -> System.out.println(game.getName() + " - " + game.getPrice() + " USD"));
        }
    }

    private static void showRemainingMemory() {
        System.out.println("Remaining Memory: " + manager.getRemainingMemory() + " MB");
    }
    private static void showRemainingMoney() {
        System.out.println("Remaining Money: " + manager.getRemainingMoney() + " Dollars");
    }
    
    private static void rateGame() {
        System.out.print("Enter the name of the game you want to rate: ");
        String name = scanner.nextLine();  // Use nextLine() to capture full input including spaces.
        System.out.print("Rate the game (up to 5): ");
        double rating = scanner.nextDouble();
        scanner.nextLine();  // Consume the leftover newline character after reading a double.

        double newRating = manager.rateGame(name, rating);
        if (newRating == -1) {
            System.out.println("Unable to find the game or error in rating.");
        } else {
            System.out.printf("New rating of %s is %.2f%n", name, newRating);  // Corrected the format string
        }
    }

    
}

