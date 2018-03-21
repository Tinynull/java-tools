package com.zhaoliang.jackson.core.seserialization.creator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BeanWithCreator {

    private int id;
    private String name;

    public BeanWithCreator(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"theName\":\"weston\",\"id\":\"1001\"}";
        BeanWithCreator beanWithCreator = objectMapper.readValue(json, BeanWithCreator.class);
        System.out.println(beanWithCreator);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanWithCreator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
