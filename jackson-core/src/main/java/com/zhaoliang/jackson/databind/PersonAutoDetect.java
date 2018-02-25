package com.zhaoliang.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * The JsonAutoDetect.Visibility class contains constants matching the visibility
 * levels in Java, meaning ANY, DEFAULT, NON_PRIVATE, NONE, PROTECTED_AND_PRIVATE
 * and PUBLIC_ONLY.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonAutoDetect {

    public String name = null;
    private long personId = 123;

}