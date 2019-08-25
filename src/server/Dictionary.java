package server;

import java.util.HashMap;

public class Dictionary {
    // Decide to use a hashmap to store the read-in dictionary.
    private HashMap<String, String[]> dictionary;
    public Dictionary(String path){
        // For now let's use an empty dictionary for test use.
        dictionary = new HashMap<String, String[]>();
        defaultDictionary();
    }
    // Test if there is a word contained in the dictionary.
    public boolean contain(String word){
//        if(dictionary.get(word)!=null){
//            return true;
//        }
//        return false;
        return dictionary.get(word)!=null;
    }
    private void defaultDictionary(){
        String word1= "hello";
        String[] meaning1 = {"exclamation: hello; exclamation: hallo; exclamation: hullo"};
        String word2 = "apple";
        String[] meaning2 = {"noun\n" +
                "noun: apple; plural noun: apples; noun: apple tree; plural noun: apple trees\n"};
        String word3= "great";
        String[] meaning3 = {"adjective\n" +
                "adjective: great; comparative adjective: greater; superlative adjective: greatest\n"};
        dictionary.put(word1,meaning1);
        dictionary.put(word2,meaning2);
        dictionary.put(word3, meaning3);
    }
    public String[] meaning(String word){
        return dictionary.get(word);
    }


    public HashMap<String, String[]> getDictionary() {
        return dictionary;
    }

}
