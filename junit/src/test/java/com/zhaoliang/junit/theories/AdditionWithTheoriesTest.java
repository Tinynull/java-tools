package com.zhaoliang.junit.theories;

import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
@RunWith(Theories.class)
public class AdditionWithTheoriesTest {

    @DataPoints
    public static int[] integers() {
        return new int[]{-1, -10, -1234567, 1, 10, 1234567};
    }

    @Theory
    public void a_plus_b_is_greater_than_a_and_greater_than_b(Integer a, Integer b) {
        Assume.assumeTrue(a > 0 && b > 0);
        assertTrue(a + b > a);
        assertTrue(a + b > b);
    }

    @Theory
    public void addition_is_commutative(Integer a, Integer b) {
        assertTrue(a + b == b + a);
    }
}
