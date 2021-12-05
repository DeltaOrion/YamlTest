package me.deltaorion.yamltest;

import java.util.HashMap;
import java.util.Map;

public class Word {

    private final String word;
    private final int letterCount;


    public Word(String word) {
        this.word = word;
        //add more properties to the word
        this.letterCount = countLetters(word);
    }

    private int countLetters(String word) {
        return word.length();
    }

    public String toString() {
        return "{ " + word + ", " + letterCount + "}";
    }

    public String getName() {
        return this.word;
    }

    public Map<String,Object> serialize() {
        //serialize them here
        Map<String,Object> serialized = new HashMap<>();
        serialized.put("word",word);
        serialized.put("letter-count",letterCount);

        return serialized;
    }

    public static Word deserialize(Map<String,Object> objectMap) {
        //expand on this if you want
        if(objectMap.containsKey("word")) {
            if(objectMap.get("word") instanceof String) {
                return new Word((String) objectMap.get("word"));
            }
        }

        return null;
    }
}
