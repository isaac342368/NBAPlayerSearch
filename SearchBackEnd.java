import java.util.LinkedList;
import java.util.List;

// interface (implemented with proposal)

interface SearchBackEndInterface {
   // Adds Player to the red black tree
    public void addPlayer(PlayerStats player);

    // Returns player from the red black tree, null if they are not found
    public boolean containsPlayer(PlayerStats player);

    // returns list of the titles of players that contain the word titleWord in their name
    public List<PlayerStats> findPlayers(String titleWord);

    // returns player with most points
    public PlayerStats getMostPoints();

    // returns player with most assists
    public PlayerStats getMostAssists();

    // returns returns player with most rebounds
    public PlayerStats getMostRebounds();
}


public class SearchBackEnd implements SearchBackEndInterface {
    RedBlackTree<PlayerStats> RBT = new RedBlackTree();

    PlayerStats mostPointsPlayer;
    PlayerStats mostAssistsPlayer;
    PlayerStats mostReboundsPlayer;

    /**
     * Adds a player to the red black tree. Will catch and print error message if it catches a
     * NullPointerException or an IllegalArgumentException if an input is null, or if the player
     * already exists.
     *
     * @param player is the player to be added to the red black tree
     */
    @Override
    public void addPlayer(PlayerStats player) {
        try {
            boolean added = RBT.insert(player);
            if (added) {
                if (RBT.size() == 1) { // Activates for first added
                    mostPointsPlayer = player;
                    mostAssistsPlayer = player;
                    mostReboundsPlayer = player;
                }

                if (player.getPoints() > mostPointsPlayer.getPoints()) {
                    mostPointsPlayer = player;
                }
                if (player.getAssist() > mostAssistsPlayer.getAssist()) {
                    mostAssistsPlayer = player;
                }
                if (player.getRebounds() > mostReboundsPlayer.getRebounds()) {
                    mostReboundsPlayer = player;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to add player, Invalid entry");
        }

    }

    /**
     * Determines if a player is contained within the red black tree.
     *
     * @param player is the player to be checked for in the red black tree
     * @return true if the red black tree contains the player it is seeking
     */
    @Override
    public boolean containsPlayer(PlayerStats player) {
        return RBT.contains(player);
    }

    /**
     * Returns list of all players containing titleWord
     *
     * @param titleWord is the string name of player search for in the red black tree
     * @return List<PlayerStats> a list of all players containing titleWord
     */
    @Override
    public List<PlayerStats> findPlayers(String titleWord) {
        List<PlayerStats> foundPlayers = new LinkedList();
        if (titleWord == null || titleWord.trim().isEmpty()) {
            return foundPlayers;
        } else {
            return findPlayersHelper(RBT.root, new LinkedList(), titleWord);
        }
    }

    /**
     * Helper method to recursively sort through every player in the red black tree. Iterates
     * through the red black tree from left to right.
     *
     * @param currNode current node the method is checking player name for
     * @param foundList list of all players containing the search string
     * @param titleWord search string we are checking to see if it is contained in a player name
     * @return foundList list of all players containing the search string
     */
    private List<PlayerStats> findPlayersHelper(RedBlackTree.Node<PlayerStats> currNode,
        List<PlayerStats> foundList, String titleWord) {
        if (currNode == null)
            return foundList;

        if (currNode.data.getName().toLowerCase().contains(titleWord.toLowerCase())) {
            foundList.add(currNode.data);
        }

        findPlayersHelper(currNode.leftChild, foundList, titleWord);
        findPlayersHelper(currNode.rightChild, foundList, titleWord);
        return foundList;
    }

    /**
     * Returns the player with the most points
     *
     * @return mostPointsPlayer player with the most points
     */
    @Override
    public PlayerStats getMostPoints() {
        return this.mostPointsPlayer;
    }

    /**
     * Returns the player with the most assists
     *
     * @return mostPointsPlayer player with the most assists
     */
    @Override
    public PlayerStats getMostAssists() {
        return this.mostAssistsPlayer;
    }

    /**
     * Returns the player with the most rebounds
     *
     * @return mostPointsPlayer player with the most rebounds
     */
    @Override
    public PlayerStats getMostRebounds() {
        return this.mostReboundsPlayer;
    }



}


class SearchBackEndPlaceholder implements SearchBackEndInterface {

    @Override
    public void addPlayer(PlayerStats player) {}

    @Override
    public boolean containsPlayer(PlayerStats player) {
        return true;
    }

    @Override
    public List<PlayerStats> findPlayers(String titleWord) {
        List<PlayerStats> player = new LinkedList<>();
        player.add(new PlayerStats("Player A", 10, 11, 12));
        return player;
    }

    @Override
    public PlayerStats getMostPoints() {
        PlayerStats mostPlayer = new PlayerStats("Player A", 1, 2, 3);
        return mostPlayer;
    }

    @Override
    public PlayerStats getMostAssists() {
        PlayerStats mostAssist = new PlayerStats("Player B", 4, 5, 6);
        return mostAssist;
    }

    @Override
    public PlayerStats getMostRebounds() {
        PlayerStats mostRebounds = new PlayerStats("Player C", 7, 8, 9);
        return mostRebounds;
    }



}

