package me.deltaorion.yamltest;

import me.deltaorion.yamltest.wordmanip.AnalayseWordReader;
import me.deltaorion.yamltest.wordmanip.WordReader;
import me.deltaorion.yamltest.wordmanip.WordWriter;
import me.deltaorion.yamltest.wordmanip.YamlWordReader;
import me.deltaorion.yamltest.yaml.YamlHelper;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

    public static Main main = new Main();
    private final static String TEST_PATH = "C:\\Users\\User\\Videos\\Crusader Kings III\\words.txt";
    private final static String YAML_TEST_PATH = "C:\\Users\\User\\Videos\\Crusader Kings III\\test.yml";
    private final static String YAML_PATH = "C:\\Users\\User\\Videos\\Crusader Kings III\\words.yml";

    public static void main(String[] args) {
        main.run();
    }

    public void run() {
        Path path = FileSystems.getDefault().getPath(TEST_PATH);
        Path yamlPath = FileSystems.getDefault().getPath(YAML_PATH);
        WordReader wordReader = new AnalayseWordReader(path);
        wordReader.read();
        WordWriter writer = new WordWriter(yamlPath);
        writer.save(wordReader.getWords());

        WordReader result = new YamlWordReader(yamlPath);
        result.read();
        System.out.println(result.getWords());
    }

    public void test() {
        YamlHelper yamlHelper = new YamlHelper(YAML_TEST_PATH);
        System.out.println(yamlHelper.get("d.e.f.h.k"));
        yamlHelper.set("a",Math.random());
        yamlHelper.set("h","Hello World!");
        yamlHelper.set("d.e.p","Big Chonk");
        System.out.println(yamlHelper.isYamlSection("h"));
        yamlHelper.save();
    }
}
