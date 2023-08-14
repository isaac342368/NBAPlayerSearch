// --== CS400 File Header Information ==--
// Name: Isaac Odedoyin
// Email: oodedoyin@wisc.edu
// Team: AU
// TA: Cameron
// Lecturer: Gary Dahl
// Notes to Grader: None
import java.util.List;
import java.util.LinkedList;
/**
 *This class runs the NBAPlayerSearchApp by combining all the carious methods created in the different classes
 */
public class NBAPlayerSearchApp {	
	/**
	 *This is the main method for the NBAPlayerSearchApp class
	 */
	public static void main(String[]args) throws Exception {
		System.out.println("\nWelcome to the NBA Player Search App!\n");
		List<NBAPlayersInterface> players = new PlayerLoader().loadFile("PlayerList.csv");
		SearchBackEndInterface engine = new SearchBackEnd();
		SearchFrontEndInterface ui = new SearchFrontEnd();
		for ( int i = 0; i < players.size(); i ++) {
			engine.addPlayer((PlayerStats) (players.get(i)));
		}
		ui.run(engine);
	}
	
}
