package com.monogram.ignite;

import java.util.List;

/**
 * @author Ranger Tsao(cao.zhifu@gmail.com)
 */
public class Person {

    private String name;
    private int age;
    private List<String> addresses;

    public Person(String name, int age, List<String> addresses) {
        this.name = name;
        this.age = age;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", addresses=" + addresses +
            '}';
    }
}
