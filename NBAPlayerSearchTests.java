// Name: Isaac Odedoyin

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.console.ConsoleLauncher;

/**
 *This class runs all the tests for the NBAPlayerSearchaApp.java
 *
 */
public class NBAPlayerSearchTests{
	
	//FRONT END TESTS

	/**
	 * This first test makes sure that the mostPoints, mostAssists, and mostRebounds all return the correct
	 * String to the application. We start by testing the case with only one player in the system, and then
	 * try it again with multiple players in the System.
	 *
	 */
	@Test
	public void frontEndTest_mostValues() {

		//Testing with only one player
		SearchBackEnd engine = new SearchBackEnd();
                SearchFrontEnd tester = new SearchFrontEnd();
		engine.addPlayer(new PlayerStats("Billy Joel", 10.0, 20.0, 30.0));

		String mostPoints = "Currently, the player with the highest average points per game is Billy Joel, "
                       + "averaging 10.0 points per game.";
                String mostAssists = "Currently, the player with the highest average assists per game is Billy Joel, "
                       + "averaging 30.0 assists per game.";
                String mostRebounds = "Currently, the player with the highest average rebounds per game is Billy Joel, "
                       + "averaging 20.0 rebounds per game.";

		assertEquals(tester.mostPoints(engine), mostPoints);
                assertEquals(tester.mostAssists(engine), mostAssists);
                assertEquals(tester.mostRebounds(engine), mostRebounds);

		//Testing with multiple players
		engine = new SearchBackEnd();
		tester = new SearchFrontEnd();
		engine.addPlayer(new PlayerStats("Ada", 1.0, 2.0, 3.0));
		engine.addPlayer(new PlayerStats("Brandi", 3, 2, 1));
		engine.addPlayer(new PlayerStats("Chris", 5.0, 5, 5.0));
		engine.addPlayer(new PlayerStats("Darwin", 1, 3, 5));
		engine.addPlayer(new PlayerStats("Eli", 7.0, 0.0, 3.0));
		engine.addPlayer(new PlayerStats("Francis", 2.0, 2.0, 2.0));
		engine.addPlayer(new PlayerStats("Gary Dahl", 5, 6, 7));

		mostPoints = "Currently, the player with the highest average points per game is Eli, "
		       + "averaging 7.0 points per game.";
		mostAssists = "Currently, the player with the highest average assists per game is Gary Dahl, "
                       + "averaging 7.0 assists per game.";
		mostRebounds = "Currently, the player with the highest average rebounds per game is Gary Dahl, "
                       + "averaging 6.0 rebounds per game.";

		assertEquals(tester.mostPoints(engine), mostPoints);
		assertEquals(tester.mostAssists(engine), mostAssists);
		assertEquals(tester.mostRebounds(engine), mostRebounds);
	}
	
	/**
	 * This method test to make sure all the various scanner methods do their intended purpose. Given 
	 * that the user is asked to provide various pieces of data (ints, doubles, Strings) in various 
	 * circumstances, we have methods that deal with all of these scenarios. When picking their option 
	 * from the UI menu, if the user does not select an integer, the application will set their choice to
	 * zero and trigger the default response. When enter new player info and asked to provide a double,
	 * anything besides ints and doubles will scan the next line and ask the user to provide a double. ints 
	 * will be converted to doubles. Lastly when entering name, the application will trim it
	 *
	 */
	@Test	
	public void frontEndTest_scannerMethods() {

		//Testing the scrDouble method
		SearchFrontEnd tester = new SearchFrontEnd();
		String s = "Gary\nDahl\n12\n";
		Scanner scr = new Scanner(s);
		assertEquals(tester.scrDouble(scr), 12.0);

		s = "Gary\nDahl\n12.0\n";
                scr = new Scanner(s);
                assertEquals(tester.scrDouble(scr), 12.0);

		//Testing the scrInt method
		s = "Hello\n";
                scr = new Scanner(s);
                assertEquals(tester.scrInt(scr), 0);

		s = "5Hi\n";
                scr = new Scanner(s);
                assertEquals(tester.scrInt(scr), 0);

		s = "5\n";
                scr = new Scanner(s);
                assertEquals(tester.scrInt(scr), 5);
		
		//Testing the scrLine method
		s = "\t James Madison \t\n\n";
                scr = new Scanner(s);
                assertEquals(tester.scrLine(scr), "James Madison");
	}


