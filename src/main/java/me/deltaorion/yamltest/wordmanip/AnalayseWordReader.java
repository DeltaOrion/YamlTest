package me.deltaorion.yamltest.wordmanip;

import me.deltaorion.yamltest.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnalayseWordReader implements WordReader {

    private final Path fileStorage;
    private final File file;
    private final List<Word> words = new ArrayList<>();

    public AnalayseWordReader(Path fileStorage) {
        this.fileStorage = fileStorage;
        file = new File(fileStorage.toAbsolutePath().toString());
    }

    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Word word = new Word(line);
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Word> getWords() {
        return words;
    }
}
