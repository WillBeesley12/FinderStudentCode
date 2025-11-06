public class Beesley {
    private static final int RADIX = 256;
    private static final int DEFAULT_TABLE_SIZE = 997;
    private static final String INVALID = "INVALID KEY";
    int tableSize;
    int num;
    String[] keys;
    String[] values;
    boolean not_resizing;
    // Set up all of my instance variables with initial values
    public Beesley() {
        tableSize = DEFAULT_TABLE_SIZE;
        num = 0;
        keys = new String[tableSize];
        values = new String[tableSize];
        not_resizing = true;
    }

    // Basically just using Horner's method to hash.
    public int hash(String thingy) {
        int length = thingy.length();
        int answer = 0;
        for (int i = 0; i < length; i++) {
            answer = (answer * RADIX + thingy.charAt(i)) % tableSize;
        }
        return answer;
    }

    public void insert(String key, String value) {
        int hash = hash(key);
        while (keys[hash] != null) {
            // If there is no space, slide over and check. At the end, wrap around with mod.
            hash = (hash + 1) % tableSize;
            double load_factor = (double) num / tableSize;
            // If more than half the hashmap is full, resize. Rehash bc tableSize has changed.
            // Adding not_resizing makes it ignore this while inserting within resize().
            if (load_factor > 0.5 && not_resizing) {
                resize();
                hash = hash(key);
            }
        }
        // Once we find a spot, put the data in. Also increment num.
        keys[hash] = key;
        values[hash] = value;
        num++;
    }

    public String get(String key) {
        int hash = hash(key);
        // Check first to avoid error
        if (keys[hash] == null) {
            return INVALID;
        }
        // Need to linear probe, as it may not be in the exact spot on the first time
        while (!keys[hash].equals(key)) {
            hash = (hash + 1) % tableSize;
            // If there is nothing there, then this key has never been inserted
            if (keys[hash] == null) {
                return INVALID;
            }
        }
        return values[hash];
    }

    public void resize() {
        // Resize. Double the table size.
        not_resizing = false;
        String[] copy_keys = new String[tableSize];
        String[] copy_values = new String[tableSize];
        for (int i = 0; i < tableSize; i++) {
            copy_keys[i] = keys[i];
            copy_values[i] = values[i];
        }
        tableSize *= 2;
        keys = new String[tableSize];
        values = new String[tableSize];
        for (int i = 0; i < tableSize/2; i++) {
            if (copy_keys[i] != null) {
                insert(copy_keys[i], copy_values[i]);
            }
        }
        not_resizing = true;
    }
}