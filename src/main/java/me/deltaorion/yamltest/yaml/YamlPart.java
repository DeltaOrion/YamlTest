package me.deltaorion.yamltest.yaml;

import java.util.Set;

public interface YamlPart {

    public String getString(String path);

    public boolean isString(String path);

    public void setString(String path, String value);

    public YamlSection getYamlSection(String path);

    public boolean isYamlSection(String path);

    public boolean getBoolean(String path);

    public boolean isBoolean(String path);

    public void setBoolean(String path, boolean value);

    public int getInt(String path);

    public boolean isInt(String path);

    public void setInt(String path, int value);

    public Object get(String path);

    public void set(String path, Object value);

    public Set<String> getKeys();
}
