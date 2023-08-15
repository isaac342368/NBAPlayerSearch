
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

interface SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine);
    // Here is a sample of the minimal set of options that 
    // this front end will support:
    // NBA Player Search Command Menu:
    // 1. Add new Player into Database
    // 2. Discover who has the highest average points per game
    // 3. Discover who has the highest average assists per game
    // 4. Discover who has the most average rebounds per game
    // 5. Player Search
    // 6. Quit
}

/**
 * This class handles the front end code by dealing with the medium between the user and the program. Asks 
 * the user for information, and then uses that information accordingly to return info or store data.
 *
 * @author Jake Murawski
 */
@SuppressWarnings("unchecked")
public class SearchFrontEnd implements SearchFrontEndInterface {
	Scanner scr = new Scanner(System.in);
	SearchBackEnd backEnd = new SearchBackEnd();
	boolean looper = true; //Program loops as long as this is true
	int userInt; //Stores the selection made by the user
	String testingString; //This String is used for testing convenience
       	List<PlayerStats> testingList = new LinkedList(); //This List is also for testing	
	
	/**
	 * This is the central method which keeps the program running. From here the user is 
	 * able to select which function they would like to perform by entering an number. The
	 * program loops until the user wishes to exit. Helper methods are used to keep the 
	 * code here relativly minimal. 
	 *
	 * @author Jake Murawski
	 */
	@Override
	public void run(SearchBackEndInterface engine) {
		logo();
		while (looper) {
			printMenu();
			userInt = scrInt(scr);	
			switch (userInt) {
				case 1:
					line();
					System.out.println(playerAdd(engine));
					line();
					continue;
				case 2:
					line();
					System.out.println(mostPoints(engine));
					line();
					continue;
				case 3:
					line();
					System.out.println(mostAssists(engine));
					line();
					continue;
				case 4:
					line();
					System.out.println(mostRebounds(engine));
					line();
					continue;
				case 5: 
					line();
					System.out.println("Option 5 selected: Player Search");
               				System.out.println("Please enter a word to search for in the database:");
					String dummy = scrLine(scr);
					System.out.print(playerSearch(engine, dummy));
					line();
					continue;
				case 6:
					line();
					System.out.println("Have a nice day!");
					System.out.println("Shutting down");
					looper = false;
					continue;
				default:
					line();
					System.out.println("Please enter a valid number (1-6)");
					line();
					continue;
			}

		}
	}
	
	/**
	 * This method manages the player search function. After entering a phrase to search for, this 
	 * method uses the findPlayer method in the back end to create a list of players containing the 
	 * phrase. This method then creates a large String putting each player's info into a nice readible 
	 * format and then displays it
	 *
	 * @return The large String containing all players with the provided phrase
	 */
	public String playerSearch(SearchBackEndInterface engine, String phrase) {
		List<PlayerStats> players;
		String searchedPlayers = "";
		players = engine.findPlayers(phrase);
		System.out.println("Here are all players in the database which contain the provided phrase:\n");
		for (PlayerStats player : players) {
			searchedPlayers += player.getName() + " with " + player.getPoints() + " points, " 
				+ player.getAssist() + " assists, and " +
				player.getRebounds() + " rebounds per game\n\n";
		}
		return searchedPlayers;
	}
	
	/**
	 * Returns a String describing the player with the most rebounds currently in the engine
	 */
	public String mostRebounds(SearchBackEndInterface engine) {
		PlayerStats player = engine.getMostRebounds();
                String name = player.getName();
                double rebounds = player.getRebounds();

                System.out.println("Option 4 selected: Discover who has the highest average rebounds per game");
                return mostHelper("rebounds", name, rebounds);
        }
	
	/**
         * Returns a String describing the player with the most assists currently in the engine
         */
	public String mostAssists(SearchBackEndInterface engine) {
		PlayerStats player = engine.getMostAssists();
		String name = player.getName();
		double assists = player.getAssist();

		System.out.println("Option 3 selected: Discover who has the highest average assists per game");
		return mostHelper("assists", name, assists);
	}
	
	/**
         * Returns a String describing the player with the most points currently in the engine
         */
	public String mostPoints(SearchBackEndInterface engine) {
		PlayerStats player = engine.getMostPoints();
		String name = player.getName();
		double points = player.getPoints();

		System.out.println("Option 2 selected: Discover who has the highest average points per game");
		return mostHelper("points", name, points);
	}

