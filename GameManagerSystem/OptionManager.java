import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class OptionManager {
    private static GameManager manager;
    private static Scanner scanner = new Scanner(System.in);

    public void operateMenu(){
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
                    buyAndInstallGame();
                    break;
                case 3:
                    uninstallGame();
                    break;
                case 4:
                    searchGames();
                    break;
                case 5:
                    rateGame();
                    break;
                case 6:
                    showRemainingMemory();
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
    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1 - Show All Games");
        System.out.println("2 - Install/Buy a Game");
        System.out.println("3 - Uninstall a Game");
        System.out.println("4 - Search Games");
        System.out.println("5 - Rate a Game");
        System.out.println("6 - Show Remaining Memory");
        System.out.println("7 - Show Remaining Money");
        System.out.println("0 - Exit");
    }

    private static void initializeGameManagerSystem() {
        // Initialize GameManagerSystem
        manager = new GameManager(1024, 100);

        File file = new File("games.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 9) {
                    String name = data[0];
                    double size = Double.parseDouble(data[1]);
                    String category = data[2];
                    String developerTeam = data[3];
                    double price = Double.parseDouble(data[4]);
                    double rating = Double.parseDouble(data[5]);
                    int ratingNum = Integer.parseInt(data[6]);
                    boolean isInstalled = Boolean.parseBoolean(data[7]);
                    boolean isPurchased = Boolean.parseBoolean(data[8]);
                    Game game = new Game(name, size, category, developerTeam, price, rating, ratingNum, isInstalled, isPurchased);
                    manager.addGame(game);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The games file was not found.");
            e.printStackTrace();
        }
    }

    private static void showAllGames() {
        manager.showAllGames();
    }

    // install a game
    private static void buyAndInstallGame() {
        System.out.print("Enter the of the name of the game you want to buy/install: ");
        String name = scanner.nextLine();
        manager.buyAndInstallGame(name);
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
        System.out.println("Search instructions: ");
        System.out.println("Enter name/category/developer team to search for games, or enter 'installed' to see installed games\n");
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        List<Game> results = manager.searchGames(query);
        System.out.println("Search Results:");
        if (results.isEmpty()) {
            System.out.println("No games found.");
        } else {
            results.forEach(game -> {
                String installedStatus = game.getInstalled() ? "installed" : "not installed";
                System.out.println(game.getName() + " - " + game.getCategory() + " - " + game.getDeveloperTeam() + " - " + game.getPrice() + " USD" + " - " + game.getSize() + " MB" + " - " + installedStatus);
            });
        }
    }

    private static void showRemainingMemory() {
        double remainingMemory = manager.getRemainingMemory();
        System.out.println("Remaining Memory: " + String.format("%.2f", remainingMemory) + " MB");
    }

    private static void showRemainingMoney() {
        double remainingMoney = manager.getRemainingMoney();
        System.out.println("Remaining Money: " + String.format("%.2f", remainingMoney) + " Dollars");
    }

    private static void rateGame() {
        System.out.print("Enter the name of the game you want to rate: ");
        String name = scanner.nextLine();


        manager.rateGame(name);

    }


}



