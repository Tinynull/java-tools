package com.zhaoliang.jackson.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/12/14.
 */
public class ObjectMapperTest {

    private static ObjectMapper mapper;
    private static File file;

    @BeforeClass
    public static void setUp() throws Exception {
        mapper = new ObjectMapper(); // create once, reuse
        file = new File("data.json");
    }

    @AfterClass
    public static void tearDown() throws Exception {


    }

    @org.junit.Test
    public void object2Json() throws Exception {
    }


    @Test
    public void json2Object1() throws Exception {
        MyValue value = mapper.readValue(file, MyValue.class);
        assertEquals("weston", value.getName());
        assertEquals(45, value.getAge());

    }

    @Test
    public void json2Object2() throws Exception {
        URL resource = ObjectMapperTest.class.getClassLoader().getResource("data.json");
        MyValue value = mapper.readValue(resource, MyValue.class);
        assertEquals("weston", value.getName());
        assertEquals(45, value.getAge());

    }

    @Test
    public void json2Object3() throws Exception {
        mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
    }

}
