package com.zhaoliang.jackson.databind;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BeanWithInject {
    @JacksonInject
    public int id;

    public String name;

    public static void main(String[] args) throws IOException {
        String json = "{\"name\" : \"hello\"}";
        InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
        BeanWithInject bean = new ObjectMapper().reader(inject)
                .forType(BeanWithInject.class)
                .readValue(json);

        System.out.println(bean);
    }

    @Override
    public String toString() {
        return "BeanWithInject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
