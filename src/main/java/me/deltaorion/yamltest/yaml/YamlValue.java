package me.deltaorion.yamltest.yaml;

import java.util.Map;

public class YamlValue {

    private Object value;
    private final YamlSection yamlSection;

    public YamlValue(Object value) {
        this.value = value;

        if(value instanceof Map) {
            this.yamlSection = new YamlSection((Map<String,Object>) value);
        } else {
            this.yamlSection = null;
        }
    }

    private String asStringG() {

        if(this.value!=null)
            return this.value.toString();

        return null;
    }

    public String asString() {
        return asStringG();
    }

    public boolean isString() {
        return asStringG()!=null;
    }

    public void setString(String string) {
        value = string;
    }

    private Boolean asBooleanG() {
        if(value instanceof String) {
            String str = (String) value;
            if(str.equals("true") || str.equals("yes"))
                return true;

            if(str.equals("false") || str.equals("no"))
                return false;
        }
        return null;
    }

    public boolean asBoolean() {
        if(asBooleanG() != null)
            return asBooleanG();

        return false;
    }

    public boolean isBoolean() {
        return asBooleanG()!=null;
    }

    public void setBoolean(boolean value) {
        this.value = value;
    }

    private Integer asIntG() {
        try {
            if(value instanceof Integer)
                return (Integer) value;

            if(value instanceof Float) {
                float value = (Float) this.value;
                return (int) value;
            }

            if(value instanceof Double) {
                double value = (Double) this.value;
                return (int) value;
            }

            if(value instanceof String) {
                return Integer.parseInt((String) value);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }

    public int asInt() {
        if(asIntG()!=null)
            return asIntG();

        return 0;
    }

    public boolean isInt() {
        return asIntG()!=null;
    }

    public void setInt(int value) {
        this.value = value;
    }

    public YamlSection asYamlSection() {
        return yamlSection;
    }

    public boolean isYamlSection() {
        return yamlSection!=null;
    }

    public Object asObject() {
        return value;
    }

    public void set(Object value) {
        this.value = value;
    }

    public String toString() {
        if(value==null)
            return "null";

        return value.toString();
    }

    public boolean isNull() {
        return value==null;
    }
}
