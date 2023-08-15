import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;

// interface (implemented with proposal)
interface PlayerLoaderInterface {
    public List<NBAPlayersInterface> loadFile(String csvFilePath) throws FileNotFoundException;

}

// public class (implemented primarily in final app week)
@SuppressWarnings("unchecked")
public class PlayerLoader implements PlayerLoaderInterface {
    List stats = new LinkedList();

    /* 
     * Adds csv file, given path, to created a linked list with each playerStats to be added to PlayerStats method.
     * 
     * Return: LinkedList list
     */
    @Override
    public List<NBAPlayersInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        // TODO Auto-generated method stub
        File file = new File(csvFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exists");
        }
        List stats = new LinkedList();
	// for each cell in csv file the data is broken into a new array of strings
        try {
            List<String> temp = Files.readAllLines(Paths.get(csvFilePath));
            for (int i = 1; i < temp.size(); i++) {
                String[] cells = temp.get(i).split(","); // splits cells by ","
                String[] cellsReal = new String[cells.length];
                int count = 0;
                int startIndex = 0;
                int index = 0;
                for (int j = 0; j < cells.length; j++) {
                    String s = cells[j];
                    for (int k = 0; k < s.length(); k++) {
                        if (s.charAt(k) == '"') {
                            count++;
                        }
                    }
		    // if count is 0 then no cells with quotes. Add data into cellsReal
                    if (count == 0) {
                        String copy = "";
                        copy = cells[j];
                        cellsReal[index] = copy;
                        index++;
                        count = 0;
                        startIndex = j + 1;
                    }
		   // if count is not 0 and divisable by 2 then add without quotes
                    if (count % 2 == 0 && count != 0) {
                        String copy = "";
                        for (int m = startIndex; m <= (Math.abs(j - startIndex) + 1); m++) {
                            copy += cells[m];
                        }
                        cellsReal[index] = copy;
                        index++;
                        count = 0;
                        startIndex = j + 1;
                    }
                }
                if (csvFilePath.contains("PlayerList.csv")) {
                            PlayerStats a = new PlayerStats(cellsReal[0],
                                     Double.parseDouble((cellsReal[1])),
                             Double.parseDouble((cellsReal[2])),  Double.parseDouble((cellsReal[3])));
                    stats.add(a);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
        return stats; //returns list of playerStats
    }
}
 // Placeholder code
class PlayerLoaderPlaceholder implements PlayerLoaderInterface{
        public List<NBAPlayersInterface> loadFile(String csvFilePath) throws FileNotFoundException {
            List<NBAPlayersInterface> list = new LinkedList<>();
            list.add(new PlayerStatsPlaceholderA());
            list.add(new PlayerStatsPlaceholderB());
            return list;
        }

    }





