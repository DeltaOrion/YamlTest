package me.deltaorion.yamltest.yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class YamlHelper implements YamlPart {

    private final Yaml yaml;
    private YamlSection root;
    private final Path path;
    private final DumperOptions options = new DumperOptions();

    public YamlHelper(String path) {
        this(FileSystems.getDefault().getPath(path));
    }

    public YamlHelper(Path path) {
        initOptions();
        yaml = new Yaml(options);
        this.path = path;
        reloadFile();
    }

    private void initOptions() {
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
    }


    @Override
    public String getString(String path) {
        return root.getString(path);
    }

    @Override
    public boolean isString(String path) {
        return root.isString(path);
    }

    public void setString(String path, String value) {
        root.setString(path,value);
    }

    @Override
    public YamlSection getYamlSection(String path) {
        return root.getYamlSection(path);
    }

    @Override
    public boolean isYamlSection(String path) {
        return root.isYamlSection(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return root.getBoolean(path);
    }

    @Override
    public boolean isBoolean(String path) {
        return root.isBoolean(path);
    }

    @Override
    public void setBoolean(String path, boolean value) {
        root.setBoolean(path,value);
    }

    @Override
    public int getInt(String path) {
        return root.getInt(path);
    }

    @Override
    public boolean isInt(String path) {
        return root.isInt(path);
    }

    @Override
    public void setInt(String path, int value) {
        root.setInt(path,value);
    }

    @Override
    public Object get(String path) {
        return root.get(path);
    }

    @Override
    public void set(String path, Object value) {
        root.set(path,value);
    }

    @Override
    public Set<String> getKeys() {
        return root.getKeys();
    }

    public void save() {
        Map<String,Object> dump = root.getObjectMap();
        //FileWriter writer = null;
        FileWriter writer = null;
        try {
            writer = new FileWriter(path.toAbsolutePath().toString());
            yaml.dump(dump, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadFile() {
        try {

            File file = new File(path.toAbsolutePath().toString());
            if(!file.exists())
                file.createNewFile();

            Map<String,Object> objectMap = yaml.load(new FileInputStream(file));
            this.root = new YamlSection(objectMap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid Path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
