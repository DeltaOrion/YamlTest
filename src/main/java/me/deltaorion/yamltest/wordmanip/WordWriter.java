package me.deltaorion.yamltest.wordmanip;

import me.deltaorion.yamltest.Word;
import me.deltaorion.yamltest.yaml.YamlHelper;
import me.deltaorion.yamltest.yaml.YamlSection;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordWriter {
    private final Path path;
    private final String ROOT = "words";

    public WordWriter(Path path) {
        this.path = path;
    }

    public void save(List<Word> words) {
        YamlHelper yamlHelper = new YamlHelper(path);
        for(Word word : words) {
            Map<String,Object> serialization = word.serialize();
            serialization.forEach((str, obj) -> {
                yamlHelper.set(ROOT + YamlSection.PATH_SEPERATOR + word.getName() + YamlSection.PATH_SEPERATOR + str,obj);
            });
        }
        yamlHelper.save();
    }
}
