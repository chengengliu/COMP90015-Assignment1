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
    public static void main (String args[]){
        Dictionary dictionary = new Dictionary("Hello");

    }
    // Test if there is a word contained in the dictionary.
    public boolean contain(String word){
        return dictionary.get(word)!=null;
    }
    // Sample input is:
    // Abaddon (n.) Hell; the bottomless pit.
    // "Abaft (adv.) Toward the stern; aft; as, to go abaft."
    // This parser will be able to separate the word and the defeinition.
    private static void formatParser(String line){
        String test = "Abaddon (n.) Hell; the bottomless pit.\n";
        String[] tokens = test.split(" ");
        String word = tokens[0];
        String[] meaning = new String[tokens.length-1];
        for(int i=0; i < tokens.length-1; i++){
            meaning[i] = tokens[i];
        }
        for (int i =0; i<meaning.length; i++){
            System.out.println(meaning[i]);
        }
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
//        System.out.println(meaning1.length);


//        dictionary.put(word1,meaning1);
//        dictionary.put(word2,meaning2);
//        dictionary.put(word3, meaning3);
    }
    public String[] meaning(String word){
        return dictionary.get(word);
    }


    public HashMap<String, String[]> getDictionary() {
        return dictionary;
    }

}
