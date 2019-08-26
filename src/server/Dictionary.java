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
//    public static void main (String args[]){
//        Dictionary dictionary = new Dictionary("Hello");
//        dictionary.test();
//    }
    public void test(){
        String word = "asd amd" ;
        String[] meaning = {"hello"};
        String[] meaning2 = {"hello2"};
//        dictionary.put(word, meaning);
//        dictionary.put(word,meaning2);
//
        System.out.println((formatParserForMeaning(word))[0]);



    }


    // Test if there is a word contained in the dictionary.
    public synchronized boolean contain(String word){
        return (dictionary.get(word))!=null;
    }
    // Sample input is:
    // Abaddon (n.) Hell; the bottomless pit.
    // "Abaft (adv.) Toward the stern; aft; as, to go abaft."
    // This parser will be able to separate the word and the defeinition.
    private synchronized  String[] formatParserForMeaning(String line){
        String[] tokens = line.split(" ");
//        String word = tokens[0];
        String[] meaning = new String[tokens.length-1];
        for(int i=0; i < tokens.length-1; i++){
            meaning[i] = tokens[i+1];
        }
        if(meaning == null || tokens.length==1){
            String[] output= new String[1];
            output[0] = line;
            return output;
        }
        return meaning;

    }
     // This will be the standard format.
    public synchronized String[] meaning(String word){
        return dictionary.get(word);
    }
    public synchronized void add(String word, String meaning){
        dictionary.put(word,formatParserForMeaning(meaning));
    }
    public synchronized void delete(String word){
        dictionary.remove(word);
    }
    public HashMap<String, String[]> getDictionary() {
        return dictionary;
    }

    private void defaultDictionary(){
        String word1 = "hello exclamation: hello; exclamation: hallo; exclamation: hullo";
        String word2 = "apple noun noun: apple; plural noun: apples; noun: apple tree; plural noun: apple trees";
        String word3 = "great adjective adjective: great; comparative adjective: greater; superlative adjective: greatest";
        dictionary.put(word1.split(" ")[0],formatParserForMeaning(word1));
        dictionary.put(word2.split(" ")[0],formatParserForMeaning(word2));
        dictionary.put(word3.split(" ")[0],formatParserForMeaning(word3));
    }

}
