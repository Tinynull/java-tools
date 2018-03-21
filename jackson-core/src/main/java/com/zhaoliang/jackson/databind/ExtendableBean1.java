package com.zhaoliang.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtendableBean1 {
    public String name;
    private Map<String, String> properties = new HashMap<>();

    public ExtendableBean1() {
    }

    public ExtendableBean1(String name, Map<String, String> properties) {
        this.name = name;
        this.properties = properties;
    }

    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                "  \"name\": \"My bean\",\n" +
                "  \"attr2\": \"val2\",\n" +
                "  \"attr1\": \"val1\"\n" +
                "}";


        ExtendableBean1 bean =
                new ObjectMapper().readValue(json, ExtendableBean1.class);
        System.out.println(bean);
    }

    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "ExtendableBean{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
