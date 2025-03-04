/*
* Author: Chengeng Liu
* Student ID: 813174
* */
package server;

import java.io.*;
import java.util.HashMap;

public class Dictionary {
    // Decide to use a hashmap to store the read-in dictionary.
    private HashMap<String, String[]> dictionary;
    public Dictionary(String path){
        // For now let's use an empty dictionary for test use.
        dictionary = new HashMap<String, String[]>();
        // At the stage, the file is guaranteed to exist by the DicServer.
        loadDictionary(path);
    }
    private void loadDictionary(String path){
        if(path == null){
            defaultDictionary();
        }
        else{
            try{
                File file = new File(path);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line= null;
                while((line=reader.readLine())!=null){
                    line.toLowerCase();
                    if(assertValidaDictionary(line.split(" ")[0])){
                        dictionary.put(line.split(" ")[0],formatParserForMeaning(line));
                    }
                    else {
                        System.out.println("Dictionary Format wrong! Using built-in dictioanry now!");
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("No such file! Use default dictionary!");
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("No such file! Use default dictionary!");
            }
//        test();
        }
    }
    private boolean assertValidaDictionary(String input){
        int ascii=0;
        for(int i=0; i<input.length(); i++){
            ascii = input.charAt(i);
            // If it is non-alphabetical character(not within the range of a-z)
            if (ascii<97 || ascii>122){
                return false;
            }
        }
        return true;
    }

    // Test if there is a word contained in the dictionary.

    /**
     * If the current dictionary contains the searching word.
     * @param word target of searching
     * @return true: exist flae: not exist
     */
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
    // Convert from String to array of String.
    private synchronized String[] formatParserForInput(String input){
        String[] tokens = input.split(" ");
        String[] meaning = new String[tokens.length];
        for(int i=0; i<tokens.length; i++){
            meaning[i] = tokens[i];
        }
        if(meaning == null || tokens.length==1){
            String[] output= new String[1];
            output[0] = input;
            return output;
        }
        return meaning;
    }
     // This will be the standard format.

    /**
     * Retrieve the meaning of query word.
     * @param word target
     * @return meaning of the word
     */
    public synchronized String[] meaning(String word){
        return dictionary.get(word);
    }

    /**
     * Add a word with its meaning into the dictionary.
     * @param word word
     * @param meaning the meaning of the word
     * @param flag specify which parser it needs.
     */
    public synchronized void add(String word, String meaning,String flag){
//        dictionary.put(word,formatParserForMeaning(meaning));
        switch (flag){
            case "input":
                dictionary.put(word,formatParserForInput(meaning));
                break;
            case "meaning":
                dictionary.put(word,formatParserForMeaning(meaning));
        }
    }

    /**
     * Delete the word from the dictionary.
     * @param word the word you want to delete
     */
    public synchronized void delete(String word){
        dictionary.remove(word);
    }

    /**
     * Return a dictionary.
     * @return dictionary
     */
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
