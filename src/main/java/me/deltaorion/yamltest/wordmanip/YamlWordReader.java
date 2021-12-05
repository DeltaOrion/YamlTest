package me.deltaorion.yamltest.wordmanip;

import me.deltaorion.yamltest.Word;
import me.deltaorion.yamltest.yaml.YamlHelper;
import me.deltaorion.yamltest.yaml.YamlSection;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class YamlWordReader implements WordReader{

    private final Path path;
    private final List<Word> words;

    public YamlWordReader(Path path) {
        this.path = path;
        words = new ArrayList<>();
    }

    @Override
    public void read() {
        YamlHelper yamlHelper = new YamlHelper(path);
        yamlHelper.getYamlSection("words").getKeys().forEach(key -> {
            Word word = new Word(yamlHelper.getString("words" + YamlSection.PATH_SEPERATOR + key + YamlSection.PATH_SEPERATOR + "word"));
            words.add(word);
        });
    }

    @Override
    public List<Word> getWords() {
        return words;
    }
}
