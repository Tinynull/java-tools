package com.zhaoliang.ignite.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Students test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class StudentsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void name() throws Exception {
        Students s = new Students.Builder(1).setOrgId(1)
                                            .setFirstName("zhao")
                                            .setLastName("weston")
                                            .setResume("i am the best!")
                                            .setSalary(1.0)
                                            .build();
        Assert.assertEquals(1, s.getId());
        Assert.assertEquals(1.0, s.getSalary(), 0);
        Assert.assertEquals("weston", s.getLastName());

    }
}