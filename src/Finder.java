import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: William Beesley
 **/

public class Finder {

    private static Beesley data;

    public Finder() {
        data = new Beesley();
    }
    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] stuff = line.split(",");
            data.insert(stuff[keyCol], stuff[valCol]);
        }
        br.close();
    }

    public String query(String key){
        return data.get(key);
    }
}