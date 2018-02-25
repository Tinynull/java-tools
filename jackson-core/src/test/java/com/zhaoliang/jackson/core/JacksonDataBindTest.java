package com.zhaoliang.jackson.core;

/**
 * jackson data bind test.
 * Created by (zhaoliang@metagraph.io) on (17-2-8).
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

enum Sexy {
    MEN("男", "M"), WOMEN("女", "W");
    private String text;
    private String code;

    Sexy(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{\"text\":\"" + getText() + "\",\"code\":\"" + getCode() + "\"}";
    }
}

/**
 * Jackson Data bind 解析生成示例
 */
public class JacksonDataBindTest {

    private ObjectMapper baseMapper;
    private ObjectMapper prettyMapper1;
    private ObjectMapper prettyMapper2;
    private ObjectMapper nonEmptyMapper;

    @Before
    public void init() {
        baseMapper = new ObjectMapper();

        // 对于空的对象转json的时候不抛出错误
        baseMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 禁用遇到未知属性抛出异常
        baseMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        baseMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 低层级配置
        baseMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        baseMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        baseMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 配置两个副本
        prettyMapper1 = baseMapper.copy();
        prettyMapper2 = baseMapper.copy();

        // 高级配置
        prettyMapper1.enable(SerializationFeature.INDENT_OUTPUT);
        prettyMapper1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        prettyMapper1.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        // 禁用序列化日期为timestamps
        prettyMapper1.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        prettyMapper1.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);

        // Json格式化展示
        prettyMapper2.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        prettyMapper2.enable(SerializationFeature.INDENT_OUTPUT);
        prettyMapper2.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        prettyMapper2.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);

        nonEmptyMapper = new ObjectMapper();
        nonEmptyMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        nonEmptyMapper.enable(SerializationFeature.INDENT_OUTPUT);
        nonEmptyMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testReadValue() throws IOException {
        String json = "{\n" +
                "  \"name\" : \"发如雪\",\n" +
                "  \"now\" : \"2015-12-17 17:25:13\",\n" +
                "  \"sexy\" : \"MEN\"\n" +
                "}";
        TestBean testBean = nonEmptyMapper.readValue(json, TestBean.class);
        assertEquals(Sexy.MEN, testBean.getSexy());
        System.out.println(testBean.toString());
    }

    @Test
    public void testWriteValue() throws IOException {
        TestBean testBean = new TestBean("发如雪", Sexy.MEN);
        System.out.println(prettyMapper1.writeValueAsString(testBean));
        System.out.println(prettyMapper2.writeValueAsString(testBean));
        System.out.println(nonEmptyMapper.writeValueAsString(testBean));
    }

}

class TestBean {
    private String name;
    private String course;
    private Date now;
    private Sexy sexy;

    public TestBean() {
    }

    public TestBean(String name, Sexy sexy) {
        this.name = name;
        this.sexy = sexy;
        this.now = new Date();
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sexy getSexy() {
        return sexy;
    }

    public void setSexy(Sexy sexy) {
        this.sexy = sexy;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", now=" + now +
                ", sexy=" + sexy +
                '}';
    }
}