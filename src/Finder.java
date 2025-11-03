import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: William Beesley
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";

    public Finder() {
        data = new HashMap<String, String>();
    }

    HashMap<String, String> data;

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] stuff = line.split(",");
            data.put(stuff[keyCol], stuff[valCol]);
        }
        br.close();
    }

    public String query(String key){
        String answer = data.get(key);
        if (answer == null) {
            return INVALID;
        }
        return data.get(key);
    }
}