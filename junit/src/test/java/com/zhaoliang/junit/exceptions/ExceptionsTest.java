package com.zhaoliang.junit.exceptions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Exceptions Test
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
public class ExceptionsTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
        new ArrayList<>().get(0);
    }

    @Test
    public void testExceptionMessage() {
        try {
            new ArrayList<>().get(0);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        List<Object> list = new ArrayList<>();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 0, Size: 0");
        thrown.expectMessage(CoreMatchers.containsString("Size: 0"));
        list.get(0); // execution will never get past this line
    }
}