	/**
         * This test makes sure that, given a phrase, the application can return a large String containing 
	 * all players who's name contains that phrase. We first test on an application containing only one
	 * player, using a variety of segments from their name (both upper and lower case). We then test
	 * on an application with multiple players, to make sure we can correctly return all players containing
	 * the phrase. 
         *
         */
	@Test
	public void frontEndTest_playerSearch() {
		//Testing with one player
		SearchBackEnd engine = new SearchBackEnd();
                SearchFrontEnd tester = new SearchFrontEnd();
                engine.addPlayer(new PlayerStats("Billy Joel", 10.0, 20.0, 30.0));

		String playersSearched = "Billy Joel with 10.0 points, 30.0 assists, and 20.0 rebounds per game\n\n" ;
		assertEquals(tester.playerSearch(engine, "Billy"), playersSearched);
                assertEquals(tester.playerSearch(engine, "ly"), playersSearched);
                assertEquals(tester.playerSearch(engine, "j"), playersSearched);
		
		//Testing with multiple players
                engine = new SearchBackEnd();
                tester = new SearchFrontEnd();
                engine.addPlayer(new PlayerStats("Ada", 1.0, 2.0, 3.0));
                engine.addPlayer(new PlayerStats("Brandi", 3, 2, 1));
                engine.addPlayer(new PlayerStats("Chris", 5.0, 5, 5.0));
                engine.addPlayer(new PlayerStats("Darwin", 1, 3, 5));
                engine.addPlayer(new PlayerStats("Eli", 7.0, 0.0, 3.0));
                engine.addPlayer(new PlayerStats("Francis", 2.0, 2.0, 2.0));
                engine.addPlayer(new PlayerStats("Gary Dahl", 5, 6, 7));

		String playersSearched1 = "Brandi with 3.0 points, 1.0 assists, and 2.0 rebounds per game\n\n"
			+ "Darwin with 1.0 points, 5.0 assists, and 3.0 rebounds per game\n\n"
			+ "Chris with 5.0 points, 5.0 assists, and 5.0 rebounds per game\n\n" 
			+ "Francis with 2.0 points, 2.0 assists, and 2.0 rebounds per game\n\n"
			+ "Eli with 7.0 points, 3.0 assists, and 0.0 rebounds per game\n\n";
		assertEquals(tester.playerSearch(engine, "i"), playersSearched1);

		String playersSearched2 = "Gary Dahl with 5.0 points, 7.0 assists, and 6.0 rebounds per game\n\n";
		assertEquals(tester.playerSearch(engine, "Dahl"), playersSearched2);
	}




	//DATA WRANGLER TESTS

       /**
	 *  This test method confirms if the getAssist works correctly when loading csv file correctly.
	 *  The  test comparethe first line of code with correct number of assits for player.     
	 *  
	 */
	@Test
	public void dataWranglerTest_TestgetAssist(){
		PlayerLoader csvWriter = new PlayerLoader();
                try {
                	List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
                	assertEquals(list.get(0).getAssist(), 5.3);
                } catch (FileNotFoundException e) {
                	System.out.println("caught");
                }
	}

	//test Data Wrangler
        /**
         *  This test method confirms if the getPoints works correctly when loading csv file correctly.
         *  The  test compare the 10th line of code with correct number of assits for player.
         *
         */
        @Test
	public void dataWranglerTest_TestgetPoints(){
        	PlayerLoader csvWriter = new PlayerLoader();
        	try {
            		List<NBAPlayersInterface> list = csvWriter.loadFile( "PlayerList.csv");
            		assertEquals(list.get(10).getPoints(), 16.4);
        	} catch (FileNotFoundException e) {
            		System.out.println("caught");
        	}
	}

	//test Data Wrangler
        /**
         *  This test method confirms if the getName works correctly when loading csv file correctly.
         *  The  test comparethe first line of code with correct number of assits for player.  
	 *             
         */
        @Test
	public void dataWranglerTest_TestgetName(){
        	PlayerLoader csvWriter = new PlayerLoader();
       		try {
            		List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
            		assertEquals(list.get(10).getName(), "kemba walker");
                } catch (FileNotFoundException e) {
            		System.out.println("caught");
        	}
    	}

   	//test Data Wrangler
        /**
         *  This test method confirms if the getAssist works correctly when loading csv file correctly.
         *  The  test comparethe first line of code with correct number of assits for player.
         *
         */
      	@Test
	public void dataWranglerTest_TestgetRebounds(){
		PlayerLoader csvWriter = new PlayerLoader();
        	try {
            		List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
	   		assertEquals(list.get(1).getRebounds(), 7.7);
        	} catch (FileNotFoundException e) {
            		System.out.println("caught");
       		}
   	}

    //BACK END TESTS

