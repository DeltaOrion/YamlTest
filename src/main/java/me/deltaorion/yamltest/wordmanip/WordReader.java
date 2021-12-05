package me.deltaorion.yamltest.wordmanip;

import me.deltaorion.yamltest.Word;

import java.util.List;

public interface WordReader {

    public void read();

    public List<Word> getWords();
}
