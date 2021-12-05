package me.deltaorion.yamltest.yaml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class YamlSection implements YamlPart {

    private final Map<String,YamlValue> root;
    public static final String PATH_SEPERATOR = ".";
    private final static String PATH_REGEX = "\\" + PATH_SEPERATOR;

    public YamlSection(Map<String,Object> objectMap) {
        root = new HashMap<>();
        if(objectMap!=null) {
            objectMap.forEach((key, value) -> {
                root.put(key, new YamlValue(value));
            });
        }
    }

    public YamlSection() {
        root = new HashMap<>();
    }

    @Override
    public String getString(String path) {
        return analysePath(path).asString();
    }

    @Override
    public boolean isString(String path) {
        return analysePath(path).isString();
    }

    public void setString(String path, String value) {
        setPath(path,value);
    }

    @Override
    public YamlSection getYamlSection(String path) {
        return analysePath(path).asYamlSection();
    }

    @Override
    public boolean isYamlSection(String path) {
        return analysePath(path).isYamlSection();
    }

    @Override
    public boolean getBoolean(String path) {
        return analysePath(path).asBoolean();
    }

    @Override
    public boolean isBoolean(String path) {
        return analysePath(path).isBoolean();
    }

    @Override
    public void setBoolean(String path, boolean value) {
        setPath(path,value);
    }

    @Override
    public int getInt(String path) {
        return analysePath(path).asInt();
    }

    @Override
    public boolean isInt(String path) {
        return analysePath(path).isInt();
    }

    @Override
    public void setInt(String path, int value) {
        setPath(path,value);
    }

    @Override
    public Object get(String path) {
        return analysePath(path).asObject();
    }

    @Override
    public void set(String path, Object value) {
        setPath(path,value);
    }

    private YamlValue getYamlValue(String key) {
        YamlValue yamlValue = root.get(key);
        if(yamlValue==null)
            return new YamlValue(null);

        return yamlValue;
    }

    public Set<String> getKeys() {
        return root.keySet();
    }

    private YamlValue analysePath(String path) {
        String[] split = path.split(PATH_REGEX);

        YamlSection root = this;
        final int last = split.length-1;
        for(int i=0;i<split.length;i++) {
            String key = split[i];
            if(i<last) {
                if (root.isYamlSection(key)) {
                    root = root.getYamlSection(key);
                } else {
                    return new YamlValue(null);
                }
            } else {
                return root.getYamlValue(key);
            }
        }

        return root.getYamlValue(path);
    }

    private void setPath(String path, Object value) {
        if(path.contains(".")) {
            createPath(path,value);
        } else {
            YamlValue yamlValue = new YamlValue(value);
            root.put(path,yamlValue);
        }
    }

    private void createPath(String path, Object value) {
        //get the yaml section
        String[] split = path.split(PATH_REGEX);
        YamlSection root = this;
        int i = 0;
        //get the final yaml section
        for(;i<split.length;i++) {
            String key = split[i];
            if(root.isYamlSection(key)) {
                root = root.getYamlSection(key);
            } else {
                break;
            }
        }

        final int last = split.length-1;
        for(;i<split.length;i++) {
            String key = split[i];
            if(i<last) {
                root.setPath(key,new LinkedHashMap<>());
                root = root.getYamlSection(key);
            } else {
                root.setPath(key,value);
            }
        }
    }

    public String toString() {
        return root.toString();
    }

    public Map<String,Object> getObjectMap() {
        Map<String,Object> linkedHashMap = new LinkedHashMap<>();
        root.forEach((s, yamlValue) -> {
           if(yamlValue.isYamlSection()) {
               YamlSection yamlSection = yamlValue.asYamlSection();
               Map<String,Object> l = yamlSection.getObjectMap();
               linkedHashMap.put(s,l);
           } else {
               linkedHashMap.put(s,yamlValue.toString());
           }
        });

        return linkedHashMap;
    }

}
