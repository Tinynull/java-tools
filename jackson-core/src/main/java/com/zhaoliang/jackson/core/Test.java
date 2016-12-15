package com.zhaoliang.jackson.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/12/6.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse

//        MyValue value = mapper.readValue(new File("data.json"), MyValue.class);
//        value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
        MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
//        mapper.writeValue(new File("result.json"), myResultObject);
//        byte[] jsonBytes = mapper.writeValueAsBytes(myResultObject);
//        String jsonString = mapper.writeValueAsString(myResultObject);
    }
}
