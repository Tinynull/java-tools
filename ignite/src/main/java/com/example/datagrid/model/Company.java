package com.example.datagrid.model;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/7.
 */
public class Company {
    private String name;
    private String addr;

    public Company(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Company{" +
            "name='" + name + '\'' +
            ", addr='" + addr + '\'' +
            '}';
    }
}
