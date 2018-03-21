package com.zhaoliang.jackson.databind.jsonanygetter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class Test {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("att1", "value1");
        map.put("att2", "value2");

        ExtendableBean name = new ExtendableBean("name", map);
        String s = mapper.writeValueAsString(name);
        System.out.println(s);
    }
}
