package com.zhaoliang.jackson.core.jacksonInFiveMinutes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by zhaoliang on 2017/8/10.
 */
public class Test {
    public static void main(String[] args) throws IOException {


        ObjectMapper mapper = new ObjectMapper();
        URL resource = Test.class.getClassLoader().getResource("user.json");

        /*
          从这个例子可以看出支持的类型：
          1. boolean类型。
          2. byte[]数组。
          3. 简单的对象类型。
          4. 枚举类型。
         */
        User user = mapper.readValue(resource, User.class);
        System.out.println(user);

        /*
          将一个实例对象映射为json.
         */
        mapper.writeValue(new File("user-modified.json"), user);

        /**
         * 实例对象中的字段映射到json中的key,主要是看其get和set方法。
         * 在这个例子中，User中的字段：_isVerified，映射到json中为 verified。
         * 其主要原因是jackson是根据get和set方法来推断的。setVerified(boolean f); isVerified().
         *
         */
    }
}
