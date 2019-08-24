package server;

import java.util.HashMap;

public class Dictionary {
    // Decide to use a hashmap to store the read-in dictionary.
    private HashMap<String, String[]> dictionary;
    public Dictionary(String path){
        // For now let's use an empty dictionary for test use.
        dictionary = new HashMap<String, String[]>();
    }

    public HashMap<String, String[]> getDictionary() {
        return dictionary;
    }
}
