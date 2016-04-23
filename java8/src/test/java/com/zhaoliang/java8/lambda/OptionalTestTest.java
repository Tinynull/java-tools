package com.zhaoliang.java8.lambda;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/23.
 */
public class OptionalTestTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void test1() throws Exception {
        Optional<String> optional = Optional.of(null);
        System.out.println(optional.get());
    }

    @Test
    public void testTest2() throws Exception {
        Optional<String> optional = Optional.of("hello");
        assertEquals("hello", optional.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void test3() throws Exception {
        final Optional<Object> empty = Optional.empty();
        empty.get();
    }

    @Test(expected = NullPointerException.class)
    public void test4() throws Exception {
        final Optional<String> empty = Optional.empty();
        if (!empty.isPresent()) {
            System.out.println("empty");
        }

        final String hello = empty.orElse("hello");
        assertEquals(hello, "hello");

        final String orElseGet = empty.orElseGet(() -> "hello");
        assertEquals(orElseGet, "hello");

        empty.orElseThrow(NullPointerException::new);
    }

    /**
     * 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不
     * 为null，则创建包含mapping返回值的Optional作为map方法返回值
     * ，否则返回空Optional。
     *
     * @throws Exception
     */
    @Test
    public void testOptinalMap() throws Exception {
        final Optional<String> test = Optional.of("test");
        final Optional<String> optional = test.map(s -> s.toUpperCase());
        assertNotEquals(test, optional);
        assertEquals("TEST", optional.get());

        final Optional<String> empty = Optional.empty();
        final Optional<String> optional1 = empty.map(s -> s.toUpperCase());
        assertNotEquals(test, optional1);
        assertEquals("TEST", optional.orElse("TEST"));

    }

    /**
     * 如果有值，为其执行mapping函数返回Optional类型返回值，否则返
     * 回空Optional。flatMap与map（Funtion）方法类似，区别在于
     * flatMap中的mapper返回值必须是Optional。调用结束时，flatMap
     * 不会对结果用Optional封装。
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        final Optional<String> optional = Optional.of("test").flatMap(s -> Optional.of(s.toUpperCase()));
        assertEquals("TEST",optional.get());
    }

    @Test
    public void test6() throws Exception {
        final Optional<String> name = Optional.of("ziang");
        Optional<String> longName = name.filter((value) -> value.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));//输出Sanaulla

    }
}