	/**
	 * Since all of the methods that return the player with the greatest value do essentially the same 
	 * thing, this helper method avoids some of the redundancy by formatting the returned String the 
	 * same way for all three.
	 */
	public String mostHelper(String type, String name, double value) {
		String string = "Currently, the player with the highest average " + type + " per game is ";
		string += name + ", averaging " + value + " " + type + " per game.";
		return string;
	}

	/**
	 * This method asks the user to provide all needed information to insert a new player into the 
	 * engine, and then uses the addPlayer method in the back end to insert them into the tree. Returns 
	 * a String saying the player was added to the engine. 
	 */
	public String playerAdd(SearchBackEndInterface engine) {
		String name;
		double points;
		double assists;
		double rebounds;
		String player;

		System.out.println("Option 1 selected: Add new Player into DataBase");
		System.out.println("Please enter the player's first and last name, separated by a space:");
		name = scrLine(scr);

		System.out.println("Please enter the player's average points per game:");
		points = scrDouble(scr);

		System.out.println("Please enter the player's average assists per game:");
                assists = scrDouble(scr);

		System.out.println("Please enter the player's average rebounds per game:");
                rebounds = scrDouble(scr);

		PlayerStats newPlayer = new PlayerStats(name, points, rebounds, assists);
		engine.addPlayer(newPlayer);
		player = "\nPlayer " + name + ", " + points + " points, " + assists + " assists, "
			+ rebounds + " rebounds has been added!";
		return player; 
	}

	/**
	 * Creates a line of 60 dahses for neatness and clarity 
	 */
	public void line() {
		System.out.println();
		String s = "";
		for (int i = 0; i < 60; i++) {
			s += "-";
		}
		System.out.println(s);
		System.out.println();
	}

	/**
	 * Prints the UI to the screen for the user to read amd select from 
	 */
	public void printMenu() {
		String s = "";
		s += "1. Add new Player into Database\n";
		s += "2. Discover who has the highest average points per game\n";
		s += "3. Discover who has the highest average assists per game\n";
		s += "4. Discover who has the most average rebounds per game\n";
		s += "5. Player Search\n";
		s += "6. Quit\n";
		System.out.print(s);
	}
	
	/**
	 * Scanner helper method to make sure the user provides a double. Recursively asks the player
	 * to enter a double if they failed to do so initially
	 */
	public double scrDouble(Scanner scr) {
		double number;
		if (scr.hasNextDouble()) {
			number = scr.nextDouble();
			scr.nextLine();
			return number;
		}
		else {
			scr.nextLine();
			System.out.println("Please enter a double");
			return scrDouble(scr);
		}
	}

	/**
	 * Scanner helper method to make sure an int was entered. If not, returns 0 which triggers the default
	 * selection in the switch above.
	 */
	public int scrInt(Scanner scr) {
		int number;
		if (scr.hasNextInt()) {
			number = scr.nextInt();
			scr.nextLine();
			return number;
		}
		else {
			scr.nextLine();
			return 0;
		}
	}
	
	/**
	 * Scanner helper method which cleans up the String entered by the user.
	 */
	public String scrLine(Scanner scr) {
		String line;
		while (!scr.hasNextLine()) { }
		line = scr.nextLine().trim();
		return line;
	}

	/**
	 * A fun little visual to make the program cooler
	 */
	public void logo() { //Gotta make that user interface look spicy
		String s = "";
		s += " .-----------------. .----------------.  .----------------. \n";
		s += "| .--------------. || .--------------. || .--------------. |\n";
		s += "| | ____  _____  | || |   ______     | || |      __      | |\n";
		s += "| ||_   \\|_   _| | || |  |_   _ \\    | || |     /  \\     | |\n";
		s += "| |  |   \\ | |   | || |    | |_) |   | || |    / /\\ \\    | |\n";
		s += "| |  | |\\ \\| |   | || |    |  __'.   | || |   / ____ \\   | |\n";
		s += "| | _| |_\\   |_  | || |   _| |__) |  | || | _/ /    \\ \\_ | |\n";
		s += "| ||_____|\\____| | || |  |_______/   | || ||____|  |____|| |\n";
		s += "| |              | || |              | || |              | |\n";
		s += "| '--------------' || '--------------' || '--------------' |\n";
		s += " '----------------'  '----------------'  '----------------' \n";
		System.out.println(s);
	}
}

/**
 * Placeholder class that was used before the code was implemented. 
 */
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("The front end has not been implemented yet.");
    }
}
