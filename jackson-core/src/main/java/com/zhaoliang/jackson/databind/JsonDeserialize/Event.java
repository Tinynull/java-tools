package com.zhaoliang.jackson.databind.JsonDeserialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.Date;

public class Event {

    private String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    public static void main(String[] args) throws IOException {
        String json = "{\"name\" : \"weston\",\"date\" : \"20-12-2014 02:30:00”}\"}";
        Event event = new ObjectMapper().readValue(json, Event.class);
        System.out.println(event);

    }


    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