    /**
     * This test method confirms if the addPlayer function works correctly with the Red Black tree.
     * It tests a worst case scenario with every letter (except the last two) in alphabetical order.
     * The last two are switched to add a different element of complexity. If the tree is made as
     * intended, addPlayer works correctly and the test will be asserted as true.
     *
     */
   @Test
      public void backendDeveloper_TestaddPlayer() {
        boolean leftBranch = false;
        boolean rightBranch = false;
        try {
            SearchBackEnd tester = new SearchBackEnd();
            tester.addPlayer(new PlayerStats("A", 1, 2, 3));
            tester.addPlayer(new PlayerStats("B", 4, 5, 21));
            tester.addPlayer(new PlayerStats("C", 1, 1, 1));
            tester.addPlayer(new PlayerStats("D", 7, 8, 9));
            tester.addPlayer(new PlayerStats("E", 10, 20, 12));
            tester.addPlayer(new PlayerStats("F", 13, 14, 15));
            tester.addPlayer(new PlayerStats("G", 2, 2, 2));
            tester.addPlayer(new PlayerStats("I", 16, 17, 18));
            tester.addPlayer(new PlayerStats("H", 19, 11, 21));

            // Below boolean values confirm each element in the tree is in the correct place.
            leftBranch = tester.RBT.root.leftChild.data.getName().equals("B")
                && tester.RBT.root.leftChild.leftChild.data.getName().equals("A")
                && tester.RBT.root.leftChild.rightChild.data.getName().equals("C");

            rightBranch = tester.RBT.root.rightChild.data.getName().equals("F")
                && tester.RBT.root.rightChild.leftChild.data.getName().equals("E")
                && tester.RBT.root.rightChild.rightChild.data.getName().equals("H")
                && tester.RBT.root.rightChild.rightChild.rightChild.data.getName().equals("I")
                && tester.RBT.root.rightChild.rightChild.leftChild.data.getName().equals("G");
           
            assertTrue(tester.RBT.root.data.getName().equals("D") && leftBranch && rightBranch);
            
        } catch (Exception e) {
            fail("Should not have thrown any exception: Did not Properly addPlayer");
        }
    }

    /**
     * This test method confirms if the back end code successfully gathers each player who has the
     * most points, assists, and rebounds. A key note is to ensuring that the test works correctly
     * is in the presence of a tie, the original most player will retain their title. The tests
     * passes when it successfully returns the correct most players from a generated RBT.
     *
     */

    @Test
    void backendDeveloper_TestmostPlayers() {
        try {
            SearchBackEnd tester = new SearchBackEnd();

            tester.addPlayer(new PlayerStats("Player A", 1, 2, 3));
            tester.addPlayer(new PlayerStats("player B", 4, 5, 21));
            tester.addPlayer(new PlayerStats("Gamer 1", 1, 1, 1));
            tester.addPlayer(new PlayerStats("Player C", 7, 8, 9));
            tester.addPlayer(new PlayerStats("Gamer D", 10, 20, 12));
            tester.addPlayer(new PlayerStats("player E", 13, 14, 15));
            tester.addPlayer(new PlayerStats("Gamer 2", 2, 2, 2));
            tester.addPlayer(new PlayerStats("Player F", 16, 17, 18));
            tester.addPlayer(new PlayerStats("Player G", 19, 11, 21)); // 21 here should not change
                                                                       // the
                                                                       // most rebound player
            tester.addPlayer(new PlayerStats("Gamer 3", 3, 3, 3));

            if (tester.getMostPoints() == null || tester.getMostAssists() == null
                || tester.getMostRebounds() == null) {
                fail("Test failed to assign most variables");
            }

            if (!tester.getMostPoints().getName().equalsIgnoreCase("player g")) {
                fail("Test failed to return the correct player with the most points");
            }

            if (!tester.getMostAssists().getName().equalsIgnoreCase("player b")) {
                fail("Test failed to return the correct player with the most assists");
            }

            if (!tester.getMostRebounds().getName().equalsIgnoreCase("gamer d")) {
                fail("Test failed to return the correct player with the most rebounds");
            }
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
  }

    /**
     * This test method confirms if the back end code successfully returns a list of all players
     * whose name contains a selected string. The test confirms if the findPlayers method
     * successfully navigates through the test red black tree to find the correct number of players
     * name containing the selected string, and if each element contains that same string.
     *
     */
    @Test
   void backendDeveloper_TestfindPlayers() {
        SearchBackEnd tester = new SearchBackEnd();
        tester.addPlayer(new PlayerStats("Player A", 1, 2, 3));
        tester.addPlayer(new PlayerStats("player B", 4, 5, 6));
        tester.addPlayer(new PlayerStats("Gamer 1", 1, 1, 1));
        tester.addPlayer(new PlayerStats("Player C", 7, 8, 9));
        tester.addPlayer(new PlayerStats("Player D", 10, 11, 12));
        tester.addPlayer(new PlayerStats("player E", 13, 14, 15));
        tester.addPlayer(new PlayerStats("Gamer 2", 2, 2, 2));
        tester.addPlayer(new PlayerStats("Player F", 16, 17, 18));
        tester.addPlayer(new PlayerStats("Player G", 19, 20, 21));
        tester.addPlayer(new PlayerStats("Gamer 3", 3, 3, 3));
        List<PlayerStats> foundPlayers = tester.findPlayers("Player");

        if (foundPlayers.isEmpty() || foundPlayers.size() != 7) {
            fail(
                "Did not return proper list size: EXPECTED: isEmpty() to be false & list size 7, ACTUAL: is Empty() was "
                    + foundPlayers.isEmpty() + " list size was: " + foundPlayers.size());
        }

        for (int i = 0; i < foundPlayers.size(); i++) {
            if (!foundPlayers.get(i).getName().toLowerCase().contains("player")) {
                fail("One or more list elements does not contain the searched word");
            }
        }
    }

//		 ADDITIONAL TESTS

		
	 /**
         *INTEGRATION MANAGER TESTS
         *This method tests the methods in the SearchFrontEnd.java file
         */
        @Test
        void IntegrationTestFrontEnd(){
                //test that the correct string is returned when a player is searched in the databae
                {
                 SearchBackEnd engine = new SearchBackEnd();
                SearchFrontEnd tester = new SearchFrontEnd();
                engine.addPlayer(new PlayerStats("LeBron James", 26.0, 8.0, 8.0));
                engine.addPlayer(new PlayerStats("Anthony Davis", 24.0,9.5,11.0));
                engine.addPlayer(new PlayerStats("Anthony Edwards", 18.0,4.0,5.0));
                engine.addPlayer(new PlayerStats("Alex Caruso", 6.3,4.0,5.5));
                engine.addPlayer(new PlayerStats("Austin Reaves", 20.0,4.8,7.0));
                engine.addPlayer(new PlayerStats("Zach LaVine", 28.0,2.7,4.0));
                engine.addPlayer(new PlayerStats("LaMelo Ball", 12,9,5.8));
                
                String result = "LeBron James with 26.0 points, 8.0 assists, and 8.0 rebounds per game\n\n";
                assertEquals(result,tester.playerSearch(engine,"LeBron James"));
                }
                
        }       


    
	/**
	 *This method tests various methods used in combination by the FrontEnd code and BackEnd code
	 *It tests core feature of the app such as fnding all players with a common name,
	 * find players with the higest stats in different catgeores and retrieving those stats
	 *
	 */
    	@Test
    	void BackEndandFrontEnd(){
	//This test checks that the RBT is implemented correctly	    
	    {
	    try {
            SearchBackEnd tester = new SearchBackEnd();
            tester.addPlayer(new PlayerStats("EF", 2, 3, 4));
            tester.addPlayer(new PlayerStats("BF", 5, 6, 22));
            tester.addPlayer(new PlayerStats("AF", 2, 2, 2));
            tester.addPlayer(new PlayerStats("HF", 8, 9, 10));
            tester.addPlayer(new PlayerStats("PF", 11, 21, 13));
            tester.addPlayer(new PlayerStats("IF", 14, 15, 16));
            tester.addPlayer(new PlayerStats("LF", 3, 3, 3));
            tester.addPlayer(new PlayerStats("CF", 17, 18, 19));
            tester.addPlayer(new PlayerStats("DF", 20, 12, 22));

            // Below confirms each element in the tree is in the correct place.
	    assertEquals("EF", tester.RBT.root.rightChild.leftChild.rightChild.data.getName());
	    assertEquals("BF",tester.RBT.root.data.getName());
	    assertEquals("AF",tester.RBT.root.leftChild.data.getName());
	    assertEquals("HF",tester.RBT.root.rightChild.data.getName());
	    assertEquals("PF", tester.RBT.root.rightChild.rightChild.rightChild.data.getName());
	    assertEquals("IF", tester.RBT.root.rightChild.rightChild.leftChild.data.getName());
	    assertEquals("LF", tester.RBT.root.rightChild.rightChild.data.getName());
	    assertEquals("DF",tester.RBT.root.rightChild.leftChild.data.getName());

            //leftBranch = tester.RBT.root.leftChild.data.getName().equals("LeBron James")
              //  && tester.RBT.root.leftChild.leftChild.data.getName().equals("Stephen Curry")
                //&& tester.RBT.root.leftChild.rightChild.data.getName().equals("Jose Calderon");

           // rightBranch = tester.RBT.root.rightChild.data.getName().equals("Franz Wagner");
             //   && tester.RBT.root.rightChild.leftChild.data.getName().equals("Evan Fournier");
               // && tester.RBT.root.rightChild.rightChild.data.getName().equals("Harrison Barnes");
                //&& tester.RBT.root.rightChild.rightChild.rightChild.data.getName().equals("Isaiah Barnes");
                //&& tester.RBT.root.rightChild.rightChild.leftChild.data.getName().equals("Paul George");

            //assertTrue(tester.RBT.root.data.getName().equals("D") && leftBranch && rightBranch);

        } catch (Exception e) {
            fail("Should not have thrown any exception: Did not Properly addPlayer");
        }
	    }
	    {
  try {
            SearchBackEnd tester = new SearchBackEnd();
		//adding players to the database(BST)
            tester.addPlayer(new PlayerStats("Kemba Walker", 11, 21, 3));
            tester.addPlayer(new PlayerStats("Julius Randle", 14, 35, 21));
            tester.addPlayer(new PlayerStats("Derrick Rose", 10, 1, 11));
            tester.addPlayer(new PlayerStats("Michael Jordan", 17, 18, 9));
            tester.addPlayer(new PlayerStats("Steph Curry", 10, 2, 1));
            tester.addPlayer(new PlayerStats("Seth Curry", 15, 14, 13));
            tester.addPlayer(new PlayerStats("Gary Payton II", 12, 2, 2));
            tester.addPlayer(new PlayerStats("Alex Caruso", 16, 15, 18));
            tester.addPlayer(new PlayerStats("Austin Reaves", 29, 11, 2)); 
                                                                      
                                                                       
            
        	//test most points
            
            assertEquals("Austin Reaves",tester.getMostPoints().getName());
	    //test most assists
	    
	    
	    assertEquals("Julius Randle",tester.getMostAssists().getName());
	    
	    //test most rebounds
	    
	    assertEquals("Julius Randle",tester.getMostRebounds().getName());

	    List<PlayerStats> foundPlayers1 = tester.findPlayers("Curry");
  	    assertEquals(2,foundPlayers1.size());
  	    assertEquals("Steph Curry",foundPlayers1.get(0).getName());
  	    assertEquals("Seth Curry", foundPlayers1.get(1).getName());
  }
            catch (Exception e) {
            assertEquals(1,0);
   }
  
	//test finding players in list
  
  
    }
    }
    

	/**
	 *This method tests the funtion of the loadfile method, making sure the csv file is read correctly
	 */
		@Test
	void IntegrationTestDataWrangler(){
		PlayerLoader csvWriter = new PlayerLoader();
		//test getassist
	{
                try {
               		 List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
                	assertEquals(2.3,list.get(3).getAssist());
                 } catch (FileNotFoundException e) {
                	System.out.println("error in data wrangler code");
                }
	}
	//test getpoints
	{
 		try {
                	List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
                	assertEquals(25.7,list.get(3).getPoints());
                 } catch (FileNotFoundException e) {
                	System.out.println("Error in data wrangler code");
                }

	}

	//test getrebounds
	{
 		try {
                	List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
                	assertEquals(11,list.get(3).getRebounds());
                 } catch (FileNotFoundException e) {
                	System.out.println("Error in data wranngler code");
                }




	}	
	//test get name
	{
		try{
			List<NBAPlayersInterface> list = csvWriter.loadFile("PlayerList.csv");
		        assertEquals("julius randle",list.get(13).getName());
                 } catch (FileNotFoundException e) {
                	System.out.println("Error in data wranngler code");
                }
	}




	//test exception is caught
	{
 		try {
                	List<NBAPlayersInterface> list = csvWriter.loadFile("PlarList.csv");
		//if exception is thrown line is never reached
                	assertEquals(1, 2.3);
                 } catch (FileNotFoundException e) {
                assertEquals(1,1);

                }
 		catch(Exception e){
		       assertEquals(1,0);	
		}
	}

  }
   public static void main(String[] args) {
    String className = MethodHandles.lookup().lookupClass().getName();
    String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
    String[] arguments = new String[] {
      "-cp",
      classPath,
      "--include-classname=.*",
      "--select-class=" + className };
    ConsoleLauncher.main(arguments);
  }


}
